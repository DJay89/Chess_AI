package game_logic;
public class Evaluation {

	private GameState gamestate;
	private boolean isWhite;
	private int enemyThreatenedCount = 0; //number of threatened enemy pieces with higher base value
	private int[] whitePawnRow = {0, 0, -1, 0, 2, 14, 30, 0};
	private int[] blackPawnRow = {0, 30, 14, 2, 0, -1, 0, 0};
	private int[] pawnLine = {-2, 0, 3, 4, 5, 1, -2, -2};

	public Evaluation (GameState gamestate, boolean isWhite) {
		this.gamestate = gamestate;
		this.isWhite = isWhite;
	}

	public double evaluateState() {
		return standardEvaluation(isWhite)-standardEvaluation(!isWhite);
		//		if(isWhite) {
		//			return standardEvaluation(true)-standardEvaluation(false);
		//		} else {
		//			return standardEvaluation(false)-standardEvaluation(true);
		//		}
	}

	private double standardEvaluation(boolean isWhite) {

		int[] borderline = {7, 23, 38, 55, 71, 87, 103};
		double pointSum = 0;

		for (int i = 0; i <= 119; i++) {
			if((gamestate.getField(i)%2 == 1 && isWhite) ||
					gamestate.getField(i)%2 == 0 && !isWhite && gamestate.getField(i) != 0) {

				pointSum += evaluatePiece(i, gamestate.getPieceType(gamestate.getX(i), gamestate.getY(i)));
			}

			if(contains(borderline, i)){
				i += 8;
			}	

		}
		//TODO evaluate number enemy pieces threatened by lower value pieces and add to pointsum
		//TODO reset enemyThreatenedCount
		return pointSum;
	}

	private boolean contains(int[] array, int key) {
		boolean result = false;

		for (int i : array) {
			if (i == key) {
				result = true;
				break;
			}
		}
		return result;
	}

	private double evaluatePiece(int position, int pieceType) {
		switch(pieceType) {
		//White Pawn: 100 + whitePawnRow[Row] + PawnLine[Line]*Row/2
		case 1: return 100 + whitePawnRow[gamestate.getY(position)] + (pawnLine[gamestate.getX(position)]*gamestate.getY(position)/2);
		//Black Pawn: 100 + blackPawnRow[Row] + PawnLine[Line]*Row/2
		case 2: return 100 + blackPawnRow[gamestate.getY(position)] + (pawnLine[gamestate.getX(position)]*gamestate.getY(position)/2);
		//Knight: 300 + 3.0 * (4-Distance to center)
		//Check for threatened pieces with higher value than the knight
		case 3:	case 4: knightThreats(position); 
						return 300 + 2.0*(4-distanceToCenter(position));
		//Bishop: 300 + 2.0 * Number of covered fields
		case 5: case 6: return 300 + 2.0*(coverDirection(position, 0, 15)+coverDirection(position, 0, 17)+coverDirection(position, 0, -17)+coverDirection(position, 0, -15));
		//Rook: 500 + 1.5 * Number of covered fields
		case 7: case 8: return 500 + 1.5*(coverDirection(position, 0, 16)+coverDirection(position, 0, -16)+coverDirection(position, 0, 1)+coverDirection(position, 0, -1));	
		//Queen: 900 + 1.0 * Number of covered fields
		case 9: case 10: return 900 + (coverDirection(position, 0, 16)+coverDirection(position, 0, -16)+coverDirection(position, 0, 1)+coverDirection(position, 0, -1)+coverDirection(position, 0, 15)+coverDirection(position, 0, 17)+coverDirection(position, 0, -17)+coverDirection(position, 0, -15));
		//King: 10000
		case 11: case 12: return 10000;
		default: System.err.println("Undefined piecetype in file Evaluation.java"); return 0;
		}
	}

	/* The following method returns a number of covered fields in a direction specified by an integer
	 * up 			=  16
	 * down			= -16
	 * left			= -1
	 * right		=  1
	 * up-right		=  17
	 * up-left		=  15
	 * down-left	= -17
	 * down-right	= -15
	 */
	private int coverDirection(int position, int coveredFields, int direction) {

		if(!gamestate.outOfBoard(position+direction)) {
			coveredFields++;

			if(gamestate.checkField(position+direction) == 0) {
				coveredFields = coverDirection(position+direction, coveredFields, direction);
			} else if(gamestate.checkField(position+direction) > gamestate.checkField(position) && 
					((isWhite && gamestate.checkField(position+direction)%2 == 0) || 
					!isWhite && gamestate.checkField(position+direction)%2 == 1)) {
				//If the covered field is an enemy piece with higher base value count it
				enemyThreatenedCount++;
			}
		}
		return coveredFields;
	}

	// The following method returns the knights distance from the center, defined by how many fields the knight can cover from its position
	private int distanceToCenter(int position) {
		switch(position) {
		case 82: case 83: case 84: case 85: case 66: case 67: case 68: case 69: case 50: case 51: case 52: case 53: case 34: case 35: case 36: case 37: 										return 0;
		case 98: case 99: case 100: case 101: case 81: case 65: case 49: case 33: case 86: case 70: case 54: case 38: case 18: case 19: case 20: case 21: 										return 1;
		case 114: case 115: case 116: case 117: case 2: case 3: case 4: case 5: case 80: case 64: case 48: case 32: case 87: case 71: case 55: case 39: case 97: case 102: case 17: case 22: 	return 2;
		case 96: case 113: case 118: case 103: case 16: case 1: case 6: case 23: 																												return 3;
		case 0: case 112: case 119: case 7: 																																					return 4;
		default: System.err.println("Knight out of bounds in file Evaluation.java"); 																											return 0;
		}
	}

	private void knightThreats(int position) {
		int[] knightMoves = {33, -33, 31, -31, 18, -18, -14, 14};
		for(int i = 0; i < knightMoves.length; i++) {
			if(!gamestate.outOfBoard(position+knightMoves[i]) && (gamestate.checkField(position+knightMoves[i]) > gamestate.checkField(position)) && 
					(isWhite && gamestate.checkField(position+knightMoves[i])%2 == 0 || 
					!isWhite && gamestate.checkField(position+knightMoves[i])%2 == 1)){
					enemyThreatenedCount++;
			}
		}
	}
	
	private void pawnThreats(int position) {
		//TODO enemy pieces of higher value threatened by pawns
	}
}

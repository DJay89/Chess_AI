package game_logic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Evaluation {

	private GameState gamestate;
	private boolean isWhite;

	public Evaluation (GameState gamestate, boolean isWhite) {
		this.gamestate = gamestate;
		this.isWhite = isWhite;
	}

	public double evaluateState() {
		if(isWhite) {
			return standardEvaluation(true)-standardEvaluation(false);
		} else {
			return standardEvaluation(false)-standardEvaluation(true);
		}
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
		case 1:
			break;
		case 2:
			break;
			//Knight: 300 + 3.0 * (4-Distance to center)
		case 3:	case 4: return 0;
			//Bishop: 300 + 2.0 * Number of covered fields
		case 5: case 6: return 300 + 2.0*(coverDirection(position, 0, 15)+coverDirection(position, 0, 17)+coverDirection(position, 0, -17)+coverDirection(position, 0, -15));
			//Rook: 500 + 1.5 * Number of covered fields
		case 7: case 8: return 500 + 1.5*(coverDirection(position, 0, 16)+coverDirection(position, 0, -16)+coverDirection(position, 0, 1)+coverDirection(position, 0, -1));	
			//Queen: 900 + 1.0 * Number of covered fields
		case 9: case 10: return 900 + (coverDirection(position, 0, 16)+coverDirection(position, 0, -16)+coverDirection(position, 0, 1)+coverDirection(position, 0, -1)+coverDirection(position, 0, 15)+coverDirection(position, 0, 17)+coverDirection(position, 0, -17)+coverDirection(position, 0, -15));
			//King: 10000
		case 11: case 12: return 10000;
		}
		return 123456789;
	}
	
	/* The following method covers fields in a direction specified by an integer
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
			}
		}
		return coveredFields;
	}
}

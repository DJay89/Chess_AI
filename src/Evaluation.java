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
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7: case 8: return 500*coverForward(position, 0);	
		case 9:
			break;
		case 10:
			break;
		case 11: case 12: return 10000;
		default:
		}
		return 0;
	}
	
	private int coverForward(int position, int coveredFields) {
		
		
		if(!gamestate.outOfBoard(position+16)) {
			coveredFields++;
			
			if(gamestate.checkField(position+16) == 0) {
				coveredFields = coverForward(position+16, coveredFields);
			}
		}
		return coveredFields;	
	}
	
}

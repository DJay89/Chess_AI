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
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		}
		
		return 0;
	}
	
}

package game_logic;

import java.util.ArrayList;

public class AlphaBeta {

	private GameState gameState;
	private int searchDepth;
	private boolean isWhite;

	public AlphaBeta(GameState gameState, int searchDepth, boolean isWhite) {
		this.gameState = gameState;
		this.searchDepth = searchDepth;
		this.isWhite = isWhite;
	}

	public double runAlphaBeta(double alpha, double beta, int currentDepth) {

		//If node is a leaf
		if(currentDepth == searchDepth) {
			Evaluation eva = new Evaluation(gameState, isWhite);
			return eva.evaluateState();
		}

		MoveGenerator mg = new MoveGenerator();
		ArrayList<MoveType> childNodes = mg.moveGen(index)
		

		//If current node is MAX
		if(currentDepth%2 == 0) {
			while(alpha < beta) {
				double V = runAlphaBeta(alpha, beta, currentDepth+1);
				if(V > alpha) {
					alpha = V;
				}
			}
			return alpha;

			//If current node is MIN
		} else if(currentDepth%2 == 1) {
			while(alpha < beta) {
				double V = runAlphaBeta(alpha, beta, currentDepth+1);
				if(V < beta) {
					beta = V;
				}
			}
			return beta;
		}

		return 0;
	}
}

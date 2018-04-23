package game_logic;

import java.util.ArrayList;

public class AlphaBeta {

	private GameState gameState;
	private int searchDepth;
	private boolean isWhite;
	private int nextNode;

	public AlphaBeta(GameState gameState, int searchDepth, boolean isWhite) {
		this.gameState = gameState;
		this.searchDepth = searchDepth;
		this.isWhite = isWhite;
	}

	public double runAlphaBeta(double alpha, double beta, int currentDepth, GameState gameState) {

		MoveGenerator mg = new MoveGenerator();
		ArrayList<MoveType> childNodes = mg.getAll(isWhite, gameState);

		// If node is a leaf
		if (currentDepth == searchDepth) {
			Evaluation eva = new Evaluation(gameState, isWhite);
			return eva.evaluateState();
		}

		// If current node is MAX
		if (currentDepth % 2 == 0) {
			while (alpha < beta) {
				double V = runAlphaBeta(alpha, beta, currentDepth++,
						this.gameState.newState(gameState, childNodes.get(nextNode++)));
				if (V > alpha) {
					alpha = V;
				}
			}
			return alpha;
		}

		// If current node is MIN
		else {
			while (alpha < beta) {
				double V = runAlphaBeta(alpha, beta, currentDepth++,
						this.gameState.newState(gameState, childNodes.get(nextNode++)));
				System.out.println(nextNode);
				if (V < beta) {
					beta = V;
				}
			}
			return beta;
		}

	}
}

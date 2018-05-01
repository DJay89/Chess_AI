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

	public double runAlphaBeta(double alpha, double beta, int currentDepth, MoveType moveType) {

		//If node is a leaf
		if(currentDepth == searchDepth) {
			Evaluation eva = new Evaluation(gameState, isWhite);
			return eva.evaluateState();
		}

		MoveGenerator mg = new MoveGenerator();
		ArrayList<MoveType> childNodes = mg.getAll(isWhite, gameState);
		int nextNode = 0;

		gameState.newState(moveType);
		
		//If current node is MAX
		if(currentDepth%2 == 0) {
			while(alpha < beta && nextNode < childNodes.size()) {
				gameState.newState(moveType);
				double V = runAlphaBeta(alpha, beta, currentDepth+1, childNodes.get(nextNode++));
				gameState.oldState(moveType);
				if(V > alpha) {
					alpha = V;
				}
			}
			gameState.oldState(moveType);
			return alpha;

		//If current node is MIN
		} else {
			while(alpha < beta && nextNode < childNodes.size()) {
				gameState.newState(moveType);
				double V = runAlphaBeta(alpha, beta, currentDepth+1, childNodes.get(nextNode++));
				gameState.oldState(moveType);
				if(V < beta) {
					beta = V;
				}
			}
			gameState.oldState(moveType);
			return beta;
		}
	}
}

package algorithm;

import algorithm.AlphaBeta_I.MoveValue;
import game_logic.GameState;

public class RunMinMax {

	public static void main(String[] args) {
		
		GameState state = new GameState();
		
		int[] inputState = state.getCurrentState();
		
//		GameState testInputState = new GameState(inputState);
//		testInputState.printBoard();
		
		AlphaBeta_I algo_i = new AlphaBeta_I(inputState);
		AlphaBeta_II  algo_ii = new AlphaBeta_II();
		
		double alpha = -999999;
		double beta = 999999;
		int maxDepth = 4;
		boolean isWhite = true;
		
		MoveValue bestMove;
		
		bestMove = algo_i.alphaBeta(alpha, beta, maxDepth, isWhite);
		
		System.err.println(bestMove.returnMove.getContent() + ": " + bestMove.returnMove.getOldPos() + " -> " + bestMove.returnMove.getNewPos());
		System.err.println("Value: " + bestMove.returnValue);
		
//		algo_ii.minMax(alpha, beta, maxDepth, isWhite);

	}

}

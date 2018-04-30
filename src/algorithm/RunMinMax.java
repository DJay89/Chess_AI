package algorithm;

import algorithm.MinMax_I.MoveValue;
import game_logic.GameState;

public class RunMinMax {

	public static void main(String[] args) {
		
		GameState state = new GameState();
		
		int[] inputState = state.getCurrentState();
		
		GameState testInputState = new GameState(inputState);
		testInputState.printBoard();
		
		MinMax_I algo_i = new MinMax_I(inputState);
		MinMax_II  algo_ii = new MinMax_II();
		
		double alpha = -999999;
		double beta = 999999;
		int maxDepth = 4;
		boolean isWhite = true;
		
//		MoveValue bestMove;
//		
//		bestMove = algo_i.minMax(alpha, beta, maxDepth, isWhite);
//		
//		System.err.println(bestMove.returnMove.getContent() + ": " + bestMove.returnMove.getOldPos() + " -> " + bestMove.returnMove.getNewPos());
//		System.err.println("Value: " + bestMove.returnValue);
		
		algo_ii.minMax(alpha, beta, maxDepth, isWhite);

	}

}

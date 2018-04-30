package algorithm;

import algorithm.MinMax.MoveValue;

public class RunMinMax {

	public static void main(String[] args) {
		
		MinMax minMax = new MinMax();
		AlternativeMinMax alt = new AlternativeMinMax();
		
		double alpha = -999999;
		double beta = 999999;
		int maxDepth = 2;
		boolean isWhite = true;
		
		MoveValue bestMove;
		
		bestMove = minMax.minMax(alpha, beta, maxDepth, isWhite);
		
		System.err.println(bestMove.returnMove.getContent() + ": " + bestMove.returnMove.getOldPos() + " -> " + bestMove.returnMove.getNewPos());
		System.err.println("Value: " + bestMove.returnValue);
		
		
//		System.out.println(alt.minMax(alpha, beta, maxDepth, isWhite));

	}

}

package algorithm;

public class RunMinMax {

	public static void main(String[] args) {
		
		MinMax minMax = new MinMax();
		AlternativeMinMax alt = new AlternativeMinMax();
		
		double alpha = 999999;
		double beta = -999999;
		int maxDepth = 4;
		boolean isWhite = true;
		
		System.out.println(minMax.minMax(alpha, beta, maxDepth, isWhite));
//		System.out.println(alt.minMax(alpha, beta, maxDepth, isWhite));

	}

}

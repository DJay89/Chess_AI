package game_logic;

public class Main {

	public static void main(String[] args) {
		
		Algorithm ab = new Algorithm();
		
		boolean isWhite = true;
		
		System.out.println(ab.alphaBeta(-999999, 999999, 4, isWhite));
		
		

	}

}

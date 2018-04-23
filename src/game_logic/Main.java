package game_logic;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		MoveType bestMove;
		
		GameState gameState = new GameState();
		boolean isWhite = true;
		int time = 10000;
		
		MoveGenerator mg = new MoveGenerator();

		
		
//		TimerController tc = new TimerController(gameState, isWhite, time);
//		tc.run();
//		while(!tc.timeout());
//		bestMove = tc.getBestMove();
//		
//		
//		System.out.println(bestMove.getOldPos() + " " + bestMove.getNewPos());
		
		/*
		test = mg.getAll(isWhite, gameState);
		
		int c = 0;
		for (MoveType mt : test ) {
		
			System.out.println(mt.getNewPos());
			c++;
			
		}
		
		System.out.println("Tal: " + c);
		System.out.println(test.get(30));
		
	*/

		ArrayList<MoveType> test = new ArrayList<MoveType>();
//		for(int i = 0; i < 120; i++) {
//			if()
//		}
		test = mg.moveGen(0, gameState);
		for(MoveType mt : test)
			System.out.println(mt.getOldPos() + "->" + mt.getNewPos());

		
		
	}

}

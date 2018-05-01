package game_logic;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		final int time = 1500;
		boolean isWhite = false;

		GameState gameState = new GameState();

		Scanner playerInput = new Scanner(System.in);

		// Translate input
		FieldTranslator translator = new FieldTranslator();
		
		
		boolean missingInput = true;

		//	System.out.println("Do you want to play /white, /black or /random ?");
		//	while(missingInput) {
		//		switch(playerInput.nextLine()) {
		//		case:
		//		}
		//	}

		MoveGenerator mg = new MoveGenerator();


		System.out.println("mg.moveGen(115, gameState): " + mg.moveGen(115, gameState));

		boolean gameRunning = true;

		for(MoveType i : mg.getAll(isWhite, gameState)) {
			System.out.println(i.getPiece());
		}
		gameState.printBoard();

		while(gameRunning) {
			System.out.println("Player has turn");
			System.out.println("oldpos:");
			//int oldPos = playerInput.nextInt();
			String oldPos = playerInput.next();
			int oldPosIndex = translator.getFieldCoordinate(oldPos);
			System.out.println("newpos:");
			// int newPos = playerInput.nextInt();
			String newPos = playerInput.next();
			int newPosIndex = translator.getFieldCoordinate(newPos);
			gameState.newState(new MoveType(newPosIndex, oldPosIndex, false, gameState.getField(oldPosIndex), gameState.getField(newPosIndex)));
			System.out.println();
			System.out.println();
			gameState.printBoard();
			System.out.println();

			TimerController tc = new TimerController(time);
			AlphaBetaController abc = new AlphaBetaController(gameState, isWhite);

			tc.run();
			abc.run();

			while(true){
				System.out.print("");
				if(tc.shutdown){
					abc.interrupt();
					break;
				}
			}

			System.out.println(abc.getBestMove().getPiece() + ": " + abc.getBestMove().getOldPos() + "->" + abc.getBestMove().getNewPos());
			
		}

	}

}

package game_logic;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
			
	final int time = 10000;
	boolean isWhite = false;
	
	GameState gameState = new GameState();
	
	TimerController tc = new TimerController(time);
	AlphaBetaController abc = new AlphaBetaController(gameState, isWhite);
		
	Scanner playerInput = new Scanner(System.in);
	
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
	
//	while(gameRunning) {
//		System.out.println("Player has turn");
//		System.out.println("oldpos:");
//		int oldPos = playerInput.nextInt();
//		System.out.println("newpos:");
//		int newPos = playerInput.nextInt();
//		gameState.newState(new MoveType(newPos, oldPos, false, gameState.getField(oldPos), gameState.getField(newPos)));
//		System.out.println();
//		System.out.println();
//		gameState.printBoard();
//		System.out.println();
//		
//		tc.start();
//		abc.start();
//		
//		System.out.println(abc.getBestMove());
//	}
	
	}

}

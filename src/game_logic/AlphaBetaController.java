package game_logic;

import java.util.ArrayList;

public class AlphaBetaController extends Thread{

	private GameState gameState;
	private boolean isWhite;
	private MoveType bestMove;

	MoveGenerator mg = new MoveGenerator();
	ArrayList<MoveType> validMoves;
	ArrayList<MoveData> firstMoves;
	AlphaBeta ab;

	public AlphaBetaController(GameState gameState, boolean isWhite) {
		this.gameState = gameState;
		this.isWhite = isWhite;
	}

	@Override
	public void run() {

		int i = 1;
		while(true) {
			if (isInterrupted())
				break;
			ab = new AlphaBeta(gameState, i++, isWhite);
			validMoves = mg.getAll(isWhite, gameState);
			firstMoves = new ArrayList<MoveData>();

			System.out.println(validMoves.size());

			for(int j = 0; j < validMoves.size(); j++) {
				if (isInterrupted())
					break;
				gameState.newState(validMoves.get(j));
				firstMoves.add(new MoveData(ab.runAlphaBeta(-100000, 100000, 1, validMoves.get(j)), validMoves.get(j)));
				gameState.oldState(validMoves.get(j));
			}

			double tempMAX = 0;

			System.out.println("firstMoves.size(): " + firstMoves.size());
			//			System.out.println("\n\n");
			//			gameState.printBoard();
			//			System.out.println("\n");

			for(int h = 0; h < firstMoves.size(); h++) {
				if(firstMoves.get(h).getValue() > tempMAX) {
					tempMAX = firstMoves.get(h).getValue();
					bestMove = firstMoves.get(h).getMoveType();
				}
			}

		}

	}

	public MoveType getBestMove() {
		return bestMove;
	}

}

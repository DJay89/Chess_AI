package game_logic;

import java.util.ArrayList;

public class AlphaBetaController implements Runnable{

	private GameState gameState;
	private boolean isWhite;
	private static MoveType bestMove;
	private boolean running;

	public AlphaBetaController(GameState gameState, boolean isWhite) {
		this.gameState = gameState;
		this.isWhite = isWhite;
	}
	
	@Override
	public void run() {

		running = true;
		while(running) {
		int i = 1;
		while(true) {
			AlphaBeta ab = new AlphaBeta(gameState, i++, isWhite);
			MoveGenerator mg = new MoveGenerator();
			ArrayList<MoveType> validMoves = mg.getAll(isWhite, gameState);
			ArrayList<MoveData> firstMoves = new ArrayList<MoveData>();

			for(int j = 0; j < validMoves.size(); j++) {
				firstMoves.add(new MoveData(ab.runAlphaBeta(-100000, 100000, 1, gameState), validMoves.get(j)));
			}

			double tmpMax = 0;

			for(int h = 0; h < firstMoves.size(); h++) {
				if(firstMoves.get(h).getValue() > tmpMax) {
					tmpMax = firstMoves.get(h).getValue();
					bestMove = firstMoves.get(h).getMoveType();
				}
			}
		}
		}
	}

	public static MoveType getBestMove() {
		return bestMove;
	}

	public MoveType terminate() {
		running = false;
		return bestMove;
	}
	
	
}

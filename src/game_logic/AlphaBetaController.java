package game_logic;

import java.util.ArrayList;

public class AlphaBetaController extends Thread{

	private GameState gameState;
	private boolean isWhite;
	static MoveType bestMove;

	public AlphaBetaController(GameState gameState, boolean isWhite) {
		this.gameState = gameState;
		this.isWhite = isWhite;
	}

	@Override
	public void run() {

		int i = 1;
		while(true) {
			AlphaBeta ab = new AlphaBeta(gameState, i++, isWhite);
			MoveGenerator mg = new MoveGenerator();
			ArrayList<MoveType> validMoves = mg.getAll(isWhite, gameState);
			ArrayList<MoveData> firstMoves = new ArrayList<MoveData>();

			for(int j = 0; j < validMoves.size(); j++) {
				firstMoves.add(new MoveData(ab.runAlphaBeta(-100000, 100000, 1, gameState), validMoves.get(j)));
			}

			double tempMAX = 0;

			for(int h = 0; h < firstMoves.size(); h++) {
				if(firstMoves.get(h).getValue() > tempMAX) {
					tempMAX = firstMoves.get(h).getValue();
					bestMove = firstMoves.get(h).getMoveType();
				}
			}

		}

	}


}

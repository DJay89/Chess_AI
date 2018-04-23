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
		int i = 1;
		
		while(running) {
			AlphaBeta ab = new AlphaBeta(gameState, i++, isWhite);
			MoveGenerator mg = new MoveGenerator();
			
			ArrayList<MoveType> validMoves = mg.moveGen(0, gameState);
			ArrayList<Move> moves = new ArrayList<Move>();

			System.out.println("valid moves: " + validMoves.size());
			
			for(int j = 0; j < validMoves.size(); j++) {
				moves.add(new Move(ab.runAlphaBeta(-100000, 100000, 1, gameState), validMoves.get(j)));
			}

			double tmpMax = 0;

			for(int h = 0; h < moves.size(); h++) {
				if(moves.get(h).getValue() > tmpMax) {
					tmpMax = moves.get(h).getValue();
					bestMove = moves.get(h).getMoveType();
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

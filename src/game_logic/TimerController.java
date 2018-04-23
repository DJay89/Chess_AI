package game_logic;

import java.util.Timer;
import java.util.TimerTask;

public class TimerController implements Runnable {
	private static boolean shutdown;
	private boolean isWhite;
	private GameState gameState;
	private int turnTime;
	private boolean timeout;
	private MoveType bestMove;
	
	// Constructor of the object takes the amount of time a turn should be set for
	public TimerController(GameState gameState, boolean isWhite, int turnTime) {
		this.gameState = gameState;
		this.isWhite = isWhite;
		this.turnTime = turnTime;
	}
		
	// Now start the turn
	public void run() {
		this.shutdownTimer();
		
		AlphaBetaController abController = new AlphaBetaController(gameState, isWhite);
		abController.run();
		
		int i = 0;
		while (!shutdown) {
			System.out.println(i);
			i++;
		}
		
		bestMove = abController.terminate();
		timeout();
		
	}
	
	// stop the turn
	private static void shutdown() {
		shutdown = true;
	}
	
	// Wait the specified amount, and tell the thread to stop the turn
	private void shutdownTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				TimerController.shutdown();
			}
		}, this.turnTime);
	}
	
	public boolean timeout() {
		return timeout;
	}

	public MoveType getBestMove() {
		return bestMove;
	}
	
}

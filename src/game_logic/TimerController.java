package game_logic;

import java.util.Timer;
import java.util.TimerTask;

public class TimerController extends Thread {
	public static boolean shutdown;
	private int turnTime;
	
	
	// Constructor of the object takes the amount of time a turn should be set for
	public TimerController(int turnTime) {
		this.turnTime = turnTime;
	}
	
	// Now start the turn
	public void run() {
		shutdown = false;
		this.shutdownTimer();
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
	
//	public static void main(String[] args) {
//		TimerController turn = new TimerController(10000);
//		turn.run();
//	}
}

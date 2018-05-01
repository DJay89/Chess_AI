package algorithm;

import java.util.ArrayList;
import java.util.Iterator;

import game_logic.Evaluation;
import game_logic.GameState;
import game_logic.MoveGenerator;
import game_logic.MoveType;

public class AlphaBeta_I {

	public class MoveValue {

		public double returnValue;
		public MoveType returnMove;

		public MoveValue() {
			returnValue = 0;
			returnMove = null;
		}

		// public MoveValue(double returnValue) {
		// this.returnValue = returnValue;
		// }

		public MoveValue(double returnValue, MoveType returnMove) {
			this.returnValue = returnValue;
			this.returnMove = returnMove;
		}

		@Override
		public String toString() {
			return "MoveValue{" + "move=" + returnMove + ", value=" + returnValue + '}';
		}

	}

	private int[] inputState;
	
	public AlphaBeta_I(int[] inputState) {
		this.inputState = inputState;
	}
	
	private GameState state = new GameState();
	private MoveGenerator generator;

	public MoveValue alphaBeta(double alpha, double beta, int maxDepth, boolean isWhite) {

		for(int i = 0; i < inputState.length ; i++ ) {
			state.setField(i, inputState[i]);
		}
			
		generator = new MoveGenerator();

		Evaluation evaluator = new Evaluation(state, isWhite);

		ArrayList<MoveType> moves = generator.getAll(isWhite, state);
		Iterator<MoveType> movesIterator = moves.iterator();

		double value = 0;
		boolean isMaximizer = isWhite;

		if (maxDepth == 0 /* || state.isGameOver() */) {
			value = evaluator.evaluateState();
			return new MoveValue();
		}

		MoveValue bestMove = null;
		MoveValue returnMove = new MoveValue();

		if (isMaximizer) {
			while (movesIterator.hasNext()) {
				MoveType currentMove = movesIterator.next();

				state.newState(currentMove);
				System.out.println("\n");
				state.printBoard();
				System.out.println("\n");
				
				returnMove = alphaBeta(alpha, beta, maxDepth - 1, opponent(isWhite));
				
				state.oldState(currentMove);

				if ((bestMove == null) || (bestMove.returnValue < returnMove.returnValue)) {
					bestMove = returnMove;
					bestMove.returnMove = currentMove;
				}

				if (returnMove.returnValue > alpha) {
					alpha = returnMove.returnValue;
					bestMove = returnMove;
				}

				if (beta <= alpha) {
					bestMove.returnValue = beta;
					bestMove.returnMove = null;
					return bestMove; // pruning
				}
			}
			return bestMove;
		} else {
			while (movesIterator.hasNext()) {
				MoveType currentMove = movesIterator.next();
				System.out.println(
						currentMove.getPiece() + ": " + currentMove.getOldPos() + "->" + currentMove.getNewPos());

				state.newState(currentMove);
				returnMove = alphaBeta(alpha, beta, maxDepth - 1, opponent(isWhite));
				state.oldState(currentMove);

				if ((bestMove == null) || (bestMove.returnValue > returnMove.returnValue)) {
					bestMove = returnMove;
					bestMove.returnMove = currentMove;
				}

				if (returnMove.returnValue < beta) {
					beta = returnMove.returnValue;
					bestMove = returnMove;
				}

				if (beta <= alpha) {
					bestMove.returnValue = alpha;
					bestMove.returnMove = null;
					return bestMove; // pruning
				}
			}
			return bestMove;
		}
	}

	private boolean opponent(boolean isWhite) {
		if (isWhite)
			return false;
		else
			return true;
	}
}

package game_logic;

import java.util.ArrayList;
import java.util.Iterator;

public class Algorithm {

	GameState state = new GameState();
	MoveGenerator genMoves = new MoveGenerator();
	

	public MoveValue alphaBeta(double alpha, double beta, int maxDepth, boolean isWhite) {
//		if (!canContinue()) {
//			return new MoveValue();
//		}
		
		Evaluation eva = new Evaluation(state, isWhite);
		
		ArrayList<MoveType> moves = genMoves.getAll(isWhite, state);
		Iterator<MoveType> movesIterator = moves.iterator();
		
		double value = 0;
		boolean isMaximizer = isWhite;
		
		if (maxDepth == 0) {
			value = eva.evaluateState();
			return new MoveValue(value);
		}
		
		MoveValue returnMove;
		MoveValue bestMove = null;
		
		if (isMaximizer) {
			while (movesIterator.hasNext()) {
				MoveType currentMove = movesIterator.next();
				state.changeState(currentMove);
				returnMove = alphaBeta(alpha, beta, maxDepth - 1, !isWhite);
				//state.undoLastMove();
				
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
				state.changeState(currentMove);
				returnMove = alphaBeta(alpha, beta, maxDepth - 1, isWhite);
				//state.undoLastMove();
				
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

}

class MoveValue {
	public double returnValue;
	public MoveType returnMove;

	public MoveValue() {
		returnValue = 0;
	}

	public MoveValue(double returnValue) {
		this.returnValue = returnValue;
	}

	public MoveValue(double returnValue, MoveType returnMove) {
		this.returnValue = returnValue;
		this.returnMove = returnMove;
	}
}
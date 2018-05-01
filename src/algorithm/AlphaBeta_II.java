package algorithm;

import java.util.ArrayList;
import java.util.Iterator;

import game_logic.Evaluation;
import game_logic.GameState;
import game_logic.MoveGenerator;
import game_logic.MoveType;

public class AlphaBeta_II {
	private class MoveValue {
	    public MoveType move;
	    public double value;

	    public MoveValue() {
	        move = null;
	        value = 0;
	    }

	    public MoveValue(MoveType move, double value) {
	        this.move = move;
	        this.value = value;
	    }

	    @Override
	    public String toString() {
	        return "MoveValue{" + "move=" + move + ", value=" + value + '}';
	    }

	}
	
	protected MoveValue alphaBeta(double alpha, double beta, int maxDepth, boolean isWhite) {
	    
		GameState state = new GameState();
		MoveGenerator generator = new MoveGenerator();
		Evaluation evaluator = new Evaluation(state, isWhite);
		
	    ArrayList<MoveType> moves = generator.getAll(isWhite, state);
	    Iterator<MoveType> movesIterator = moves.iterator();
	    
	    MoveValue moveValue = new MoveValue();
	    MoveType selectedMove = null;
	    
	    boolean isMaximizer = isWhite;
	    
	    if (maxDepth == 0 /*|| state.isGameOver()*/) {            
	        moveValue.value = evaluator.evaluateState();
	        return moveValue;
	    }
	    
	    while (movesIterator.hasNext()) {
	        MoveType currentMove = movesIterator.next();
	        System.out.println("CurrentMove = " + currentMove.getPiece() + ": " + currentMove.getOldPos() + " -> " + currentMove.getNewPos());
	        
	        state.newState(currentMove);
	        moveValue = alphaBeta(alpha, beta, maxDepth - 1, opponent(isWhite));
	        state.oldState(currentMove);
	        
	        if (isMaximizer) {
	            if (moveValue.value > alpha) {
	                selectedMove = currentMove;
	                alpha = moveValue.value;
	                System.out.println("alpha = " + alpha);
	            }
	        } else {
	            if (moveValue.value < beta) {
	                beta = moveValue.value;
	                selectedMove = currentMove;
	                System.out.println("beta = " + beta);
	            }
	        }
	        if (alpha >= beta) {
	            break;
	        }
	    }
	    return (isMaximizer) ? new MoveValue(selectedMove, alpha) : new MoveValue(selectedMove, beta);
	}

	private boolean opponent(boolean isWhite) {
		if(isWhite) return false;
		else return true;
	}
}

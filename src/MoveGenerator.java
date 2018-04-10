import java.util.ArrayList;

public class MoveGenerator {

	GameState state;

	MoveGenerator(GameState state) {

		this.state = state;

	}

	public ArrayList<MoveType> moveGen(int index) {

		ArrayList<MoveType> result = new ArrayList<MoveType>();

		switch (state.checkField(index)) {

		case (1): // White Pawn

			int[] WPawn = { 16, 15, 17 }; // Possible move paths

			for (int i : WPawn) // For each piece move path

				/*
				 * Add all generated-(with relevant moveGen method)-moves to results. Takes the
				 * move path, the index of the current position on the board, and the results.
				 */

				result.addAll(moveWPawn(i, index, result));

			break;

		case (2): // Black Pawn

			int[] BPawn = { -16, -15, -17 };

			for (int i : BPawn)
				result.addAll(moveBPawn(i, index, result));

			break;

		case (3): // White Knight

			int[] WKnight = { 33, -33, 31, -31, 18, -18, -14, 14 };

			for (int i : WKnight)
				result.addAll(moveWKnight(i, index, result));

			break;

		case (4): // Black Knight

			int[] BKnight = { 33, -33, 31, -31, 18, -18, -14, 14 };

			for (int i : BKnight)
				result.addAll(moveBKnight(i, index, result));

			break;

		case (5): // White Bishop

			int[] WBishop = { 15, 17, -15, -17 };

			for (int i : WBishop)
				result.addAll(moveWBishop(i, index, result));

			break;

		case (6): // Black Bishop

			int[] BBishop = { -15, -17, 15, 17 };

			for (int i : BBishop)
				result.addAll(moveBBishop(i, index, result));

			break;

		case (7): // White Rook

			int[] WRook = { 16, -16, 1, -1 };

			for (int i : WRook)
				result.addAll(moveWRook(i, index, result));

			break;

		case (8): // Black Rook

			int[] BRook = { 16, -16, 1, -1 };

			for (int i : BRook)
				result.addAll(moveBRook(i, index, result));

			break;

		case (9): // White Queen

			int[] WQueen = { 16, -16, 1, -1, 15, 17, -15, -17 };

			for (int i : WQueen)
				result.addAll(moveWQueen(i, index, result));

			break;

		case (10): // Black Queen

			int[] BQueen = { 16, -16, 1, -1, 15, 17, -15, -17 };

			for (int i : BQueen)
				result.addAll(moveBQueen(i, index, result));

			break;

		case (11): // White King

			int[] WKing = { 16, -16, 1, -1, 15, 17, -15, -17 };

			for (int i : WKing)
				result.addAll(moveWKing(i, index, result));

			break;

		case (12): // Black King

			int[] BKing = { 16, -16, 1, -1, 15, 17, -15, -17 };

			for (int i : BKing)
				result.addAll(moveBKing(i, index, result));

			break;

		default:
			break; // When null

		}

		return result;

	}

	private ArrayList<MoveType> moveWPawn(int move, int index, ArrayList<MoveType> result) {

		while (state.outOfBoard(index + move) == false) { // While move is on the board

			if (move == 16) { // If the move path is forward
				if (state.checkField(index + move) == 0) { // The field have to be empty
					result.add(new MoveType(index + move, index, false, 7, state.getField(index))); // Generate move
					moveWPawn(move, index + move, result); // Recursive for all the moves in a given path
				}
			}

			else { // This move path require a enemy piece to be valid
					// When the field is occupied and is an enemy piece
				if (state.checkField(index + move) != 0 && state.checkField(index + move) % 2 == 0) {
					result.add(new MoveType(index + move, index, false, 7, state.getField(index))); // Generate move
					// Not recursive - a piece blocks the path/can't move when conquering a piece
				}
			}
		}
		return result; // Return path moves
	}

	private ArrayList<MoveType> moveBPawn(int move, int index, ArrayList<MoveType> result) {

		while (state.outOfBoard(index + move) == false) {

			if (move == -16) {
				if (state.checkField(index + move) == 0) {
					result.add(new MoveType(index + move, index, false, 7, state.getField(index)));
					moveBPawn(move, index + move, result);
				}
			}

			else {
				if (state.checkField(index + move) != 0 && state.checkField(index + move) % 2 == 0) {
					result.add(new MoveType(index + move, index, false, 7, state.getField(index)));
				}
			}

		}

		return result;
	}

	private ArrayList<MoveType> moveWKnight(int move, int index, ArrayList<MoveType> result) {

		while (state.outOfBoard(index + move) == false) {

			if (state.checkField(index + move) == 0) {
				result.add(new MoveType(index + move, index, false, 7, state.getField(index)));
				moveWKnight(move, index + move, result);
			}

			else if (state.checkField(index + move) != 0 && state.checkField(index + move) % 2 == 0) {
				result.add(new MoveType(index + move, index, false, 7, state.getField(index)));
			}

		}

		return result;

	}

	private ArrayList<MoveType> moveBKnight(int move, int index, ArrayList<MoveType> result) {

		while (state.outOfBoard(index + move) == false) {

			if (state.checkField(index + move) == 0) {
				result.add(new MoveType(index + move, index, false, 7, state.getField(index)));
				moveBKnight(move, index + move, result);
			}

			else if (state.checkField(index + move) != 0 && state.checkField(index + move) % 2 == 0) {
				result.add(new MoveType(index + move, index, false, 7, state.getField(index)));
			}

		}

		return result;

	}

	private ArrayList<MoveType> moveWBishop(int move, int index, ArrayList<MoveType> result) {

		while (state.outOfBoard(index + move) == false) {

			if (state.checkField(index + move) == 0) {
				result.add(new MoveType(index + move, index, false, 7, state.getField(index)));
				moveWBishop(move, index + move, result);
			}

			else if (state.checkField(index + move) != 0 && state.checkField(index + move) % 2 == 0) {
				result.add(new MoveType(index + move, index, false, 7, state.getField(index)));
			}

		}

		return result;

	}

	private ArrayList<MoveType> moveBBishop(int move, int index, ArrayList<MoveType> result) {

		while (state.outOfBoard(index + move) == false) {

			if (state.checkField(index + move) == 0) {
				result.add(new MoveType(index + move, index, false, 7, state.getField(index)));
				moveBBishop(move, index + move, result);
			}

			else if (state.checkField(index + move) != 0 && state.checkField(index + move) % 2 == 0) {
				result.add(new MoveType(index + move, index, false, 7, state.getField(index)));
			}

		}

		return result;

	}

	private ArrayList<MoveType> moveWRook(int move, int index, ArrayList<MoveType> result) {

		while (state.outOfBoard(index + move) == false) {

			if (state.checkField(index + move) == 0) {
				result.add(new MoveType(index + move, index, false, 7, state.getField(index)));
				moveWRook(move, index + move, result);
			}

			if (state.checkField(index + move) != 0 && state.checkField(index + move) % 2 == 0)
				result.add(new MoveType(index + move, index, false, 7, state.getField(index)));
		}

		return result;

	}

	private ArrayList<MoveType> moveBRook(int move, int index, ArrayList<MoveType> result) {

		while (state.outOfBoard(index + move) == false) {

			if (state.checkField(index + move) == 0) {
				result.add(new MoveType(index + move, index, false, 8, state.getField(index)));
				return moveBRook(move, index + move, result);
			}

			if (state.checkField(index + move) != 0 && state.checkField(index + move) % 2 != 0)
				result.add(new MoveType(index + move, index, false, 8, state.getField(index)));
			return result;
		}

		return result;

	}

	private ArrayList<MoveType> moveWQueen(int move, int index, ArrayList<MoveType> result) {

		while (state.outOfBoard(index + move) == false) {

			if (state.checkField(index + move) == 0) {
				result.add(new MoveType(index + move, index, false, 9, state.getField(index)));
				return moveWQueen(move, index + move, result);
			}

			if (state.checkField(index + move) != 0 && state.checkField(index + move) % 2 == 0)
				result.add(new MoveType(index + move, index, false, 9, state.getField(index)));
			return result;
		}

		return result;

	}

	private ArrayList<MoveType> moveBQueen(int move, int index, ArrayList<MoveType> result) {

		while (state.outOfBoard(index + move) == false) {

			if (state.checkField(index + move) == 0) {
				result.add(new MoveType(index + move, index, false, 10, state.getField(index)));
				return moveBQueen(move, index + move, result);
			}

			if (state.checkField(index + move) != 0 && state.checkField(index + move) % 2 != 0)
				result.add(new MoveType(index + move, index, false, 10, state.getField(index)));
			return result;
		}

		return result;

	}

	private ArrayList<MoveType> moveWKing(int move, int index, ArrayList<MoveType> result) {

		while (state.outOfBoard(index + move) == false) {

			if (state.checkField(index + move) == 0) {
				result.add(new MoveType(index + move, index, false, 11, state.getField(index)));
			}

			if (state.checkField(index + move) != 0 && state.checkField(index + move) % 2 == 0)
				result.add(new MoveType(index + move, index, false, 11, state.getField(index)));
		}

		return result;

	}

	private ArrayList<MoveType> moveBKing(int move, int index, ArrayList<MoveType> result) {

		while (state.outOfBoard(index + move) == false) {

			if (state.checkField(index + move) == 0) {
				result.add(new MoveType(index + move, index, false, 7, state.getField(index)));
			}

			if (state.checkField(index + move) != 0 && state.checkField(index + move) % 2 != 0)
				result.add(new MoveType(index + move, index, false, 7, state.getField(index)));
		}

		return result;

	}

}

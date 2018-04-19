package game_logic;

import java.util.ArrayList;

public class MoveGenerator {

	GameState state;

	ArrayList<MoveType> result = new ArrayList<MoveType>();

	MoveGenerator(GameState state) {
		this.state = state;
	}

	public ArrayList<MoveType> moveGen(int index) {

		result.clear();

		int piece = state.checkField(index);
		boolean special = false;

		switch (piece) {

		case (1): // White Pawn

			int[] WPawn = { 16, 15, 17 }; // Possible move paths

			if (index >= 16 && index <= 23) {
				special = true;
			}

			for (int i : WPawn) // For each piece move path

				/*
				 * Add all generated-(with relevant moveGen method)-moves to results. Takes the
				 * move path, the index of the current position on the board, and the results.
				 */

				result = movePawn(piece, special, i, index, result);

			break;

		case (2): // Black Pawn

			int[] BPawn = { -16, -15, -17 };

			if (index >= 96 && index <= 103) {
				special = true;
			}

			for (int i : BPawn)
				result = movePawn(piece, special, i, index, result);

			break;

		case (3): // White Knight

			int[] WKnight = { 33, -33, 31, -31, 18, -18, -14, 14 };

			for (int i : WKnight)
				result = moveKnight(piece, i, index, result);

			break;

		case (4): // Black Knight

			int[] BKnight = { 33, -33, 31, -31, 18, -18, -14, 14 };

			for (int i : BKnight)
				result = moveKnight(piece, i, index, result);

			break;

		case (5): // White Bishop

			int[] WBishop = { 15, 17, -15, -17 };

			for (int i : WBishop)
				result = moveBishop(piece, i, index, result);

			break;

		case (6): // Black Bishop

			int[] BBishop = { -15, -17, 15, 17 };

			for (int i : BBishop)
				result = moveBishop(piece, i, index, result);

			break;

		case (7): // White Rook

			int[] WRook = { 16, -16, 1, -1 };

			for (int i : WRook)
				result = moveRook(piece, i, index, result);

			break;

		case (8): // Black Rook

			int[] BRook = { 16, -16, 1, -1 };

			for (int i : BRook)
				result = moveRook(piece, i, index, result);

			break;

		case (9): // White Queen

			int[] WQueen = { 16, -16, 1, -1, 15, 17, -15, -17 };

			for (int i : WQueen)
				result = moveQueen(piece, i, index, result);

			break;

		case (10): // Black Queen

			int[] BQueen = { 16, -16, 1, -1, 15, 17, -15, -17 };

			for (int i : BQueen)
				result = moveQueen(piece, i, index, result);

			break;

		case (11): // White King

			int[] WKing = { 16, -16, 1, -1, 15, 17, -15, -17 };

			for (int i : WKing)
				result = moveKing(piece, i, index, result);

			break;

		case (12): // Black King

			int[] BKing = { 16, -16, 1, -1, 15, 17, -15, -17 };

			for (int i : BKing)
				result = moveKing(piece, i, index, result);

			break;

		default:
			break; // When null

		}

		return result;

	}

	// pawn moves Both black and white
	private ArrayList<MoveType> movePawn(int piece, boolean special, int move, int index, ArrayList<MoveType> result) {

		// While move is on the board
		if (state.outOfBoard(index + move) == false) {

			// The move path is forward
			if (move == 16 || move == -16) {

				// Eligible for special
				if (special == true && state.checkField(index + (2 * move)) == 0) {
					// Create and add MoveType
					result.add(new MoveType(index + (2 * move), index, special, piece, state.getField(index)));
				}

				// Standard move
				if (state.checkField(index + move) == 0) {
					// Create and add MoveType
					result.add(new MoveType(index + move, index, special, piece, state.getField(index)));
				}
			}

			else { // This move path require a enemy piece to be valid

				// When the field is occupied and is an enemy piece
				if (state.checkField(index + move) != 0 && index % 2 != 0 && state.checkField(index + move) % 2 == 0) {
					result.add(new MoveType(index + move, index, false, piece, state.getField(index)));
				}

				if (state.checkField(index + move) != 0 && index % 2 == 0 && state.checkField(index + move) % 2 != 0) {
					result.add(new MoveType(index + move, index, false, piece, state.getField(index)));
				}

			}
		}

		return result; // Return moves
	}

	// Knight
	private ArrayList<MoveType> moveKnight(int piece, int move, int index, ArrayList<MoveType> result) {

		if (state.outOfBoard(index + move) == false) {

			if (state.checkField(index + move) == 0) {
				result.add(new MoveType(index + move, index, false, piece, state.getField(index)));
			}

			if (state.checkField(index + move) != 0 && index % 2 != 0 && state.checkField(index + move) % 2 == 0) {
				result.add(new MoveType(index + move, index, false, piece, state.getField(index)));
			}

			if (state.checkField(index + move) != 0 && index % 2 == 0 && state.checkField(index + move) % 2 != 0) {
				result.add(new MoveType(index + move, index, false, piece, state.getField(index)));
			}
		}

		return result;
	}

	private ArrayList<MoveType> moveBishop(int piece, int move, int index, ArrayList<MoveType> result) {

		if (state.outOfBoard(index + move) == false) {

			if (state.checkField(index + move) == 0) {
				result.add(new MoveType(index + move, index, false, piece, state.getField(index)));
				moveBishop(piece, move, index + move, result);
			}

			if (state.checkField(index + move) != 0 && index % 2 != 0 && state.checkField(index + move) % 2 == 0) {
				result.add(new MoveType(index + move, index, false, piece, state.getField(index)));
			}

			if (state.checkField(index + move) != 0 && index % 2 == 0 && state.checkField(index + move) % 2 != 0) {
				result.add(new MoveType(index + move, index, false, piece, state.getField(index)));
			}

		}

		return result;

	}

	private ArrayList<MoveType> moveRook(int piece, int move, int index, ArrayList<MoveType> result) {

		if (state.outOfBoard(index + move) == false) {

			if (state.checkField(index + move) == 0) {
				result.add(new MoveType(index + move, index, false, piece, state.getField(index)));
				moveRook(piece, move, index + move, result);
			}

			if (state.checkField(index + move) != 0 && index % 2 != 0 && state.checkField(index + move) % 2 == 0) {
				result.add(new MoveType(index + move, index, false, piece, state.getField(index)));
			}

			if (state.checkField(index + move) != 0 && index % 2 == 0 && state.checkField(index + move) % 2 != 0) {
				result.add(new MoveType(index + move, index, false, piece, state.getField(index)));
			}
		}

		return result;

	}

	private ArrayList<MoveType> moveQueen(int piece, int move, int index, ArrayList<MoveType> result) {

		if (state.outOfBoard(index + move) == false) {

			if (state.checkField(index + move) == 0) {
				result.add(new MoveType(index + move, index, false, piece, state.getField(index)));
				moveQueen(piece, move, index + move, result);
			}

			if (state.checkField(index + move) != 0 && index % 2 != 0 && state.checkField(index + move) % 2 == 0) {
				result.add(new MoveType(index + move, index, false, piece, state.getField(index)));
			}

			if (state.checkField(index + move) != 0 && index % 2 == 0 && state.checkField(index + move) % 2 != 0) {
				result.add(new MoveType(index + move, index, false, piece, state.getField(index)));
			}
		}

		return result;

	}

	private ArrayList<MoveType> moveKing(int piece, int move, int index, ArrayList<MoveType> result) {

		if (state.outOfBoard(index + move) == false) {

			if (state.checkField(index + move) == 0) {
				result.add(new MoveType(index + move, index, false, piece, state.getField(index)));
				moveKing(piece, move, index + move, result);
			}

			if (state.checkField(index + move) != 0 && index % 2 != 0 && state.checkField(index + move) % 2 == 0) {
				result.add(new MoveType(index + move, index, false, piece, state.getField(index)));
			}

			if (state.checkField(index + move) != 0 && index % 2 == 0 && state.checkField(index + move) % 2 != 0) {
				result.add(new MoveType(index + move, index, false, piece, state.getField(index)));
			}
		}

		return result;

	}

	public ArrayList<MoveType> getResult() {
		return result;
	}

	public void setResult(ArrayList<MoveType> result) {
		this.result = result;
	}

}

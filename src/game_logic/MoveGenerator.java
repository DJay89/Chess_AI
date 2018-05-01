package game_logic;

import java.util.ArrayList;

public class MoveGenerator {

	private GameState state;

	private ArrayList<MoveType> result = new ArrayList<MoveType>();

	MoveType test;

	public ArrayList<MoveType> moveGen(int index, GameState state) {

		this.state = state;

		result.clear();

		int piece = state.getField(index);
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
				if (special == true) {
					if (state.getField(index + (move)) == 0) {
						if (state.getField(index + (2 * move)) == 0) {
							// Create and add MoveType
							test = new MoveType(index + (2 * move), index, special, piece,
									state.getField(index + move));
							if (!checkThreat(test, piece))
								result.add(test);
						}
					}
				}

				// Standard move
				if (state.getField(index + move) == 0) {
					// Create and add MoveType
					test = new MoveType(index + move, index, special, piece, state.getField(index + move));
					if (!checkThreat(test, piece))
						result.add(test);
				}
			}

			else { // This move path require a enemy piece to be valid

				// When the field is occupied and is an enemy piece
				if (state.getField(index + move) != 0) {
					if (piece % 2 != 0) {
						if (state.getField(index + move) % 2 == 0) {
							test = new MoveType(index + move, index, false, piece, state.getField(index + move));
							if (!checkThreat(test, piece))
								result.add(test);
						}
					}
				}

				if (state.getField(index + move) != 0) {
					if (piece % 2 == 0) {
						if (state.getField(index + move) % 2 != 0) {
							test = new MoveType(index + move, index, false, piece, state.getField(index + move));
							if (!checkThreat(test, piece))
								result.add(test);
						}
					}
				}

			}
		}

		return result; // Return moves
	}

	// Knight
	private ArrayList<MoveType> moveKnight(int piece, int move, int index, ArrayList<MoveType> result) {

		if (state.outOfBoard(index + move) == false) {

			if (state.getField(index + move) == 0) {
				test = new MoveType(index + move, index, false, piece, state.getField(index + move));
				if (!checkThreat(test, piece))
					result.add(test);
			}

			if (state.getField(index + move) != 0) {
				if (piece % 2 != 0) {
					if (state.getField(index + move) % 2 == 0) {
						test = new MoveType(index + move, index, false, piece, state.getField(index + move));
						if (!checkThreat(test, piece))
							result.add(test);
					}
				}
			}

			if (state.getField(index + move) != 0) {
				if (piece % 2 == 0) {
					if (state.getField(index + move) % 2 != 0) {
						test = new MoveType(index + move, index, false, piece, state.getField(index + move));
						if (!checkThreat(test, piece))
							result.add(test);
					}
				}
			}
		}

		return result;
	}

	private ArrayList<MoveType> moveBishop(int piece, int move, int index, ArrayList<MoveType> result) {

		if (state.outOfBoard(index + move) == false) {

			if (state.getField(index + move) == 0) {
				test = new MoveType(index + move, index, false, piece, state.getField(index + move));
				if (!checkThreat(test, piece))
					result.add(test);
				moveBishop(piece, move, index + move, result);
			}

			if (state.getField(index + move) != 0) {
				if (piece % 2 != 0) {
					if (state.getField(index + move) % 2 == 0) {
						test = new MoveType(index + move, index, false, piece, state.getField(index + move));
						if (!checkThreat(test, piece))
							result.add(test);
					}
				}
			}

			if (state.getField(index + move) != 0) {
				if (piece % 2 == 0) {
					if (state.getField(index + move) % 2 != 0) {
						test = new MoveType(index + move, index, false, piece, state.getField(index + move));
						if (!checkThreat(test, piece))
							result.add(test);
					}
				}
			}
		}

		return result;

	}

	private ArrayList<MoveType> moveRook(int piece, int move, int index, ArrayList<MoveType> result) {

		if (state.outOfBoard(index + move) == false) {

			if (state.getField(index + move) == 0) {
				test = new MoveType(index + move, index, false, piece, state.getField(index + move));
				if (!checkThreat(test, piece))
					result.add(test);
				moveRook(piece, move, index + move, result);
			}

			if (state.getField(index + move) != 0) {
				if (piece % 2 != 0) {
					if (state.getField(index + move) % 2 == 0) {
						test = new MoveType(index + move, index, false, piece, state.getField(index + move));
						if (!checkThreat(test, piece))
							result.add(test);
					}
				}
			}

			if (state.getField(index + move) != 0) {
				if (piece % 2 == 0) {
					if (state.getField(index + move) % 2 != 0) {
						test = new MoveType(index + move, index, false, piece, state.getField(index + move));
						if (!checkThreat(test, piece))
							result.add(test);
					}
				}
			}
		}

		return result;

	}

	private ArrayList<MoveType> moveQueen(int piece, int move, int index, ArrayList<MoveType> result) {

		if (state.outOfBoard(index + move) == false) {

			if (state.getField(index + move) == 0) {
				test = new MoveType(index + move, index, false, piece, state.getField(index + move));
				if (!checkThreat(test, piece))
					result.add(test);
				moveQueen(piece, move, index + move, result);
			}

			if (state.getField(index + move) != 0) {
				if (piece % 2 != 0) {
					if (state.getField(index + move) % 2 == 0) {
						test = new MoveType(index + move, index, false, piece, state.getField(index + move));
						if (!checkThreat(test, piece))
							result.add(test);
					}
				}
			}

			if (state.getField(index + move) != 0) {
				if (piece % 2 == 0) {
					if (state.getField(index + move) % 2 != 0) {
						test = new MoveType(index + move, index, false, piece, state.getField(index + move));
						if (!checkThreat(test, piece))
							result.add(test);
					}
				}
			}
		}

		return result;

	}

	private ArrayList<MoveType> moveKing(int piece, int move, int index, ArrayList<MoveType> result) {

		if (state.outOfBoard(index + move) == false) {

			if (state.getField(index + move) == 0) {
				test = new MoveType(index + move, index, false, piece, state.getField(index + move));
				if (!checkThreat(test, piece))
					result.add(test);
			}

			if (state.getField(index + move) != 0) {
				if (piece % 2 != 0) {
					if (state.getField(index + move) % 2 == 0) {
						test = new MoveType(index + move, index, false, piece, state.getField(index + move));
						if (!checkThreat(test, piece))
							result.add(test);
					}
				}
			}

			if (state.getField(index + move) != 0) {
				if (piece % 2 == 0) {
					if (state.getField(index + move) % 2 != 0) {
						test = new MoveType(index + move, index, false, piece, state.getField(index + move));
						if (!checkThreat(test, piece))
							result.add(test);
					}
				}
			}
		}

		return result;

	}

	public ArrayList<MoveType> getAll(boolean isWhite, GameState state) {

		ArrayList<MoveType> res = new ArrayList<MoveType>();

		if (isWhite) {
			for (int i = 0; i < 120; i++) {
				if (state.getField(i) % 2 == 1) {
					res.addAll(moveGen(i, state));
				}
			}
		}
		if (!isWhite) {
			for (int i = 0; i < 120; i++) {
				if (state.getField(i) % 2 == 0 && state.getField(i) != 0) {
					res.addAll(moveGen(i, state));
				}
			}
		}
		return res;
	}

	public boolean checkThreat(MoveType boardChange, int piece) {

		state.newState(boardChange);

		int[] direction = { 16, -16, 1, -1, 15, 17, -15, -17 };
		int[] Knight = { 33, -33, 31, -31, 18, -18, -14, 14 };

		if (piece % 2 == 1) {

			if (!state.outOfBoard(state.getWKingPos() + 15))
				if (state.getField(state.getWKingPos() + 15) == 2) {
					state.oldState(boardChange);
					return true;
				}

			if (!state.outOfBoard(state.getWKingPos() + 17))
				if (state.getField(state.getWKingPos() + 17) == 2) {
					state.oldState(boardChange);
					return true;
				}
			for (int i : Knight)
				if (!state.outOfBoard(state.getWKingPos() + i))
					if (state.getField(state.getWKingPos() + i) == 4) {
						state.oldState(boardChange);
						return true;
					}
		}

		if (piece % 2 == 0) {

			if (!state.outOfBoard(state.getWKingPos() - 15))
				if (state.getField(state.getWKingPos() - 15) == 1) {
					state.oldState(boardChange);
					return true;
				}

			if (!state.outOfBoard(state.getWKingPos() - 17))
				if (state.getField(state.getWKingPos() - 17) == 1) {
					state.oldState(boardChange);
					return true;
				}
			for (int i : Knight)
				if (!state.outOfBoard(state.getWKingPos() + i))
					if (state.getField(state.getWKingPos() + i) == 3) {
						state.oldState(boardChange);
						return true;
					}
		}

		for (int i : direction) {

			if (piece % 2 == 1)
				if (kingSpotting(state.getWKingPos(), i, piece, state)) {
					state.oldState(boardChange);
					return true;
				}

			if (piece % 2 == 0)
				if (kingSpotting(state.getBKingPos(), i, piece, state)) {
					state.oldState(boardChange);
					return true;
				}
		}

		// for (int i : direction2) {
		//
		// if (piece % 2 == 1)
		// if (kingSpotting(state.getWKingPos(), i, piece, state)) {
		// state.oldState(boardChange);
		// return true;
		// }
		// if (piece % 2 == 0)
		// if (kingSpotting(state.getBKingPos(), i, piece, state)) {
		// state.oldState(boardChange);
		// return true;
		// }
		// }

		state.oldState(boardChange);
		return false;
	}

	private boolean kingSpotting(int index, int move, int piece, GameState state) {
		if (!state.outOfBoard(index + move)) {
			if (state.getField(index + move) == 0)
				return kingSpotting(index + move, move, piece, state);

			if (state.getField(index + move) % 2 != piece % 2) {
				switch (state.getField(index + move)) {

				case (9):	case (10):
					if (move == 16 || move == -16 || move == 1 || move == -1 || move == 15 || move == -15 || move == 17	|| move == -17)
						return true;
				case (7):	case (8):
					if (move == 16 || move == -16 || move == 1 || move == -1)
						return true;
				case (5):	case (6):
					if (move == 15 || move == -15 || move == 17 || move == -17)
						return true;
				}

			}

		}

		return false;
	}

	public ArrayList<MoveType> getResult() {
		return result;
	}

	public void setResult(ArrayList<MoveType> result) {
		this.result = result;
	}

}

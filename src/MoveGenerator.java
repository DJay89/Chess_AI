import java.util.ArrayList;

public class MoveGenerator {

	GameState state;

	MoveGenerator(GameState state){

		this.state = state;

	}

	public ArrayList<MoveType> moveGen(int index){

		ArrayList<MoveType> result = new ArrayList<MoveType>();

		switch(state.checkField(index)){

		case (1) :	//White Pawn 

			break;	

		case (2) : //Black Pawn

			break;

		case (3) : //White Knight

			break;

		case (4) : //Black Knight

			break;

		case (5) : //White Bishop

			break;

		case (6) : //Black Bishop

			break;

		case (7) : //White Rook

			int[] WRook = {16, -16, 1, -1};

		for (int i : WRook)
			result.addAll(moveWRook(i, index, result));	

		break;

		case (8) : //Black Rook

			int[] BRook = {16, -16, 1, -1};

		for (int i : BRook)
			result.addAll(moveBRook(i, index, result));	


		break;

		case (9) : //White Queen

			int[] WQueen = {16, -16, 1, -1, 15, 17, -15, -17};

		for (int i : WQueen)
			result.addAll(moveWQueen(i, index, result));	

		break;

		case (10) : //Black Queen

			int[] BQueen = {16, -16, 1, -1, 15, 17, -15, -17};

		for (int i : BQueen)
			result.addAll(moveBQueen(i, index, result));	


		break;

		case (11) : //White King

			int[] WKing = {16, -16, 1, -1, 15, 17, -15, -17};

		for (int i : WKing)
			result.addAll(moveWKing(i, index, result));	


		break;

		case (12) : //Black King

			int[] BKing = {16, -16, 1, -1, 15, 17, -15, -17};

		for (int i : BKing)
			result.addAll(moveBKing(i, index, result));	


		break;

		default : break; //When null

		}

		return result;

	}

	private ArrayList<MoveType> moveWRook(int move, int index, ArrayList<MoveType> result){

		while(state.outOfBoard(index+move) == false){

			if (state.checkField(index+move) == 0){
				result.add(new MoveType(index+move, index, false, 7, state.getField(index)));
				moveWRook(move, index+move, result);
			}

			if (state.checkField(index+move) != 0 && state.checkField(index+move)%2 == 0)
				result.add(new MoveType(index+move, index, false, 7, state.getField(index)));
		}

		return result;

	}

	private ArrayList<MoveType> moveBRook(int move, int index, ArrayList<MoveType> result){

		while(state.outOfBoard(index+move) == false){

			if (state.checkField(index+move) == 0){
				result.add(new MoveType(index+move, index, false, 8, state.getField(index)));
				return moveBRook(move, index+move, result);
			}

			if (state.checkField(index+move) != 0 && state.checkField(index+move)%2 != 0)
				result.add(new MoveType(index+move, index, false, 8, state.getField(index)));
				return result;
		}


		return result;

	}

	private ArrayList<MoveType> moveWQueen(int move, int index, ArrayList<MoveType> result){

		while(state.outOfBoard(index+move) == false){

			if (state.checkField(index+move) == 0){
				result.add(new MoveType(index+move, index, false, 9, state.getField(index)));
				return moveWQueen(move, index+move, result);
			}

			if (state.checkField(index+move) != 0 && state.checkField(index+move)%2 == 0)
				result.add(new MoveType(index+move, index, false, 9, state.getField(index)));
				return result;
		}

		return result;

	}

	private ArrayList<MoveType> moveBQueen(int move, int index, ArrayList<MoveType> result){

		while(state.outOfBoard(index+move) == false){

			if (state.checkField(index+move) == 0){
				result.add(new MoveType(index+move, index, false, 10, state.getField(index)));
				return moveBQueen(move, index+move, result);
			}

			if (state.checkField(index+move) != 0 && state.checkField(index+move)%2 != 0)
				result.add(new MoveType(index+move, index, false, 10, state.getField(index)));
			return result;
		}


		return result;

	}

	private ArrayList<MoveType> moveWKing(int move, int index, ArrayList<MoveType> result){

		while(state.outOfBoard(index+move) == false){

			if (state.checkField(index+move) == 0){
				result.add(new MoveType(index+move, index, false, 11, state.getField(index)));
			}

			if (state.checkField(index+move) != 0 && state.checkField(index+move)%2 == 0)
				result.add(new MoveType(index+move, index, false, 11, state.getField(index)));
		}

		return result;

	}

	private ArrayList<MoveType> moveBKing(int move, int index, ArrayList<MoveType> result){

		while(state.outOfBoard(index+move) == false){

			if (state.checkField(index+move) == 0){
				result.add(new MoveType(index+move, index, false, 7, state.getField(index)));
			}

			if (state.checkField(index+move) != 0 && state.checkField(index+move)%2 != 0)
				result.add(new MoveType(index+move, index, false, 7, state.getField(index)));
		}


		return result;

	}

}

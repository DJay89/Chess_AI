import java.util.ArrayList;

public class MoveGenerator {
	
	GameState state;
	
	MoveGenerator(GameState state){
		
		this.state = state;
		
	}
	
	public ArrayList<MoveType> moveGen(int index){
		
		ArrayList result = new ArrayList();
		
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
			
			break;
		
		case (8) : //Black Rook
			
			break;
		
		case (9) : //White Queen
			
			break;
		
		case (10) : //Black Queen
			
			break;
		
		case (11) : //White King
			
			break;
		
		case (12) : //Black King
			
			break;
		
		default : break;
		
		}
		
		return result;
		
	}

}

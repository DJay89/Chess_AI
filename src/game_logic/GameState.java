package game_logic;
public class GameState {

	/*				
	 * 				white	|	black
	 * King			11		|	12
	 * Queen		9		|	10
	 * Rook/tower	7		|	8
	 * Bishop		5		|	6
	 * Knight		3		|	4
	 * Pawn			1		|	2
	 */


	private int[] currentState;
	private int WKing, BKing;

	//default constructor for generating a GameState in the standard chess starting position
	public GameState() {
		currentState = new int[256];

		//filling the board with blanks
		for(int i = 0; i < 256; i++) {
			currentState[i]=0;
		}

		//filling a2-h2 with white pawns
		for(int i = 16; i < 24; i++) {
			currentState[i]=1;
		}

		//filling a7-h7 with black pawns
		for(int i = 96; i < 104; i++) {
			currentState[i]=2;
		}

		//white rooks/towers
		currentState[0]=7; currentState[7]=7;
		//white knights
		currentState[1]=3; currentState[6]=3;
		//white bishops
		currentState[2]=5; currentState[5]=5;
		//white king
		currentState[4]=11;
		WKing = 4;
		//white queen
		currentState[3]=9;

		//black rooks/towers
		currentState[112]=8; currentState[119]=8;
		//black knights
		currentState[113]=4; currentState[118]=4;
		//black bishops
		currentState[114]=6; currentState[117]=6;
		//black king
		currentState[116]=12;
		BKing = 116;
		//black queen
		currentState[115]=10;

		//		//Testing that the starting gamestate is correct 
		//		for(int i = 0; i < currentState.length; i++) {
		//			if(i%16 == 0) {
		//				System.out.println();
		//			}
		//			System.out.print(" " + currentState[i]);
		//		}
	}
	
	public void changeState(MoveType move){
		
		if(move.getPiece() == 11)
			WKing = move.newPos;
		
		if(move.getPiece() == 12)
			BKing = move.getNewPos();
		
		currentState[move.getOldPos()] = 0;
		currentState[move.getNewPos()] = move.getPiece();
		
	}
	
	public GameState newState(GameState state, MoveType move){
		
		if(move.getPiece() == 11)
			state.setWKingPos(move.newPos);
		
		if(move.getPiece() == 12)
			state.setBKingPos(move.newPos); 
		
		state.currentState[move.getOldPos()] = 0;
		state.currentState[move.getNewPos()] = move.getPiece();
		
		return state;
		
	}
	
	public int getWKingPos(){
		return WKing;
	}
	
	public void setWKingPos(int pos){
		this.WKing = pos;
	}
	
	public int getBKingPos(){
		return BKing;
	}
	
	public void setBKingPos(int pos){
		this.BKing = pos;
	}

	public int[] getCurrentState() {
		return currentState;
	}

	public int getField(int index) {
		return currentState[index];
	}
	
	public void setField(int index, int piece){
		this.currentState[index] = piece;
	}

	public int getPieceType(int x, int y) {
		return currentState[y*16+x];
	}

	public int getX(int i) {
		return i%16;
	}

	public int getY(int i) {
		return i/16;
	}

	public boolean outOfBoard(int i) {
		if(((i >> 3) & 1) == 1 || ((i >> 7) & 1) == 1) {
			return true;
		} else {
			return false;
		}
	}

	public int checkField(int index){
		return currentState[index];
	}

	public void printBoard(){

		int repeat = 0;
		int bonus = 0;

		while(repeat < 8){
			
			System.out.print("[ " + (7-repeat) + "]   ");

			for(int i = 112-bonus; i < 120-bonus; i++){
				if (currentState[i] < 10)
					System.out.print("[ " + currentState[i] + "]");
				if (currentState[i] > 9)
					System.out.print("[" + currentState[i] + "]");
			}
			
			System.out.print("\n");
			if(repeat == 7){	
				System.out.print("\n       ");
				for(int i = 0; i < 8; i++){	
					System.out.print("[ " + i + "]");	
				}	
			}
			repeat++;
			bonus += 16;
		}
	}

}

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
	
	
	int[] currentState;
	
	//default constructor for generating a GameState in the standart chess starting position
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
		//black queen
		currentState[115]=10;
		
		/* 
		 * Testing that the starting gamestate is correct
		 *  
		for(int i = 112; i < 120; i++) {
			System.out.print(" " + currentState[i]);
		}
		System.out.println();
		for(int i = 96; i < 104; i++) {
			System.out.print(" " + currentState[i]);
		}
		System.out.println();
		for(int i = 16; i < 24; i++) {
			System.out.print(" " + currentState[i]);
		}
		System.out.println();
		for(int i = 0; i < 8; i++) {
			System.out.print(" " + currentState[i]);
		} */
	}
	
}


public class HelloWorld {

	public static void main(String[] args) {
		
		System.out.println("Hallo");

		GameState myState = new GameState();
		myState.printBoard();
		
		//MoveGenerator mg = new MoveGenerator(myState);
		//mg.moveGen(20);
		//System.out.println(mg.getResult());

	}

}

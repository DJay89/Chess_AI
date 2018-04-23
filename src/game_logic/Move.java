package game_logic;

public class Move {

	private double value;
	private MoveType moveType;
	
	public Move(double value, MoveType moveType) {
		this.value = value;
		this.moveType = moveType;
	}

	public double getValue() {
		return value;
	}

	public MoveType getMoveType() {
		return moveType;
	}
}

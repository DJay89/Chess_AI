package game_logic;

public class MoveData {

	private double value;
	private MoveType moveType;
	
	public MoveData(double value, MoveType moveType) {
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

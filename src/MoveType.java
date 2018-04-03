
public class MoveType {

	int newPos;
	int oldPos;
	boolean special;
	int piece;
	int content;

	MoveType(int newPos, int oldPos, boolean special, int piece, int content){

		this.newPos = newPos;
		this.oldPos = oldPos;
		this.special = special;
		this.piece = piece;
		this.content = content;

	}


	public int getNewPos() {
		return newPos;
	}

	public void setNewPos(int newPos) {
		this.newPos = newPos;
	}

	public int getOldPos() {
		return oldPos;
	}

	public void setOldPos(int oldPos) {
		this.oldPos = oldPos;
	}

	public boolean isSpecial() {
		return special;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}

	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
	}

	public int getContent() {
		return content;
	}

	public void setContent(int content) {
		this.content = content;
	}

}

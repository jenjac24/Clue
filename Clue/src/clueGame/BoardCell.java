package clueGame;

public abstract class BoardCell {
	
	protected int row;
	protected int col;
	
	public boolean isWalkway(){
		return false;
	}
	
	public boolean isRoom(){
		return false;
	}
	
	public boolean isDoorway(){
		return false;
	}

	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", col=" + col + "]";
	}
	
	
	
	//adding this method later
	//abstract public void draw();

}

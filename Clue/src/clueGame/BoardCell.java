package clueGame;

public abstract class BoardCell {
	
	private int row;
	private int col;
	
	public boolean isWalkway(){
		return false;
	}
	
	public boolean isRoom(){
		return false;
	}
	
	public boolean isDoorway(){
		return false;
	}
	
	//adding this method later
	//abstract public void draw();

}

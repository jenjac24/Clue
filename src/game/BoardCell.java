package game;

abstract public class BoardCell {
	int rows, columns;
	public Boolean isWalkway(){
		return false;
	}
	public Boolean isRoom(){
		return false;
	}
	public Boolean isDoorway(){
		return false;
	}
	public void draw(){
		
	}
}

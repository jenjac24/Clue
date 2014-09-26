package game;

 public class BoardCell {
	int row, column;
	
	public BoardCell(){
		
	}
	
	public BoardCell(int row, int column){
		this.row = row;
		this.column = column;
	}
	
	public int row(){
		return row;
	}
	public int column(){
		return column;
	}
	
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

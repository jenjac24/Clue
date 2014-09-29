package clueGame;

public class WalkwayCell extends BoardCell {

	public WalkwayCell(int rows, int cols){
		row = rows;
		col = cols;
	}
	
	@Override
	public boolean isWalkway(){
		return true;
	}
	
	/*@Override
	public void draw() {
		// TODO Auto-generated method stub

	}*/

}

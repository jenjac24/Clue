package clueGame;

public class WalkwayCell extends BoardCell {

	/*@Override
	public String toString() {
		super.toString();
		return "WalkwayCell []";
	}
*/
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
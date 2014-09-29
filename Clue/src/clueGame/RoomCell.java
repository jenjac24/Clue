package clueGame;

public class RoomCell extends BoardCell {

	public enum DoorDirection {UP, DOWN, LEFT, RIGHT, NONE};
	
	private DoorDirection doorDirection;
	private char roomInitial;
	private boolean isDoor;
	
	public RoomCell(int rows, int cols, char c, DoorDirection dir, boolean door){
		row = rows;
		col = cols;
		doorDirection = dir;
		roomInitial = c;
		isDoor = door;
	}
	
	
	@Override
	public boolean isRoom(){
		return true;
	}
	
	public DoorDirection getDoorDirection(){
		return doorDirection;
	}
	
	public char getInitial(){
		return roomInitial;
	}


	@Override
	public String toString() {
		super.toString();
		return "RoomCell [doorDirection=" + doorDirection + ", roomInitial="
				+ roomInitial + ", isDoor=" + isDoor + "]";
	}
	
	/*@Override
	public void draw() {
		// TODO Auto-generated method stub

	}
	*/

}

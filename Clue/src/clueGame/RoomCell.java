package clueGame;

public class RoomCell extends BoardCell {

	public enum DoorDirection {UP, DOWN, LEFT, RIGHT, NONE};
	
	private DoorDirection doorDirection;
	private char roomInitial;
	private boolean isDoor;
	
<<<<<<< HEAD
	public RoomCell(char c, DoorDirection dir, boolean door){
=======
	public RoomCell(int rows, int cols, char c, DoorDirection dir, boolean door){
		row = rows;
		col = cols;
>>>>>>> d5740a5824928b931c0a25a5029124877cc9db7e
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

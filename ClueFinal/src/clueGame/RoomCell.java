package clueGame;

public class RoomCell extends BoardCell {

	public enum DoorDirection {UP, DOWN, LEFT, RIGHT, NONE};

	private DoorDirection doorDirection;
	private char roomInitial;
	private boolean isDoor;
	
	public DoorDirection determineDirection(char c){
		//System.out.println(c + " = c");
		if(c=='U') return DoorDirection.UP;
		else if(c=='D') return DoorDirection.DOWN;
		else if(c=='R') return DoorDirection.RIGHT;
		else if(c=='L') return DoorDirection.LEFT;
		else if(c=='N'){ 
			isDoor=false;
			return DoorDirection.NONE;
		}
		else return DoorDirection.NONE;
	}

	public RoomCell(int rows, int cols, String input){
		row = rows;
		col = cols;
		roomInitial = input.charAt(0);
		isDoor = false;
		if(input.length()>1){
			isDoor = true;
			doorDirection = determineDirection(input.charAt(1));
			//System.out.println(this);
		}
	}

	@Override
	public boolean isRoom(){
		return true;
	}
	
	@Override
	public boolean isDoorway(){
		return isDoor;
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
		return "RoomCell [row = " + row + " col = " + col + " doorDirection=" + doorDirection + ", roomInitial="
		+ roomInitial + ", isDoor=" + isDoor + "]";
	}
	

	/*@Override
	public void draw() {
		// TODO Auto-generated method stub

	}
	 */

}

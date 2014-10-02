package game;

public class RoomCell extends BoardCell{
	public enum DoorDirection{UP,DOWN,LEFT,RIGHT,NONE};
	private DoorDirection doorDirection;
	private char roomInitial;
	
	RoomCell(int row, int column, char room, DoorDirection d){
		super(row,column);
		roomInitial = room;
		doorDirection = d;
		}
	
	public RoomCell() {

	}

	@Override
	public Boolean isRoom(){
		return true;
	}
	
	@Override
	public void draw(){
		
	}
	
	@Override
	public Boolean isDoorway(){
		if (doorDirection.equals(DoorDirection.NONE)){
			return false;
		}
		return true;
	}
	@Override
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	public char getInitial() {
		return roomInitial;
	}

	public void setRoomInitial(char roomInitial) {
		this.roomInitial = roomInitial;
	}

}

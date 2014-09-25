package game;

public class RoomCell extends BoardCell{
	public enum DoorDirection{UP,DOWN,LEFT,RIGHT,NONE};
	private DoorDirection doorDirection;
	private char roomInitial;
	
	@Override
	public Boolean isRoom(){
		return true;
	}
	
	@Override
	public void draw(){
		
	}

	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

}

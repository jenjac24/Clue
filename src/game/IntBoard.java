package game;

import game.RoomCell;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class IntBoard {
	
	private int length;
	private int width;
	
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	
	private BoardCell[][] layout;
	
	public IntBoard(int length, int width, BoardCell[][] l){
		this.length = length;
		this.width = width;
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		layout = l;
	}
	
	public void calcTargets(BoardCell thisCell, int numberOfMoves){
		targets.clear();
		visited.clear();
		visited.add(thisCell);
		doCalc(thisCell, numberOfMoves, thisCell);
	}
	
	
	public void doCalc(BoardCell thisCell, int numberOfMoves, BoardCell firstCell){ //finds possible moves
		visited.add(firstCell);
		LinkedList<BoardCell> adjCell = null; 
		adjCell = getAdjList(thisCell); //may need a try catch?
		for(BoardCell c : adjCell){
			if(c.isDoorway()) {
				targets.add(c);
				visited.add(c);
			}
			if(numberOfMoves == 1) {
				targets.add(c); //if last move used, saves spot as possible location
				visited.clear();
				
			}
			else{
				visited.add(thisCell);
				doCalc(c, numberOfMoves - 1, firstCell); //spends one move to an adjacent cell
				
			}
		}
	}
	
	public Set<BoardCell> getTargets(){
		
		return targets;
	}
	
	public LinkedList<BoardCell> getAdjList(BoardCell cell){
		Boolean notDoor = true;
		LinkedList<BoardCell> list = new LinkedList<BoardCell>();
		int row = cell.row();
		int column = cell.column();
		if(layout[row][column].isRoom()){
			if(layout[row][column].isDoorway())
				notDoor = false;
			else{
				list.clear();
				return list;
			}
		}
		
		if(row != 0 && !visited.contains(layout[row-1][column])){
			BoardCell topCell = layout[row-1][column];
			if(topCell.isDoorway() && notDoor){
				if(topCell.getDoorDirection() == RoomCell.DoorDirection.DOWN)
				list.add(topCell);
			}else if(!notDoor){
				if(layout[row][column].getDoorDirection() == RoomCell.DoorDirection.UP)
					list.add(topCell);	
			}else if(!topCell.isRoom())
				list.add(topCell);
		}
		if(row < (length - 1) && !visited.contains(layout[row+1][column])){
			BoardCell botomCell = layout[row+1][column];
			if(botomCell.isDoorway() && notDoor){
				if(botomCell.getDoorDirection() == RoomCell.DoorDirection.UP)
				list.add(botomCell);
			}else if(!notDoor){
				if(layout[row][column].getDoorDirection() == RoomCell.DoorDirection.DOWN)
					list.add(botomCell);
			}else if(!botomCell.isRoom())
				list.add(botomCell);
		}
		if(column != 0 && !visited.contains(layout[row][column-1])){
			BoardCell leftCell = layout[row][column-1];
			if(leftCell.isDoorway() && notDoor){
				if(leftCell.getDoorDirection() == RoomCell.DoorDirection.RIGHT)
				list.add(leftCell);
			}else if(!notDoor){
				if(layout[row][column].getDoorDirection() == RoomCell.DoorDirection.LEFT)
					list.add(leftCell);
			}else if(!leftCell.isRoom())
				list.add(leftCell);
		}
		if(column < (width - 1) && !visited.contains(layout[row][column+1])){
			BoardCell rightCell = layout[row][column+1];
			if(rightCell.isDoorway() && notDoor){
				if(rightCell.getDoorDirection() == RoomCell.DoorDirection.LEFT)
				list.add(rightCell);
			}else if(!notDoor){
				if(layout[row][column].getDoorDirection() == RoomCell.DoorDirection.RIGHT)
					list.add(rightCell);
			}else if(!rightCell.isRoom())
				list.add(rightCell);
		}
		
		return list;
	}

}

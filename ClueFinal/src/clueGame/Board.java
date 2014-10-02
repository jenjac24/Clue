package clueGame;



import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import clueGame.RoomCell.DoorDirection;

public class Board {

	private BoardCell[][] grid;
	private Map<Character, String> rooms;
	private int numRows;
	private int numColumns;
	private String layoutFile;
	private LinkedList<BoardCell> adjList;
	private Map<BoardCell, LinkedList<BoardCell>> adjCellsMap; 
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;

	

	public Board(String layoutFileName, Map<Character, String> room){
		rooms = room;
		layoutFile = layoutFileName;
		adjList = new LinkedList<BoardCell>();
		adjCellsMap = new HashMap<BoardCell, LinkedList<BoardCell>>();
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		
	}

	public Board loadBoardConfig() throws BadConfigFormatException{
		int rows = 0;
		int cols = 0;
		int row = 0;
		String roomCode;
		String line;
		String comma = ",";
		String[] arr;

		char first, second;
		FileReader read, read2;
		try {
			read = new FileReader(layoutFile);
			Scanner scan = new Scanner(read);
			while(scan.hasNextLine()){
				rows++;
				line = scan.nextLine();
				arr = line.split(",");
				if(rows==1) cols = arr.length;
				if(rows!=1){
					if(cols!=arr.length){
						throw new BadConfigFormatException(arr.length);
					}
				}
				cols = arr.length;
			}
			numRows = rows;
			numColumns = cols;
			//if numRows doesn't equal 23 or 22, throw an exception, same for numColumns
			if(numRows!=22) throw new BadConfigFormatException(numRows);
			if(numColumns!=23) throw new BadConfigFormatException(numColumns);
			grid = new BoardCell[numRows][numColumns];
			read2 = new FileReader(layoutFile);
			Scanner scan2 = new Scanner(read2);
			String[][] mapCells = new String[numRows][numColumns];

			while(scan2.hasNextLine()){
				line = scan2.nextLine();
				//now i split the string with commas and then put them into an array
				//which will essentially be the same as the excel file
				arr = line.split(",");
				mapCells[row] = arr;
				row++;
			}

			for(int i = 0; i<mapCells.length; i++){
				for(int j=0; j<mapCells[0].length; j++){
					roomCode = mapCells[i][j];
					if(roomCode.charAt(0) =='W'){
						//fill grid with walkway cell
						fillGrid(i,j, roomCode, false, grid);
					}
					else if(rooms.containsKey(roomCode.charAt(0))){
						//fill grid with room cell
						fillGrid(i,j,roomCode,true, grid);
					}
					else {
						throw new BadConfigFormatException(roomCode.charAt(0));
					}
				}
			}

			//throw an error if not all of the lengths of the second arrays are the same
			for(int i =0; i<mapCells.length; i++){
				if(mapCells[0].length!=numColumns) throw new BadConfigFormatException(mapCells.length);
			}
			scan2.close();
			scan. close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BadConfigFormatException(5);
		}
		return this;
	}

	public void fillGrid(int i, int j, String roomCode, boolean room, BoardCell[][] tempGrid){
		if(room){
			tempGrid[i][j] = new RoomCell(i, j, roomCode);
		}
		else tempGrid[i][j] = new WalkwayCell(i,j);
	}

	public BoardCell getCellAt(int row, int col){
		return grid[row][col];
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public RoomCell getRoomCellAt(int row, int col){
		if(row <=numRows && col<=numColumns){
			if(grid[row][col].isRoom()){
				return (RoomCell) grid[row][col];

			}
		}
		else {
			return new RoomCell(0,0,"");
		}
		return new RoomCell(0,0,"");

	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}
	public boolean canEnter(RoomCell room, BoardCell current){
		int rowCurrent = current.getRow();
		int colCurrent = current.getCol();
		int rowRoom = room.getRow();
		int colRoom = room.getCol();

		RoomCell.DoorDirection direction = room.getDoorDirection();
		switch(direction){
		case DOWN: rowRoom++;
		if(rowRoom==rowCurrent&&colCurrent==colRoom) return true;
		break;
		case UP: rowRoom--;
		if(rowRoom==rowCurrent&&colCurrent==colRoom) return true;
		break;
		case LEFT: colRoom--;
		if(rowRoom==rowCurrent&&colCurrent==colRoom) return true;
		break;
		case RIGHT: colRoom++;
		if(rowRoom==rowCurrent&&colCurrent==colRoom) return true;
		break;
		}
		return false;
	}

	public boolean inBoard(int row, int col) {
		if(row >= 0 && row < numRows) {
			if(col >= 0 && col < numColumns) {
				return true;
			}

		}
		return false;

	}
	public void leaveDoorDirection(RoomCell doorway) {
		RoomCell.DoorDirection direction = doorway.getDoorDirection();
		int rowDoorway = doorway.getRow();
		int colDoorway = doorway.getCol();
		switch(direction){
		case DOWN: 
			if(inBoard(rowDoorway+1, colDoorway)) {
				adjList.add(getCellAt(rowDoorway+1,colDoorway));
			}
			break;
		case UP: 
			if(inBoard(rowDoorway-1, colDoorway)) {
				adjList.add(getCellAt(rowDoorway-1,colDoorway));
			}
			break;
		case LEFT: 
			if(inBoard(rowDoorway, colDoorway-1)) {
				adjList.add(getCellAt(rowDoorway,colDoorway-1));
			}
			break;
		case RIGHT:
			if(inBoard(rowDoorway, colDoorway+1)) {
				adjList.add(getCellAt(rowDoorway,colDoorway+1));
			}
			break;
		}
	}
	public void calcAdjacencies() {
		//if you are in a roomcell that is a door then the adjacencie is only the direction that you can exit the door
		BoardCell currentLocation;//= getCellAt(0,0);
		for(int i = 0; i < numRows; i++) {
			for(int j =0; j < numColumns; j++) {
				currentLocation = getCellAt(i,j);
				adjList = new LinkedList<BoardCell>();
				//check to see if current location is a roomcell or a walkway
				if(currentLocation.isRoom() && currentLocation.isDoorway()) {
					RoomCell currentRoomCell = getRoomCellAt(i,j);
					//if roomCell is a doorway, check direction that you can exit, else do nothing
					leaveDoorDirection(currentRoomCell);

				}
				else if (currentLocation.isWalkway()) {  //currentLocation is a walkway
					//check each of surrounding 4 cells, check to see if they are in map, check to see if they are a doorway or walkway
					int currentRow = currentLocation.getRow();
					int currentCol = currentLocation.getCol();
					if(inBoard(currentRow+1, currentCol)) { //DOWN
						BoardCell temp = getCellAt(currentRow+1, currentCol);
						if(temp.isDoorway()) {
							RoomCell room = getRoomCellAt(currentRow+1,currentCol);
							if(canEnter(room,currentLocation)) {
								adjList.add(temp);	
							}

						}
						else if(temp.isWalkway()) {
							adjList.add(temp);
						}
						//check if its a door
						//if it is a door, see if we can enter it, if yes add t oadj list
						//check if its a walkway, add to adj list
					}
					if(inBoard(currentRow-1, currentCol)) { //UP
						BoardCell temp = getCellAt(currentRow-1, currentCol);
						if(temp.isDoorway()) {
							RoomCell room = getRoomCellAt(currentRow-1,currentCol);
							if(canEnter(room,currentLocation)) {
								adjList.add(temp);	
							}

						}
						else if(temp.isWalkway()) {
							adjList.add(temp);

						}

					}
					if(inBoard(currentRow, currentCol+1)) { //RIGHT
						BoardCell temp = getCellAt(currentRow, currentCol+1);
						if(temp.isDoorway()) {
							RoomCell room = getRoomCellAt(currentRow,currentCol+1);
							if(canEnter(room,currentLocation)) {
								adjList.add(temp);	
							}

						}
						else if(temp.isWalkway()) {
							adjList.add(temp);
						}
					}
					if(inBoard(currentRow, currentCol-1)) { //LEFT
						BoardCell temp = getCellAt(currentRow, currentCol-1);
						if(temp.isDoorway()) {
							RoomCell room = getRoomCellAt(currentRow,currentCol-1);
							if(canEnter(room,currentLocation)) {
								adjList.add(temp);	
							}

						}
						else if(temp.isWalkway()) {
							adjList.add(temp);
						}
					}

				}
				adjCellsMap.put(currentLocation, adjList);
			}
		}//end of double for loops

	}
	public LinkedList<BoardCell> getAdjList(int row, int col){
		BoardCell cell = getCellAt(row, col);
		calcAdjacencies();
		return adjCellsMap.get(cell); 
	}

	public Set<BoardCell> getTargets(){
		return targets;
	}

	public void calcTargets(int row, int col, int numSteps){
		BoardCell cell = getCellAt(row, col);
		targets.clear();
		fillTargets(cell, numSteps);
	}
		
	public void fillTargets(BoardCell cell, int numSteps){	
		//calculate the number of targets, fill Set<BoardCell> with targets
		//calculate the number of targets, fill Set<BoardCell> with targets
		cell = getCellAt(cell.getRow(), cell.getCol());
		visited.add(cell);
		LinkedList<BoardCell> adjCells = new LinkedList<BoardCell>(adjCellsMap.get(cell));
		//make sure you don't check a cell you have already visited
		for(BoardCell ce: visited){
			if(adjCells.contains(getCellAt(ce.getRow(), ce.getCol())))
				adjCells.remove(getCellAt(ce.getRow(), ce.getCol()));
		}
		for(BoardCell c: adjCells){
			c = getCellAt(c.getRow(), c.getCol());
			visited.add(c);
			if(numSteps == 1){
				targets.add(c);
			}
			else if (numSteps!=1&&c.isDoorway()){
				RoomCell door = getRoomCellAt(c.getRow(), c.getCol());
				//if you can enter the door, then add it to your targets list
				if(canEnter(door, cell)){
					targets.add(c);
				}
			}
			else 
				fillTargets(getCellAt(c.getRow(), c.getCol()), numSteps-1);
			visited.remove(c);
		}
	}
}









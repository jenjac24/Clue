package game;

import game.RoomCell.DoorDirection;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Board {
	private static BoardCell[][] layout; //size of layout will be determined upon reading file
	private Map<Character,String> rooms;
	private int numRows, numColumns;
	IntBoard calc;
	
	
	public void loadBoardConfig(String layoutFile, String legend) throws IOException, BadConfigFormatException{
		rooms = new HashMap<Character,String>();
		FileReader reader = new FileReader(legend);
		FileReader reader1 = new FileReader(layoutFile);
		Scanner in = new Scanner(reader);
		Scanner in1 = new Scanner(reader1);
		//Creates legend map
		while (in.hasNext()){
			String tempChar = in.next();
			String tempString = in.nextLine();
			tempString = tempString.substring(1);
			if (tempString.contains(",")) {
				in.close();
				in1.close();
				throw new BadConfigFormatException(1);
			}
			rooms.put(tempChar.charAt(0), tempString);
		}
		in.close();
		Scanner tempIn = new Scanner(in1.nextLine());
		tempIn.useDelimiter(",");
		numColumns = 0;
		numRows = 1;
		//get number of columns and rows
		while (tempIn.hasNext()){
			tempIn.next();
			numColumns++;
		}
		tempIn.close();
		Scanner tempIn1;
		while (in1.hasNextLine()){
			Integer tempColumns = 0;
			tempIn1 = new Scanner(in1.nextLine());
			tempIn1.useDelimiter(",");
			while (tempIn1.hasNext()){
				tempIn1.next();
				tempColumns++;
			}
			tempIn1.close();
			if (!tempColumns.equals(numColumns)){
				in1.close();
				throw new BadConfigFormatException(0);
			}
			numRows++;
		}
		layout = new BoardCell[numRows][numColumns];
		in1.close();
		String tempCell = "";
		char tempDirection = 'A';
		FileReader reader2 = new FileReader(layoutFile);
		Scanner in2 = new Scanner(reader2);
		in2.useDelimiter(",");
		//adds values to layout from the file
		for (int row = 0; row < numRows; row++){
			for (int column = 0; column < numColumns; column++){
				if (!in2.hasNext()){
					in2.close();
					throw new BadConfigFormatException(0);
				}
				if (column == numColumns-1){
					tempCell = in2.nextLine().substring(1);
				}
				else{
					tempCell = in2.next();
				}
				if (!rooms.containsKey(tempCell.charAt(0))){
					in2.close();
					throw new BadConfigFormatException(2);
				}
				if (tempCell.charAt(0) == 'W'){
					if (tempCell.length() > 1){
						in2.close();
						throw new BadConfigFormatException(3);
					}
					layout[row][column] = new BoardCell(row,column);
				}
				else {
					if (tempCell.length() > 1){
						tempDirection = tempCell.charAt(1);
						switch (tempDirection){
						case 'U':
							layout[row][column] = new RoomCell(row,column,tempCell.charAt(0),DoorDirection.UP);
							break;
						case 'D':
							layout[row][column] = new RoomCell(row,column,tempCell.charAt(0),DoorDirection.DOWN);
							break;
						case 'L':
							layout[row][column] = new RoomCell(row,column,tempCell.charAt(0),DoorDirection.LEFT);
							break;
						case 'R':
							layout[row][column] = new RoomCell(row,column,tempCell.charAt(0),DoorDirection.RIGHT);
							break;
						}
					} else layout[row][column] = new RoomCell(row,column,tempCell.charAt(0),DoorDirection.NONE);
				}
			}
		}
		in2.close();
		reader.close();
		reader1.close();
		reader2.close();
	}
	
	public void calcAdjacencies(){
		calc = new IntBoard(numRows, numColumns);
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	public Map<Character,String> getRooms() {
		return rooms;
	}
	public int getNumRooms(){
		return rooms.size();
	}
	public String getRoom(Character key) {
		return rooms.get(key);
	}
	public BoardCell[][] getLayout() {
		return layout;
	}
	public static BoardCell getCellAt(int row, int col) {
		return layout[row][col];
	}
	public RoomCell getRoomCellAt(int row, int col) {
		RoomCell tempCell = new RoomCell();
		if (layout[row][col] instanceof RoomCell){
			tempCell = (RoomCell) layout[row][col];
			return tempCell;
		}
		else{
			return tempCell;
		}
	}
		
	/*
	public LinkedList<BoardCell> getAdjList(BoardCell cell){
		LinkedList<BoardCell> list = new LinkedList<BoardCell>();
		int row = cell.row();
		int column = cell.column();

		if (cell.isDoorway()){
			RoomCell testDoorDirection = getRoomCellAt(row,column);
			if (testDoorDirection.getDoorDirection().equals(DoorDirection.UP)) list.add(getCellAt(row-1,column));
			if (testDoorDirection.getDoorDirection().equals(DoorDirection.DOWN)) list.add(getCellAt(row+1,column));
			if (testDoorDirection.getDoorDirection().equals(DoorDirection.RIGHT)) list.add(getCellAt(row,column+1));
			if (testDoorDirection.getDoorDirection().equals(DoorDirection.LEFT)) list.add(getCellAt(row,column-1));
		}
		//for entering a door, if you're above the door, the bottom cell must have a door direction of UP,
		//so this is counter intuitive.
		else{
			if(row != 0 && !visited.contains(getCellAt(row - 1, column))){
				BoardCell topCell = getCellAt(row - 1, column);
				if(!topCell.isRoom()){
					list.add(topCell);
				}
				else {
					RoomCell doorTest = getRoomCellAt(row - 1, column);
					if (doorTest.getDoorDirection().equals(DoorDirection.DOWN)){
						list.add(doorTest);
					}
				}
			}
			if(row != (numRows - 1) && !visited.contains(getCellAt(row + 1, column))){
				BoardCell bottomCell = getCellAt(row + 1, column);
				if(!bottomCell.isRoom()){
					list.add(bottomCell);
				}
				else {
					RoomCell doorTest = getRoomCellAt(row + 1, column);
					if (doorTest.getDoorDirection().equals(DoorDirection.UP)){
						list.add(doorTest);
					}
				}
			}
			if(column != 0 && !visited.contains(getCellAt(row, column - 1))){
				BoardCell leftCell = getCellAt(row, column - 1);
				if(!leftCell.isRoom()){
					list.add(leftCell);
				}
				else {
					RoomCell doorTest = getRoomCellAt(row, column - 1);
					if (doorTest.getDoorDirection().equals(DoorDirection.RIGHT)){
						list.add(doorTest);
					}
				}
			}
			if(column != (numColumns - 1) && !visited.contains(getCellAt(row, column + 1))){
				BoardCell rightCell = getCellAt(row, column + 1);
				if(!rightCell.isRoom()){
					list.add(rightCell);
				}
				else {
					RoomCell doorTest = getRoomCellAt(row, column + 1);
					if (doorTest.getDoorDirection().equals(DoorDirection.LEFT)){
						list.add(doorTest);
					}
				}
			}
		}

		return list;
	}*/

	
	public LinkedList<BoardCell> getAdjList(int row, int column){
		return calc.getAdjList(getCellAt(row, column));
	}
	
	public void calcTargets(int row, int column, int numberOfMoves){
		calc.calcTargets(getCellAt(row, column), numberOfMoves);
	}
	
	public Set<BoardCell> getTargets(){
		return calc.getTargets();
	}
}

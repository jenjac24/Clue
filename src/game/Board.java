package game;

import game.RoomCell.DoorDirection;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {
	private BoardCell[][] layout; //size of layout will be determined upon reading file
	private Map<Character,String> rooms;
	private int numRows, numColumns;
	public void loadBoardConfig(String layoutFile, String legend) throws Exception{
		Map<Character,String> rooms = new HashMap<Character,String>();
		FileReader reader = new FileReader(legend);
		FileReader reader1 = new FileReader(layoutFile);
		Scanner in = new Scanner(reader);
		Scanner in1 = new Scanner(reader1);
		Scanner in2 = new Scanner(reader1);
		//Creates legend map
		while (in.hasNext()){
			String tempChar = in.next();
			String tempString = in.nextLine();
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
		while (in1.hasNextLine()){
			in1.nextLine();
			numRows++;
		}
		layout = new BoardCell[numRows][numColumns];
		in1.close();
		in2.useDelimiter(",");
		String tempCell = "";
		char tempDirection = 'A';
		//adds values to layout from the file
		for (int row = 0; row < numRows; row++){
			for (int column = 0; column < numColumns && in2.hasNext(); column++){
				tempCell = in2.next();
				if (tempCell.equals("W")){
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
	public BoardCell getCellAt(int row, int col) {
		return layout[row][col];
	}
	public RoomCell getRoomCellAt(int row, int col) {
		return (RoomCell) layout[row][col];
	}
}

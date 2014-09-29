package clueGame;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import clueGame.RoomCell.DoorDirection;

public class Board {

	private static BoardCell[][] grid;
	private static Map<Character, String> rooms;
	private int numRows;
	private int numColumns;
	private String layoutFile;

	public Board(String layoutFileName, Map<Character, String> room){
		rooms = room;
		layoutFile = layoutFileName;
	}

	public Board loadBoardConfig(){
		/*
		int rows = 0;
		int cols = 0;
		int row =0;
		String c;
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
				if(rows==1){
					arr = line.split(",");
					cols = arr.length;
				}
			}
			numRows = rows;
			numColumns = cols;
			System.out.println(numRows);
			System.out.println(numColumns + " numcols");
			//if numRows doesn't equal 23 or 22, throw an exception, same for numColumns
			if(numRows!=22) throw new BadConfigFormatException(numRows);
			if(numColumns!=23) throw new BadConfigFormatException(numColumns);
			grid = new BoardCell[numRows][numColumns];
			read2 = new FileReader("ClueMap.csv");
			Scanner scan2 = new Scanner(read2);
			String[][] chars = new String[numRows][numColumns];

			while(scan2.hasNextLine()){
				line = scan2.nextLine();
				//now i split the string with commas and then put them into an array
				//which will essentially be the same as the excel file
				arr = line.split(",");
				chars[row] = arr;
				row++;
			}

			for(int i = 0; i<chars.length; i++){
				for(int j=0; j<chars[0].length; j++){
					c = chars[i][j];
					if(c.length() > 1){
						second = c.charAt(1);
						fillGrid(i, j, c.charAt(0), determineDirection(second), true);
						//System.out.println(c + " first if");
					}
					else {
						first = c.charAt(0);
						fillGrid(i,j,first, DoorDirection.NONE, false);
						//System.out.println(c + " second if");
					}
				}
			}

			//throw an error if not all of the lengths of the second arrays are the same
			for(int i =0; i<chars.length; i++){
				if(chars[0].length!=numColumns) throw new BadConfigFormatException(chars.length);
			}
			scan2.close();
			scan. close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*for(int i =0; i<grid.length; i++){
		for(int j =0; j<grid[0].length; j++){
			System.out.print(grid[i][j] + "  ");
		}
		System.out.println();
	}*/
		return this;
	}

	public void fillGrid(int i, int j, char first, DoorDirection dir, boolean isDoor){
		/*System.out.println("HERERRRRERERERERERERERERE");
		System.out.println(rooms);
		Iterator<Entry<Character, String>> in = rooms.entrySet().iterator(); 
		while(in.hasNext()){
		    Entry<Character, String> var = in.next();
		    Character key = var.getKey();
		    String value = var.getValue();
		    System.out.println(key+", "+ rooms.get(key));
		    //System.out.println(value+", "+ rooms.get(value));
		}*/
		//System.out.println(first);
		if(rooms.containsKey(first)){
			//System.out.println("HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
			grid[i][j] = new RoomCell(i, j, first, dir, isDoor);
		}
		else grid[i][j] = new WalkwayCell(i,j);
	}

	public DoorDirection determineDirection(char c){
		if(c=='U') return DoorDirection.UP;
		else if(c=='D') return DoorDirection.DOWN;
		else if(c=='R') return DoorDirection.RIGHT;
		else if(c=='L') return DoorDirection.LEFT;
		else return DoorDirection.NONE;
	}

	public BoardCell getCellAt(int row, int col){
		return grid[row][col];
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public RoomCell getRoomCellAt(int row, int col){
		//return grid[row][col];
		return new RoomCell(row, col, 'C', DoorDirection.NONE, false);
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

}

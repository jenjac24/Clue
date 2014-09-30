package clueGame;

<<<<<<< HEAD
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
=======
import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
>>>>>>> d5740a5824928b931c0a25a5029124877cc9db7e
import java.util.Scanner;

import clueGame.RoomCell.DoorDirection;

public class Board {

<<<<<<< HEAD
	private BoardCell[][] grid;
	private Map<Character, String> rooms;
	private int numRows;
	private int numColumns;

	public Board(){
		loadBoardConfig();
		//grid = new BoardCell[numRows][numColumns];
		//think we initialize whether it is a roomcell or boardcell when we load the file
	}

	public void loadBoardConfig(){
		int rows = 0;
		int cols = 0;
		int i =0, j=0, count =0;
		String c;
		String line;
		String comma = ",";
		char first, second;
		FileReader read;
		try {
			read = new FileReader("ClueMap.csv");
			Scanner scan = new Scanner(read);
			System.out.println("hello");
			scan.useDelimiter(comma);
			while(scan.hasNextLine()){
				rows++;
				if(!scan.hasNextLine()){
					System.out.println("lala");
					System.out.println(scan.next());
					while(scan.hasNext()){
						System.out.println("ro");
						cols++;
						c = scan.next();
					}
				}
				line = scan.nextLine();
				
			}
			numRows = rows;
			numColumns = cols;
			System.out.println(numRows);
			System.out.println(numColumns);
			//if numRows doesn't equal 23 or 22, throw an exception, same for numColumns
			grid = new BoardCell[numRows][numColumns];
			Scanner scan2 = new Scanner(read);
			scan2.useDelimiter(comma);
			while(scan2.hasNext()){
				if(count%numColumns==0) {
					i++;
					j=-1;
				}
				j++;
				count++;
				c = scan2.next();
				if(c.length() > 1){
					second = c.charAt(1);
					fillGrid(i, j, c.charAt(0), determineDirection(second), true);
					System.out.println(c + " first if");
				}
				else {
					first = c.charAt(0);
					fillGrid(i,j,first, DoorDirection.NONE, false);
					System.out.println(c + " second if");
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void fillGrid(int i, int j, char first, DoorDirection dir, boolean isDoor){
		if(rooms.containsKey(first)){
			grid[i][j] = new RoomCell(first, dir, isDoor);
		}
	}

=======
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

>>>>>>> d5740a5824928b931c0a25a5029124877cc9db7e
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
<<<<<<< HEAD
		return new RoomCell('C', DoorDirection.NONE, false);
=======
		return new RoomCell(row, col, 'C', DoorDirection.NONE, false);
>>>>>>> d5740a5824928b931c0a25a5029124877cc9db7e
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

<<<<<<< HEAD
	public static void main(String[] args){
		Board board = new Board();
		board.loadBoardConfig();
	}

=======
>>>>>>> d5740a5824928b931c0a25a5029124877cc9db7e
}

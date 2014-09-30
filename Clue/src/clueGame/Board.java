package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;

import clueGame.RoomCell.DoorDirection;

public class Board {

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
		return new RoomCell('C', DoorDirection.NONE, false);
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public static void main(String[] args){
		Board board = new Board();
		board.loadBoardConfig();
	}

}

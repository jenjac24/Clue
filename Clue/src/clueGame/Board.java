package clueGame;

import java.util.Map;

import experiment.BoardCell;

public class Board {
	
	private BoardCell[][] grid;
	private Map<Character, String> rooms;
	private int numRows;
	private int numColumns;
	
	public Board(){
		grid = new BoardCell[numRows][numColumns];
		/*for(int i =0; i<numRows; i++){
			for(int j =0; j <numColumns; j++){
				grid[i][j] = new BoardCell(i, j);
			}
		}*/
		//think we initialize whether it is a roomcell or boardcell when we load the file
	}
	
	
	
	public void loadBoardConfig(){
		//load the board layout, the numRows and numCols
	}

	public BoardCell getCellAt(int row, int col){
		return grid[row][col];
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}
	
	public RoomCell getRoomCellAt(int row, int col){
		//return grid[row][col];
		return new RoomCell();
		//what to return here?
		//return grid[row][col];
	}
	
	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	
}

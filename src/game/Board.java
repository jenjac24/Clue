package game;

import java.util.ArrayList;
import java.util.Map;

public class Board {
	private String[][] layout; //size of layout will be determined upon reading file
	private Map<Character,String> rooms;
	private int numRows, numColumns;
	public static void loadBoardConfig(){
		
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	public int getRooms() {
		return rooms.size();
	}
	public String getRoom(Character key) {
		return rooms.get(key);
	}
	public String[][] getLayout() {
		return layout;
	}
}

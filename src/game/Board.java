package game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {
	private String[][] layout; //size of layout will be determined upon reading file
	private Map<Character,String> rooms;
	private int numRows, numColumns;
	//Added code that reads in the legend and correctly creates the map.
	public static void loadBoardConfig(String layout, String legend) throws Exception{
		Map<Character,String> rooms = new HashMap<Character,String>();
		FileReader reader = new FileReader(legend);
		Scanner in = new Scanner(reader);
		while (in.hasNext()){
			String tempChar = in.next();
			String tempString = in.nextLine();
			rooms.put(tempChar.charAt(0), tempString);
		}
		in.close();
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	// getRooms is generating a null pointer exception in testRooms
	public Map<Character,String> getRooms() {
		return rooms;
	}
	public int getNumRooms(){
		return rooms.size();
	}
	public String getRoom(Character key) {
		return rooms.get(key);
	}
	public String[][] getLayout() {
		return layout;
	}
}

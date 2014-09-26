package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ClueGame {
	public Map<Character, String> rooms;
	private Board board;
	private String layoutFile, legendFile;

	public ClueGame(String layout, String legend){
		layoutFile = layout;
		legendFile = legend;
		rooms = new HashMap<Character, String>();
		board = new Board(layoutFile);
		loadConfigFiles();
	}

	public void loadConfigFiles(){
		loadRoomConfig();
		try {
			board.loadBoardConfig(layoutFile);
		} catch (BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getLayoutFile(){
		return layoutFile;
	}

	public Board getBoard(){
		return board;
	}

	public void loadRoomConfig() {
		// TODO Auto-generated method stub
		//load legend
		try {
			FileReader reader = new FileReader(legendFile);
			Scanner in = new Scanner(reader);
			in.useDelimiter(", *");
			String temp;
			Character tempChar;
			String tempRoom;
			while(in.hasNext()) {
				System.out.println("loop");
				temp = in.next();
				tempChar = temp.charAt(0);
				tempRoom = in.next();
				System.out.println("Char: " + tempChar + " Room: " + tempRoom);
				rooms.put(tempChar,tempRoom);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	public static void main(String[] args){
		ClueGame clue = new ClueGame("ClueMap.csv", "legend.txt");
	}

}




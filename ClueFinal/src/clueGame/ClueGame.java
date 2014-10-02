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
		loadConfigFiles();
	}

	public void loadConfigFiles(){
		try{
		loadRoomConfig();
		board = new Board(layoutFile, rooms);
		board.loadBoardConfig();
		}
		catch(Exception e){
		}
	}

	public String getLayoutFile(){
		return layoutFile;
	}

	public Board getBoard(){
		return board;
	}

	public void loadRoomConfig() throws BadConfigFormatException {
		// TODO Auto-generated method stub
		//load ClueLegend file
		try {
			//create file readers and scanners
			FileReader reader = new FileReader(legendFile);
			Scanner in = new Scanner(reader);
			//variables to use in methods
			String temp;
			Character tempChar;
			String tempRoom;
			//2D array that will hold the legend data
			String[][] arr = new String[11][2];
			int row = 0;
			//going through to load the data to the 2D array
			while(in.hasNextLine()) {
				temp = in.nextLine();
				arr[row] = temp.split(", ");
				//checking to see if the legend file has the correct format, or throwing an exception if not
				if(arr[row].length!=2) throw new BadConfigFormatException(arr[row].length);
				row++;
			} 
			//loading the room map with the values from the 2D array
			for(int i = 0; i<arr.length; i++){
				for(int j =0; j<arr[0].length-1; j++){
					tempChar = arr[i][j].charAt(0);
					tempRoom = arr[i][j+1];
					rooms.put(tempChar,tempRoom);
				}
			}
		} catch (BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BadConfigFormatException(5);	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}




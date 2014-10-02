package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ClueGame {
	public static Map<Character, String> rooms;
	private static Board board;
	private String layoutFile, legendFile;

	public ClueGame(String layout, String legend){
		layoutFile = layout;
		legendFile = legend;
		rooms = new HashMap<Character, String>();
		//board = new Board(layoutFile, rooms);
		loadConfigFiles();
	}

	public void loadConfigFiles(){
		loadRoomConfig();
		board = new Board(layoutFile, rooms);
		board.loadBoardConfig();
<<<<<<< HEAD
		loadLegend();
	}
	
	public String getLayoutFile(){
		return layoutFile;
=======
>>>>>>> d5740a5824928b931c0a25a5029124877cc9db7e
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
		/*try {
			FileReader reader = new FileReader(legendFile);
			Scanner in = new Scanner(reader);
			//in.useDelimiter(", ");
			String temp;
			Character tempChar;
			String tempRoom;
			String[][] arr = new String[11][2];
			int row= 0;
			while(in.hasNextLine()) {
				temp = in.nextLine();
				arr[row] = temp.split(",");
				row++;
			}
			for(int i = 0; i<arr.length; i++){
				for(int j =0; j<arr[0].length-1; j++){
					tempChar = arr[i][j].charAt(0);
					tempRoom = arr[i][j+1];
					System.out.println("Char: " + tempChar + " Room: " + tempRoom);
					rooms.put(tempChar,tempRoom);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

*/
	}

<<<<<<< HEAD
	public void loadRoomConfig() {
		// TODO Auto-generated method stub
		
	}
	
	
=======
>>>>>>> d5740a5824928b931c0a25a5029124877cc9db7e
}




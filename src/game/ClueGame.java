package game;

import java.io.IOException;
import java.util.Map;

public class ClueGame {
	private Map<Character,String> rooms;
	private String layout;
	private String legend;
	private Board board;
	
	public ClueGame(String a, String b){
		layout = a;
		legend = b;
		board = new Board();
	}
	
	public void loadConfigFiles() {
		try {
			board.loadBoardConfig(layout,legend);
		} catch (BadConfigFormatException e) {
			System.out.println(e.toString());
		} catch (IOException e){
			System.out.println(e.toString());
		}
	}

	public Board getBoard() {
		return board;
	}
	
	public void loadRoomConfig() {
		loadConfigFiles();
	}
}

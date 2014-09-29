package game;

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
	
	public void loadConfigFiles() throws Exception{
		board.loadBoardConfig(layout,legend);
	}

	public Board getBoard() {
		return board;
	}
}

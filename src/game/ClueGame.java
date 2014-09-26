package game;

import java.util.Map;

public class ClueGame {
	private Map<Character,String> rooms;
	private String layout;
	private String legend;
	
	public ClueGame(String a, String b){
		layout = a;
		legend = b;
	}
	
	public void loadConfigFiles() throws Exception{
		Board.loadBoardConfig(layout,legend);
	}

	public Board getBoard() {
		return null;
	}
}

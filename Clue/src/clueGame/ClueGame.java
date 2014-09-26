package clueGame;

import java.util.HashMap;
import java.util.Map;

public class ClueGame {
	public Map<Character, String> rooms;
	private Board board;
	private String layoutFile, legendFile;
	
	public ClueGame(String layout, String legend){
		layoutFile = layout;
		legendFile = legend;
		loadConfigFiles();
		rooms = new HashMap<Character, String>();
		board = new Board();
	}
	
	public void loadConfigFiles(){
		board.loadBoardConfig();
		loadLegend();
		
	}
	
	public Board getBoard(){
		return board;
	}
	
	public void loadLegend(){
		//load the legend file
	}
	
	
}

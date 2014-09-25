package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

public class IntBoard {
	
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	
	public IntBoard(){
		Set targets = null;
	}
	
	public Set<BoardCell> calcTargets(BoardCell thisCell, int numberOfMoves){ //finds possible moves
		visited.add(thisCell);
		LinkedList<BoardCell> adjCell = null; 
		adjCell = getAdjList(thisCell); //may need a try catch?
		for(BoardCell c : adjCell){
			if(numberOfMoves == 1) targets.add(c); //if last move used, saves spot as possible location
			else calcTargets(c, numberOfMoves --); //spends one move to an adjacent cell
		}
		return null;
	}
	
	public Set<BoardCell> getTargets(){
		Set<BoardCell> targets = null;
		
		return targets;
	}
	
	public BoardCell getCell(int x, int y){
		BoardCell cell = null;
		return cell;
	}
	
	public LinkedList<BoardCell> getAdjList(BoardCell cell){
		LinkedList<BoardCell> list = null;
		return list;
	}

}

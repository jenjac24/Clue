package game;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class IntBoard {
	
	private int BOARD_LENGTH = 22;
	private int BOARD_WIDTH = 24;
	
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] board = new BoardCell[BOARD_LENGTH][BOARD_WIDTH];
	
	public void constructBoard(){
		for(int i = 0; i < BOARD_LENGTH; i ++){
			for(int j = 0; j < BOARD_WIDTH; j++){
				board[i][j] = new BoardCell(i, j);
			}
		}
	}
	
	public IntBoard(){
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		//constructBoard();
	}
	
	public void calcTargets(BoardCell thisCell, int numberOfMoves){
		targets.clear();
		doCalc(thisCell, numberOfMoves);
	}
	
	
	public void doCalc(BoardCell thisCell, int numberOfMoves){ //finds possible moves
		visited.add(thisCell);
		LinkedList<BoardCell> adjCell = null; 
		adjCell = getAdjList(thisCell); //may need a try catch?
		for(BoardCell c : adjCell){
			if(numberOfMoves == 1) targets.add(c); //if last move used, saves spot as possible location
			else doCalc(c, numberOfMoves - 1); //spends one move to an adjacent cell
		}
		adjCell.clear();
		visited.clear();
	}
	
	public Set<BoardCell> getTargets(){
		
		return targets;
	}
	
	public BoardCell getCell(int x, int y){
		return board[x][y];
	}
	
	public LinkedList<BoardCell> getAdjList(BoardCell cell){
		LinkedList<BoardCell> list = new LinkedList<BoardCell>();
		int row = cell.row();
		int column = cell.column();
		
		
		if(row != 0 && !visited.contains(getCell(row - 1, column))){
			BoardCell leftCell = getCell(row - 1, column);
			if(!leftCell.isRoom() || (leftCell.isRoom() && leftCell.isDoorway())){
				list.add(leftCell);
			}
		}
		if(row != (BOARD_LENGTH - 1) && !visited.contains(getCell(row + 1, column))){
			BoardCell rightCell = getCell(row + 1, column);
			if(!cell.isRoom() || (cell.isRoom() && cell.isDoorway())){
				list.add(rightCell);
			}
		}
		if(column != 0 && !visited.contains(getCell(row, column - 1))){
			BoardCell topCell = getCell(row, column - 1);
			if((!cell.isRoom() || (cell.isRoom() && cell.isDoorway()))){
				list.add(topCell);
			}
		}
		if(column != (BOARD_WIDTH - 1) && !visited.contains(getCell(row, column + 1))){
			BoardCell bottomCell = getCell(row, column + 1);
			if((!cell.isRoom() || (cell.isRoom() && cell.isDoorway()))){
				list.add(bottomCell);
			}
		}
		
		return list;
		
		/*
		if(row != 0 && !visited.contains(getCell(row - 1, column)) && (!cell.isRoom() || (cell.isRoom() && cell.isDoorway())))			
			list.add(getCell(row - 1, column));
		if(row != (BOARD_LENGTH - 1) && !visited.contains(getCell(row + 1, column)) && (!cell.isRoom() || (cell.isRoom() && cell.isDoorway()))) 
			list.add(getCell(row + 1, column));
		if(column != 0 && !visited.contains(getCell(row, column - 1)) && (!cell.isRoom() || (cell.isRoom() && cell.isDoorway())))
			list.add(getCell(row, column - 1));
		if(column != (BOARD_WIDTH - 1) && !visited.contains(getCell(row, column + 1)) && (!cell.isRoom() || (cell.isRoom() && cell.isDoorway())))
			list.add(getCell(row, column + 1));
		return list;*/
	}

}


package experiment;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IntBoard {
private LinkedList<BoardCell> adjacentCells; 
private int[][] grid;
private Map<BoardCell,LinkedList<BoardCell>> adjacentCellsMap;
private Set<BoardCell> visited;
private Set<BoardCell> targets;

//constructor
public IntBoard(int row, int col) {
	adjacentCells = new LinkedList<BoardCell>();
	grid = new int[row][col];
	adjacentCellsMap = new HashMap<BoardCell, LinkedList<BoardCell>>();
	visited = new HashSet();
	targets = new HashSet();


}

public BoardCell getCell(int row, int col) {
	return (new BoardCell(row, col));
	
}

//calcAdjacencies, calculates the adjacency lists for each grid cell, store in a map data structure
public void caclAdjacencies() {
	for(int i = 0; i < 4; i++) {
		for(int j =0; j < 4; j++) {
			BoardCell currentLocation = new BoardCell(i,j);
			LinkedList<BoardCell> adjList = new LinkedList<BoardCell>();
			if(i+1 < 4) {
				BoardCell tempCell = new BoardCell(i+1,j);
				adjList.add(tempCell);
				
			}
			if(i-1 >= 0) {
				BoardCell tempCell = new BoardCell(i-1,j);
				adjList.add(tempCell);
				
			}
			if(j+1 < 4) {
				BoardCell tempCell = new BoardCell(i,j+1);
				adjList.add(tempCell);
				
			}
			if(j-1 >= 0) {
				BoardCell tempCell = new BoardCell(i,j-1);
				adjList.add(tempCell);
				
			}
			adjacentCellsMap.put(currentLocation, adjList);
			
		}
	}
	 
	
}
//calcTargets, calculates the targets using the algorithm described in pwpt, list of targets will be stored in an instance variable
public void calcTargets(BoardCell cell, int numSteps) {
	//visited (set)
		//targets (set)
		//adjacentCellsMap map<boardcell, linkedlist<boardCell>>
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		visited.add(cell);
		
		
		LinkedList<BoardCell> adjCells = new LinkedList<BoardCell>(adjacentCellsMap.get(cell));
		for(BoardCell c: adjCells){
			visited.add(cell);
			if(numSteps ==1) targets.add(cell);
			else calcTargets(c, numSteps--);
			visited.remove(cell);
		}
}
//getTargets, returns list of targets, return type is Set<BoardCell>
public Set<BoardCell> getTargets() {
	
	return targets;
	
}
//getAdjList returns the adjacency list for one cell, return type = LinkedList<BoardCell>
public LinkedList<BoardCell> getAdjList(BoardCell cell) {
	
	return adjacentCells;
	
}
	
}

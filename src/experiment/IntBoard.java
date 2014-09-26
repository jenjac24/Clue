
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
public final static int ROWS = 4;
public final static int COLS =4;
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
public void calcAdjacencies() {
	//System.out.println("This is from calcAdjacencies");
	for(int i = 0; i < ROWS; i++) {
		for(int j =0; j < COLS; j++) {
			BoardCell currentLocation = getCell(i,j);
			LinkedList<BoardCell> adjList = new LinkedList<BoardCell>();
			
			//System.out.println("CurrentLocation: " + currentLocation);
			if(i+1 < ROWS) {
				BoardCell tempCell = new BoardCell(i+1,j);
				adjList.add(tempCell);
				//System.out.println("Just added this point to adjList: " + (i+1) + "," + j);
				
			}
			if(i-1 >= 0) {
				BoardCell tempCell = new BoardCell(i-1,j);
				adjList.add(tempCell);
				//System.out.println("Just added this point to adjList: " + (i-1) + "," + j);
				
			}
			 if(j+1 < COLS) {
				BoardCell tempCell = new BoardCell(i,j+1);
				adjList.add(tempCell);
				//System.out.println("Just added this point to adjList: " + i + "," + (j+1));
			}
			if(j-1 >= 0) {
				BoardCell tempCell = new BoardCell(i,j-1);
				adjList.add(tempCell);
				//System.out.println("Just added this point to adjList: " + i + "," + (j-1));
				
			}
			//System.out.println("adjList size: " + adjList.size());
			adjacentCellsMap.put(currentLocation, adjList);
			//System.out.println("Map: " + adjacentCellsMap.get(currentLocation));
			
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
	calcAdjacencies();
	
	
	System.out.println("This is from getAdjList: cell:" + cell);
	System.out.println("Is cell a key in map? ");
	if(adjacentCellsMap.containsKey(cell)) {
		System.out.println("Key is in map!"); 
	}
	else {
	System.out.println("Key is not in map!");
	}
	System.out.println("LinkedList value from key cell:" + adjacentCellsMap.get(cell));
	adjacentCells = adjacentCellsMap.get(cell);
	System.out.println("size of map: " + adjacentCellsMap.size());
	return adjacentCells;
	
}
	
}

package test;

import java.util.LinkedList;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import game.Board;
import game.BoardCell;
import game.ClueGame;
import game.IntBoard;

public class AdjacencyTests {
	private static Board board;
	@BeforeClass
	public static void setUp() {
		ClueGame game = new ClueGame("ClueLayout.csv", "ClueLegend.txt");
		game.loadConfigFiles();
		board = game.getBoard();
		//board.calcAdjacencies();
	}
	@Test
	public void testWalkWay(){
		LinkedList<BoardCell> testList;
		testList = board.getAdjList(4, 9);
		Assert.assertEquals(4, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(3, 9)));
		Assert.assertTrue(testList.contains(board.getCellAt(5, 9)));
		Assert.assertTrue(testList.contains(board.getCellAt(4, 8)));
		Assert.assertTrue(testList.contains(board.getCellAt(4, 10)));
		testList = board.getAdjList(8, 9);
		Assert.assertEquals(4, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(7, 9)));
		Assert.assertTrue(testList.contains(board.getCellAt(9, 9)));
		Assert.assertTrue(testList.contains(board.getCellAt(8, 8)));
		Assert.assertTrue(testList.contains(board.getCellAt(8, 10)));
	}
	@Test
	public void testCornerOfWalkWay(){
		LinkedList<BoardCell> testList;
		testList = board.getAdjList(0, 23);
		Assert.assertEquals(2, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(0, 22)));
		Assert.assertTrue(testList.contains(board.getCellAt(1, 23)));
		testList = board.getAdjList(0, 0);
		Assert.assertTrue(testList.contains(board.getCellAt(0, 1)));
		Assert.assertTrue(testList.contains(board.getCellAt(1, 0)));
	}
	@Test
	public void testInsideRoom(){
		LinkedList<BoardCell> testList;
		testList = board.getAdjList(3, 6);
		Assert.assertEquals(0, testList.size());
	}
	@Test
	public void testAtDoor(){
		LinkedList<BoardCell> testList;
		testList = board.getAdjList(5, 19);
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(6, 19)));
	}
	@Test
	public void testWall(){
		LinkedList<BoardCell> testList;
		testList = board.getAdjList(19, 5);
		Assert.assertEquals(2, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(18, 5)));
		Assert.assertTrue(testList.contains(board.getCellAt(20, 5)));
	}
	@Test
	public void testCloset(){
		LinkedList<BoardCell> testList;
		testList = board.getAdjList(8, 22);
		Assert.assertEquals(3, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(7, 22)));
		Assert.assertTrue(testList.contains(board.getCellAt(9, 22)));
		Assert.assertTrue(testList.contains(board.getCellAt(8, 23)));
	}
}
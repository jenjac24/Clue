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
		ClueGame game = new ClueGame("OurClueLayout.csv", "OurClueLegend.txt");
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
<<<<<<< HEAD
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
=======
>>>>>>> e5e61b592eca591b073bf5162a3d76dbc63463c2
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
		testList = board.getAdjList(14, 4);
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(13, 4)));
	}
	@Test
	public void testAdjToRoomNoDoorway(){
		LinkedList<BoardCell> testList;
		testList = board.getAdjList(19, 5);
		Assert.assertEquals(2, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(18, 5)));
		Assert.assertTrue(testList.contains(board.getCellAt(20, 5)));
		testList = board.getAdjList(8, 22);
		Assert.assertEquals(3, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(7, 22)));
		Assert.assertTrue(testList.contains(board.getCellAt(9, 22)));
		Assert.assertTrue(testList.contains(board.getCellAt(8, 23)));
	}
	@Test
	public void testAdjToDoorway(){
		LinkedList<BoardCell> testList;
		testList = board.getAdjList(0, 0);
		Assert.assertEquals(2, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(1, 0)));
		Assert.assertTrue(testList.contains(board.getCellAt(0, 1)));
		testList = board.getAdjList(11, 0);
		Assert.assertEquals(2, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(10, 0)));
		Assert.assertTrue(testList.contains(board.getCellAt(12, 0)));
		testList = board.getAdjList(0, 8);
		Assert.assertEquals(3, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(0, 7)));
		Assert.assertTrue(testList.contains(board.getCellAt(1, 8)));
		Assert.assertTrue(testList.contains(board.getCellAt(0, 9)));
		testList = board.getAdjList(17, 13);
		Assert.assertEquals(4, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(17, 12)));
		Assert.assertTrue(testList.contains(board.getCellAt(17, 14)));
		Assert.assertTrue(testList.contains(board.getCellAt(18, 13)));
		Assert.assertTrue(testList.contains(board.getCellAt(16, 13)));
	}
	@Test
	public void testCornerAdjacencies(){
		LinkedList<BoardCell> testList;
		testList = board.getAdjList(0, 0);
		Assert.assertEquals(2, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(1, 0)));
		Assert.assertTrue(testList.contains(board.getCellAt(0, 1)));
		testList = board.getAdjList(0, 23);
		Assert.assertEquals(2, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(0, 22)));
		Assert.assertTrue(testList.contains(board.getCellAt(1, 23)));
		testList = board.getAdjList(21, 0);
		Assert.assertEquals(0, testList.size());
		testList = board.getAdjList(21, 23);
		Assert.assertEquals(0, testList.size());
		
	}
}
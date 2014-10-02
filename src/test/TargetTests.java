package test;

import java.util.LinkedList;
import java.util.Set;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import game.Board;
import game.BoardCell;
import game.ClueGame;

public class TargetTests {
	private static Board board;
	@BeforeClass
	public static void setUp() {
		ClueGame game = new ClueGame("OurClueLayout.csv", "OurClueLegend.txt");
		game.loadConfigFiles();
		board = game.getBoard();
		board.calcAdjacencies();
	}
	
	@Test
	public void testAdjacency0()
	{
		LinkedList<BoardCell> testList = board.getAdjList(0,0);
		Assert.assertTrue(testList.contains(board.getCellAt(1, 0)));
		Assert.assertTrue(testList.contains(board.getCellAt(0, 1)));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testTargets0_0_1() //tests upper left corner
	{
		board.calcTargets(0,0,1);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(0, 1)));
		Assert.assertTrue(targets.contains(board.getCellAt(1, 0)));
	}
	@Test
	public void testTargets0_23_1() //tests upper right corner
	{
		board.calcTargets(0,23,1);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(0, 22)));
		Assert.assertTrue(targets.contains(board.getCellAt(1, 23)));
	}
	
	@Test
	public void testTargets21_23_1() //tests lower right corner
	{
		board.calcTargets(21,23,1);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(0, targets.size());
	}
	
	@Test
	public void testTargets0_0_3() //tests upper left corner
	{
		board.calcTargets(0,0,3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(0, 3)));
		Assert.assertTrue(targets.contains(board.getCellAt(1, 0)));
	}
	
	@Test
	public void testTargets0_23_3() //tests upper right corner
	{
		board.calcTargets(0,23,3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(5, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(0, 21)));//
		Assert.assertTrue(targets.contains(board.getCellAt(2, 22)));//
		Assert.assertTrue(targets.contains(board.getCellAt(1, 23)));
		Assert.assertTrue(targets.contains(board.getCellAt(3, 23)));//
		Assert.assertTrue(targets.contains(board.getCellAt(0, 22)));//
	}
	
	@Test
	public void testTargets21_0_3() //tests lower left corner
	{
		board.calcTargets(21,0,3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(0, targets.size());
	}
	
	@Test
	public void testTargets21_23_3() //tests bottom right corner
	{
		board.calcTargets(21,23,3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(0, targets.size());
	}
	
	@Test
	public void testTargets7_10_3() //tests a middle point of the board (7, 10)
	{
		board.calcTargets(7,10,3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(12, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(7, 7)));//1
		Assert.assertTrue(targets.contains(board.getCellAt(8, 8)));//2
		Assert.assertTrue(targets.contains(board.getCellAt(6, 8)));//3
		Assert.assertTrue(targets.contains(board.getCellAt(9, 9)));//4
		Assert.assertTrue(targets.contains(board.getCellAt(5, 9)));//5
		Assert.assertTrue(targets.contains(board.getCellAt(7, 9)));//6
		Assert.assertTrue(targets.contains(board.getCellAt(4, 10)));//7
		Assert.assertTrue(targets.contains(board.getCellAt(10, 10)));//8
		Assert.assertTrue(targets.contains(board.getCellAt(6, 10)));//9
		Assert.assertTrue(targets.contains(board.getCellAt(5, 11)));//10
		Assert.assertTrue(targets.contains(board.getCellAt(6, 12)));//11
		Assert.assertTrue(targets.contains(board.getCellAt(8, 10)));//16
	}
	@Test
	public void testTargetsLeavingRoom(){
		board.calcTargets(5,18,3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(6, 16)));
		Assert.assertTrue(targets.contains(board.getCellAt(6, 20)));
		board.calcTargets(7,7,6);
		targets = board.getTargets();
		Assert.assertEquals(17, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(2, 8)));
		Assert.assertTrue(targets.contains(board.getCellAt(4, 8)));
		Assert.assertTrue(targets.contains(board.getCellAt(6, 8)));
		Assert.assertTrue(targets.contains(board.getCellAt(8, 8)));
		Assert.assertTrue(targets.contains(board.getCellAt(10, 8)));
		Assert.assertTrue(targets.contains(board.getCellAt(12, 8)));
		Assert.assertTrue(targets.contains(board.getCellAt(3, 9)));
		Assert.assertTrue(targets.contains(board.getCellAt(5, 9)));
		Assert.assertTrue(targets.contains(board.getCellAt(7, 9)));
		Assert.assertTrue(targets.contains(board.getCellAt(9, 9)));
		Assert.assertTrue(targets.contains(board.getCellAt(11, 9)));
		Assert.assertTrue(targets.contains(board.getCellAt(4, 10)));
		Assert.assertTrue(targets.contains(board.getCellAt(6, 10)));
		Assert.assertTrue(targets.contains(board.getCellAt(8, 10)));
		Assert.assertTrue(targets.contains(board.getCellAt(10, 10)));
		Assert.assertTrue(targets.contains(board.getCellAt(6, 12)));
	}

}

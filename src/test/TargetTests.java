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

public class TargetTests {
	private static Board board;
	@BeforeClass
	public static void setUp() {
		ClueGame game = new ClueGame("OurClueLayout.csv", "OurClueLegend.txt");
		game.loadConfigFiles();
		board = game.getBoard();
		//board.calcAdjacencies();
	}
	
	@Test
	public void testAdjacency0()
	{
		BoardCell cell = board.getCellAt(0,0);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCellAt(1, 0)));
		Assert.assertTrue(testList.contains(board.getCellAt(0, 1)));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testTargets0_0_1() //tests upper left corner
	{
		BoardCell cell = board.getCellAt(0, 0);
		board.calcTargets(cell, 1);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(0, 1)));
		Assert.assertTrue(targets.contains(board.getCellAt(1, 0)));
	}
	@Test
	public void testTargets0_23_1() //tests upper right corner
	{
		BoardCell cell = board.getCellAt(0, 23);
		board.calcTargets(cell, 1);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(0, 22)));
		Assert.assertTrue(targets.contains(board.getCellAt(1, 23)));
	}
	
	@Test
	public void testTargets21_23_1() //tests lower right corner
	{
		BoardCell cell = board.getCellAt(21, 23);
		board.calcTargets(cell, 1);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(0, targets.size());
		//Assert.assertTrue(targets.contains(board.getCell(21, 22)));
		//Assert.assertTrue(targets.contains(board.getCell(20, 23)));
	}
	
	@Test
	public void testTargets0_0_3() //tests upper left corner
	{
		BoardCell cell = board.getCellAt(0, 0);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(2, targets.size());
		//Assert.assertTrue(targets.contains(board.getCell(3, 0)));
		//Assert.assertTrue(targets.contains(board.getCell(2, 1)));
		//Assert.assertTrue(targets.contains(board.getCell(0, 1)));
		//Assert.assertTrue(targets.contains(board.getCell(1, 2)));
		Assert.assertTrue(targets.contains(board.getCellAt(0, 3)));
		Assert.assertTrue(targets.contains(board.getCellAt(1, 0)));
	}
	
	@Test
	public void testTargets0_23_3() //tests upper right corner
	{
		BoardCell cell = board.getCellAt(0, 23);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(5, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(0, 21)));//
		Assert.assertTrue(targets.contains(board.getCellAt(2, 22)));//
		//Assert.assertTrue(targets.contains(board.getCell(1, 21)));//
		Assert.assertTrue(targets.contains(board.getCellAt(1, 23)));
		Assert.assertTrue(targets.contains(board.getCellAt(3, 23)));//
		Assert.assertTrue(targets.contains(board.getCellAt(0, 22)));//
	}
	
	@Test
	public void testTargets21_0_3() //tests lower left corner
	{
		BoardCell cell = board.getCellAt(21, 0);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(0, targets.size());
		//Assert.assertTrue(targets.contains(board.getCell(18, 0)));
		//Assert.assertTrue(targets.contains(board.getCell(19, 1)));
		//Assert.assertTrue(targets.contains(board.getCell(20, 2)));
		//Assert.assertTrue(targets.contains(board.getCell(20, 0)));
		//Assert.assertTrue(targets.contains(board.getCell(21, 1)));
		//Assert.assertTrue(targets.contains(board.getCell(21, 3)));
	}
	
	@Test
	public void testTargets21_23_3() //tests bottom right corner
	{
		BoardCell cell = board.getCellAt(21, 23);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(0, targets.size());
		//Assert.assertTrue(targets.contains(board.getCell(21, 20)));
		//Assert.assertTrue(targets.contains(board.getCell(20, 21)));
		//Assert.assertTrue(targets.contains(board.getCell(19, 22)));
		//Assert.assertTrue(targets.contains(board.getCell(21, 22)));
		//Assert.assertTrue(targets.contains(board.getCell(20, 23)));
		//Assert.assertTrue(targets.contains(board.getCell(18, 23)));
	}
	
	@Test
	public void testTargets7_10_3() //tests a middle point of the board (7, 10)
	{
		BoardCell cell = board.getCellAt(7, 10);
		board.calcTargets(cell, 3);
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
		//Assert.assertTrue(targets.contains(board.getCell(7, 13)));//12
		//Assert.assertTrue(targets.contains(board.getCell(9, 11)));//13
		//Assert.assertTrue(targets.contains(board.getCell(8, 12)));//14
		//Assert.assertTrue(targets.contains(board.getCell(7, 11)));//15
		//these can't be targets because they're room cells that aren't doors
		Assert.assertTrue(targets.contains(board.getCellAt(8, 10)));//16
	}
	@Test
	public void testTargetsLeavingRoom(){
		BoardCell cell = board.getCellAt(5, 18);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(6, 16)));
		Assert.assertTrue(targets.contains(board.getCellAt(6, 20)));
		cell = board.getCellAt(7, 7);
		board.calcTargets(cell, 6);
		targets = board.getTargets();
		Assert.assertEquals(16, targets.size());
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

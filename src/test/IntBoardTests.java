package test;

import java.util.LinkedList;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import game.BoardCell;
import game.IntBoard;

public class IntBoardTests {
	static IntBoard board;
	@BeforeClass
	public static void setBoard(){
		board = new IntBoard();
	}
	
	@Test
	public void testAdjacency0()
	{
		BoardCell cell = board.getCell(0,0);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(1, 0)));
		Assert.assertTrue(testList.contains(board.getCell(0, 1)));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testTargets0_0_1() //tests upper left corner
	{
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 1);
		Set targets = board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(0, 1)));
		Assert.assertTrue(targets.contains(board.getCell(1, 0)));
	}
	@Test
	public void testTargets0_23_1() //tests upper left corner
	{
		BoardCell cell = board.getCell(0, 23);
		board.calcTargets(cell, 1);
		Set targets = board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(0, 22)));
		Assert.assertTrue(targets.contains(board.getCell(1, 23)));
	}
	
	@Test
	public void testTargets21_23_1() //tests upper left corner
	{
		BoardCell cell = board.getCell(21, 23);
		board.calcTargets(cell, 1);
		Set targets = board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(21, 22)));
		Assert.assertTrue(targets.contains(board.getCell(20, 23)));
	}
	
	@Test
	public void testTargets0_0_3() //tests upper left corner
	{
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 3);
		Set targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(3, 0)));
		Assert.assertTrue(targets.contains(board.getCell(2, 1)));
		Assert.assertTrue(targets.contains(board.getCell(0, 1)));
		Assert.assertTrue(targets.contains(board.getCell(1, 2)));
		Assert.assertTrue(targets.contains(board.getCell(0, 3)));
		Assert.assertTrue(targets.contains(board.getCell(1, 0)));
	}
	
	@Test
	public void testTargets0_23_3() //tests upper right corner
	{
		BoardCell cell = board.getCell(0, 23);
		board.calcTargets(cell, 3);
		Set targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(0, 20)));//
		Assert.assertTrue(targets.contains(board.getCell(2, 22)));//
		Assert.assertTrue(targets.contains(board.getCell(1, 21)));//
		Assert.assertTrue(targets.contains(board.getCell(1, 23)));
		Assert.assertTrue(targets.contains(board.getCell(3, 23)));//
		Assert.assertTrue(targets.contains(board.getCell(0, 22)));//
	}
	
	@Test
	public void testTargets21_0_3() //tests lower left corner
	{
		BoardCell cell = board.getCell(21, 0);
		board.calcTargets(cell, 3);
		Set targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(18, 0)));
		Assert.assertTrue(targets.contains(board.getCell(19, 1)));
		Assert.assertTrue(targets.contains(board.getCell(20, 2)));
		Assert.assertTrue(targets.contains(board.getCell(20, 0)));
		Assert.assertTrue(targets.contains(board.getCell(21, 1)));
		Assert.assertTrue(targets.contains(board.getCell(21, 3)));
	}
	
	@Test
	public void testTargets21_23_3() //tests bottom right corner
	{
		BoardCell cell = board.getCell(21, 23);
		board.calcTargets(cell, 3);
		Set targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(21, 20)));
		Assert.assertTrue(targets.contains(board.getCell(20, 21)));
		Assert.assertTrue(targets.contains(board.getCell(19, 22)));
		Assert.assertTrue(targets.contains(board.getCell(21, 22)));
		Assert.assertTrue(targets.contains(board.getCell(20, 23)));
		Assert.assertTrue(targets.contains(board.getCell(18, 23)));
	}
	
	@Test
	public void testTargets7_10_3() //tests a middle point of the board (7, 10)
	{
		BoardCell cell = board.getCell(7, 10);
		board.calcTargets(cell, 3);
		Set targets = board.getTargets();
		Assert.assertEquals(16, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(7, 7)));//1
		Assert.assertTrue(targets.contains(board.getCell(8, 8)));//2
		Assert.assertTrue(targets.contains(board.getCell(6, 8)));//3
		Assert.assertTrue(targets.contains(board.getCell(9, 9)));//4
		Assert.assertTrue(targets.contains(board.getCell(5, 9)));//5
		Assert.assertTrue(targets.contains(board.getCell(7, 9)));//6
		Assert.assertTrue(targets.contains(board.getCell(4, 10)));//7
		Assert.assertTrue(targets.contains(board.getCell(10, 10)));//8
		Assert.assertTrue(targets.contains(board.getCell(6, 10)));//9
		Assert.assertTrue(targets.contains(board.getCell(5, 11)));//10
		Assert.assertTrue(targets.contains(board.getCell(6, 12)));//11
		Assert.assertTrue(targets.contains(board.getCell(7, 13)));//12
		Assert.assertTrue(targets.contains(board.getCell(9, 11)));//13
		Assert.assertTrue(targets.contains(board.getCell(8, 12)));//14
		Assert.assertTrue(targets.contains(board.getCell(7, 11)));//15
		Assert.assertTrue(targets.contains(board.getCell(8, 10)));//16
	}

}

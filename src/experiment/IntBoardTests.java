package experiment;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


public class IntBoardTests {
	IntBoard board;
	
	@Before	
		public void initialize(){
		board = new IntBoard(4, 4);
	}
		
	
	
	@Test
	public void testAdjTopLeftCorner() {
		BoardCell cell = board.getCell(0,0);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(1, 0)));
		Assert.assertTrue(testList.contains(board.getCell(0, 1)));
		Assert.assertEquals(2, testList.size());
	}

	
	@Test
	public void testAdjBottomRightCorner() {
		BoardCell cell = board.getCell(3,3);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(2, 3)));
		Assert.assertTrue(testList.contains(board.getCell(3, 2)));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testSecondColumn() {
		BoardCell cell = board.getCell(2,2);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(1, 2)));
		Assert.assertTrue(testList.contains(board.getCell(2, 3)));
		Assert.assertTrue(testList.contains(board.getCell(3, 2)));
		Assert.assertTrue(testList.contains(board.getCell(2, 1)));
		Assert.assertEquals(4, testList.size());
	}
	
	@Test
	public void testFirstColumn() {
		BoardCell cell = board.getCell(1,1);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(1, 2)));
		Assert.assertTrue(testList.contains(board.getCell(1, 0)));
		Assert.assertTrue(testList.contains(board.getCell(3, 2)));
		Assert.assertTrue(testList.contains(board.getCell(0, 1)));
		Assert.assertEquals(4, testList.size());
	}
	
	@Test
	public void testLeftEdge() {
		BoardCell cell = board.getCell(2, 0);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(2, 1)));
		Assert.assertTrue(testList.contains(board.getCell(3, 0)));
		Assert.assertTrue(testList.contains(board.getCell(1, 0)));
		Assert.assertEquals(3, testList.size());
	}
	
	@Test
	public void testRightEdge() {
		BoardCell cell = board.getCell(2,3);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(1, 3)));
		Assert.assertTrue(testList.contains(board.getCell(3, 1)));
		Assert.assertTrue(testList.contains(board.getCell(2, 2)));
		Assert.assertEquals(3, testList.size());
	}
	
	@Test
	public void testPath1() {
		BoardCell cell = board.getCell(2,2);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(1, 0)));
		Assert.assertTrue(testList.contains(board.getCell(0, 1)));
		Assert.assertEquals(2, testList.size());
	}

	@Test
	public void testTargets0_3()
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
	public void testTargets0_2()
	{
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 2);
		Set targets = board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(0, 2)));
		Assert.assertTrue(targets.contains(board.getCell(1, 1)));
		Assert.assertTrue(targets.contains(board.getCell(2, 0)));
	}
	
	@Test
	public void testTargets0_4()
	{
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 4);
		Set targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(1, 3)));
		Assert.assertTrue(targets.contains(board.getCell(2, 2)));
		Assert.assertTrue(targets.contains(board.getCell(1, 1)));
		Assert.assertTrue(targets.contains(board.getCell(2, 0)));
		Assert.assertTrue(targets.contains(board.getCell(3, 1)));
		Assert.assertTrue(targets.contains(board.getCell(2, 0)));
	}
	
	@Test
	public void testTargets11_2()
	{
		BoardCell cell = board.getCell(1, 1);
		board.calcTargets(cell, 2);
		Set targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(0, 0)));
		Assert.assertTrue(targets.contains(board.getCell(0, 2)));
		Assert.assertTrue(targets.contains(board.getCell(1, 3)));
		Assert.assertTrue(targets.contains(board.getCell(2, 2)));
		Assert.assertTrue(targets.contains(board.getCell(3, 1)));
		Assert.assertTrue(targets.contains(board.getCell(2, 0)));
	}
	
	@Test
	public void testTargets11_3()
	{
		BoardCell cell = board.getCell(1, 1);
		board.calcTargets(cell, 3);
		Set targets = board.getTargets();
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(1, 2)));
		Assert.assertTrue(targets.contains(board.getCell(0, 3)));
		Assert.assertTrue(targets.contains(board.getCell(1, 0)));
		Assert.assertTrue(targets.contains(board.getCell(2, 3)));
		Assert.assertTrue(targets.contains(board.getCell(3, 2)));
		Assert.assertTrue(targets.contains(board.getCell(2, 1)));
		Assert.assertTrue(targets.contains(board.getCell(3, 0)));
	}
	
	@Test
	public void testTargets11_1()
	{
		BoardCell cell = board.getCell(1, 1);
		board.calcTargets(cell, 1);
		Set targets = board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(1, 2)));
		Assert.assertTrue(targets.contains(board.getCell(0, 1)));
		Assert.assertTrue(targets.contains(board.getCell(1, 0)));
		Assert.assertTrue(targets.contains(board.getCell(2, 1)));

	}
	
}


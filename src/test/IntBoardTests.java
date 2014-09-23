package test;

import java.util.LinkedList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import game.BoardCell;
import game.IntBoard;

public class IntBoardTests {
	@Before
	public void setBoard(){
		IntBoard board = new IntBoard();
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

}

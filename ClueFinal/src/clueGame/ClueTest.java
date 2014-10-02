package clueGame;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ClueTest {

	private static Board board;
	private static final int NUM_ROOMS =11;
	private static final int NUM_ROWS = 22;
	private static final int NUM_COLUMNS = 23;

	//initializing our board, only need to do this once so static
	
	@BeforeClass
	public static void initialize() throws BadConfigFormatException{
		ClueGame game = new ClueGame("ClueLayout (2).csv", "ClueLegend.txt");
		game.loadRoomConfig();
		board = game.getBoard();
	}

	//test that we have the correct number of rooms(11)

	@Test
	public void roomsHasCorrectNumRooms() {
		//System.out.println("Testing");
		Map<Character, String> rooms = board.getRooms();
		//System.out.println("board.getRooms(): " + board.getRooms());
		// Ensure we read the correct number of rooms
		assertEquals(NUM_ROOMS, rooms.size());
		// Test retrieving a few from the hash, including the first
		// and last in the file and a few others
		assertEquals("Conservatory", rooms.get('C'));
		assertEquals("Ballroom", rooms.get('B'));
		assertEquals("Billiard room", rooms.get('R'));
		assertEquals("Dining room", rooms.get('D'));
		assertEquals("Walkway", rooms.get('W'));
	}

	//test that we have the correct number of rooms and columns
	@Test
	public void testNumRowsandCols() {
		// Ensure we have the proper number of rows and columns
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());	
	}

	//test that the door directions are correct
	//@Test

	public void testForCorrectDoorDirections(){
		// Test one each RIGHT/LEFT/UP/DOWN
		//System.out.println("I am here................");
		RoomCell room = board.getRoomCellAt(4, 3);
		//System.out.println("And now Im here!!!!!!!!!!!!!!!!!!!!!");
		assertTrue(room.isDoorway());
		//System.out.println("If you see this then isDoorwayworked" );
		assertEquals(RoomCell.DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getRoomCellAt(8, 7);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.DOWN, room.getDoorDirection());
		room = board.getRoomCellAt(3, 5);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.LEFT, room.getDoorDirection());
		room = board.getRoomCellAt(18,8);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.UP, room.getDoorDirection());
		// Test that room pieces that aren't doors know it
		assertFalse(room.isDoorway());	
		// Test that walkways are not doors
		BoardCell cell = board.getCellAt(0, 4);
		assertFalse(cell.isDoorway());	
	}

	//test that we have the correct number of doorways that we have on our map


	@Test
	public void testCountOfNumDoorways(){
		//loop to test all BoardCell objects and count the number that return true for isDoorway
		int numDoors = 0;
		int numRooms = 0;
		int totalCells = board.getNumColumns() * board.getNumRows();
		//System.out.println("board.getNumColumns: " + board.getNumColumns() + " board.getNumrows: " + board.getNumRows());
		Assert.assertEquals(506, totalCells);
		for (int row=0; row<board.getNumRows(); row++)
			for (int col=0; col<board.getNumColumns(); col++) {
				BoardCell cell = board.getCellAt(row, col);
				if (cell.isDoorway())
					numDoors++;

			}
		//System.out.println("Number of doors: " + numDoors);
		Assert.assertEquals(17, numDoors);
	}


	//test that the room initial is correct for various cells



	@Test
	public void testCorrectInitial(){
		assertEquals('C', board.getRoomCellAt(11, 0).getInitial());
		assertEquals('R', board.getRoomCellAt(7, 19).getInitial());
		assertEquals('B', board.getRoomCellAt(20, 0).getInitial());
		//System.out.println(board.getRoomCellAt(20,0));
		//assertEquals('O', board.getRoomCellAt(21, 22).getInitial());
		assertEquals('K', board.getRoomCellAt(7, 1).getInitial());
	}



	//test that an exception is thrown when it should be

	// Test that an exception is thrown for a bad config file
	@Test (expected = BadConfigFormatException.class)
	public void testBadColumns() throws BadConfigFormatException {
		// overloaded Game ctor takes config file names
		ClueGame game = new ClueGame("ClueLayoutBadColumns.csv", "ClueLegend.txt");
		// You may change these calls if needed to match your function names
		// My loadConfigFiles has a try/catch, so I can't call it directly to
		// see test throwing the BadConfigFormatException
		game.loadRoomConfig();
		game.getBoard().loadBoardConfig();
	}


	//this one gives trouble
	// Test that an exception is thrown for a bad config file



	@Test (expected = BadConfigFormatException.class)
	public void testBadRoom() throws BadConfigFormatException, FileNotFoundException {
		// overloaded Board ctor takes config file name
		ClueGame game2 = new ClueGame("ClueLayoutBadRoom.csv", "ClueLegend.txt");
		game2.loadRoomConfig();
		game2.getBoard().loadBoardConfig();
	}



	// Test that an exception is thrown for a bad config file
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoomFormat() throws BadConfigFormatException, FileNotFoundException {
		// overloaded Board ctor takes config file name
		ClueGame game = new ClueGame("ClueLayout.csv", "ClueLegendBadFormat.txt");
		game.loadRoomConfig();
		game.getBoard().loadBoardConfig();
	}


	//adjusted
	@Test
	public void testWalkwayAdjacencies() {
		BoardCell cell = board.getCellAt(9, 4);
		LinkedList<BoardCell> testList = board.getAdjList(9,4);
		Assert.assertTrue(testList.contains(board.getCellAt(8, 4)));
		Assert.assertTrue(testList.contains(board.getCellAt(10, 4)));
		Assert.assertTrue(testList.contains(board.getCellAt(9, 3)));
		Assert.assertTrue(testList.contains(board.getCellAt(9, 5)));
		Assert.assertEquals(4, testList.size());
	}

	//adjusted
	@Test
	public void testWalkwayAdjacenciesOnlyContainsWalkways() {
		BoardCell cell = board.getCellAt(14, 6);
		LinkedList<BoardCell> testList = board.getAdjList(14, 6);
		Assert.assertTrue(testList.contains(board.getCellAt(14, 5)));
		Assert.assertTrue(testList.contains(board.getCellAt(14, 7)));
		Assert.assertEquals(2, testList.size());
	}
	

	//adjusted
	@Test
	public void testAdjTopLeftCorner() {
		BoardCell cell = board.getCellAt(0, 0);
		LinkedList<BoardCell> testList = board.getAdjList(0,0);
		Assert.assertEquals(0, testList.size());
	}


	//adjusted
	@Test
	public void testAdjBottomRightCorner() {
		BoardCell cell = board.getCellAt(21, 22);
		LinkedList<BoardCell> testList = board.getAdjList(21,22);
		Assert.assertEquals(0, testList.size());
	}



	//adjusted
	@Test
	public void testLeftEdge() {
		BoardCell cell = board.getCellAt(2, 0);
		LinkedList<BoardCell> testList = board.getAdjList(2,0);
		Assert.assertEquals(0, testList.size());
	}

	//adjusted
	@Test
	public void testRightEdge() {
		BoardCell cell = board.getCellAt(12,22);
		LinkedList<BoardCell> testList = board.getAdjList(12,22);
		Assert.assertTrue(testList.contains(board.getCellAt(12, 21)));
		Assert.assertEquals(1, testList.size());
	}

	//adjusted
	@Test
	public void testBesideRoomCellNotDoorway18_16() {
		BoardCell cell = board.getCellAt(18, 16);
		LinkedList<BoardCell> testList = board.getAdjList(18, 16);
		Assert.assertTrue(testList.contains(board.getCellAt(17, 16)));
		Assert.assertTrue(testList.contains(board.getCellAt(19, 16)));
		Assert.assertTrue(testList.contains(board.getCellAt(18, 17)));
		Assert.assertEquals(3, testList.size());
	}

	//adjusted
	@Test
	public void testBesideRoomCellNotDoorway1_4() {
		BoardCell cell = board.getCellAt(1, 4);
		LinkedList<BoardCell> testList = board.getAdjList(1, 4);
		Assert.assertTrue(testList.contains(board.getCellAt(2, 4)));
		Assert.assertTrue(testList.contains(board.getCellAt(0, 4)));
		Assert.assertEquals(2, testList.size());
	}

	//need 4 of beside doorway
	//adjusted
	@Test
	public void testBesideDoorway4_14() {
		BoardCell cell = board.getCellAt(4, 14);
		LinkedList<BoardCell> testList = board.getAdjList(4, 14);
		Assert.assertTrue(testList.contains(board.getRoomCellAt(3, 14)));//this is a doorway
		Assert.assertTrue(testList.contains(board.getCellAt(4, 15)));
		Assert.assertTrue(testList.contains(board.getCellAt(4, 13)));
		Assert.assertTrue(testList.contains(board.getCellAt(5, 14)));
		Assert.assertEquals(4, testList.size());
	}


	//adjusted
	@Test
	public void testBesideDoorway19_8() {
		BoardCell cell = board.getCellAt(19, 8);
		LinkedList<BoardCell> testList = board.getAdjList(19, 8);
		Assert.assertTrue(testList.contains(board.getRoomCellAt(19, 9)));//this is a doorway
		Assert.assertTrue(testList.contains(board.getCellAt(18, 8)));
		Assert.assertTrue(testList.contains(board.getCellAt(20, 8)));
		Assert.assertTrue(testList.contains(board.getCellAt(19, 7)));
		Assert.assertEquals(4, testList.size());
	}

	//adjusted
	@Test
	public void testBesideDoorway7_18() {
		BoardCell cell = board.getCellAt(7, 18);
		LinkedList<BoardCell> testList = board.getAdjList(7,18);
		Assert.assertTrue(testList.contains(board.getRoomCellAt(8, 18)));//this is a doorway
		Assert.assertTrue(testList.contains(board.getCellAt(7, 17)));
		Assert.assertTrue(testList.contains(board.getCellAt(6, 18)));
		Assert.assertEquals(3, testList.size());
	}

	//adjusted
	@Test
	public void testBesideDoorway11_6() {
		BoardCell cell = board.getCellAt(11, 6);
		LinkedList<BoardCell> testList = board.getAdjList(11,6);
		Assert.assertTrue(testList.contains(board.getRoomCellAt(11, 5)));//this is a doorway
		Assert.assertTrue(testList.contains(board.getCellAt(10, 6)));
		Assert.assertTrue(testList.contains(board.getCellAt(11, 7)));
		Assert.assertEquals(3, testList.size());
	}
	//done with beside doorway tests
	//start locations that are doorways tests

	//adjusted
	@Test
	public void testDoorway11_5() {
		BoardCell cell = board.getCellAt(11, 5);
		LinkedList<BoardCell> testList = board.getAdjList(11, 5);
		Assert.assertTrue(testList.contains(board.getCellAt(11, 6)));
		Assert.assertEquals(1, testList.size());
	}

	//adjusted
	@Test
	public void testDoorway16_18() {
		BoardCell cell = board.getCellAt(16, 18);
		LinkedList<BoardCell> testList = board.getAdjList(16, 18);
		Assert.assertTrue(testList.contains(board.getCellAt(17, 18)));
		Assert.assertEquals(1, testList.size());
	}
	
	//adjusted
	@Test
	public void testWalkwayWithDoorway10_7()
	{
		BoardCell cell = board.getCellAt(10, 7);
		board.calcTargets(10,7, 3);
		Set targets = board.getTargets();
		Assert.assertEquals(10, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(10, 8)));//yay
		Assert.assertTrue(targets.contains(board.getCellAt(9, 7)));
		Assert.assertTrue(targets.contains(board.getRoomCellAt(8, 7)));//yay
		Assert.assertTrue(targets.contains(board.getCellAt(9, 5)));//yay
		Assert.assertTrue(targets.contains(board.getCellAt(12, 8)));//yay
		Assert.assertTrue(targets.contains(board.getCellAt(11, 7)));//yay
		Assert.assertTrue(targets.contains(board.getCellAt(13, 7)));//yay
		Assert.assertTrue(targets.contains(board.getRoomCellAt(11, 5)));//yay
		Assert.assertTrue(targets.contains(board.getCellAt(10, 6)));//yay
		Assert.assertTrue(targets.contains(board.getCellAt(10, 4)));//yay
	}
	
	//adjusted
	@Test
	public void testWalkwayWithDoorway4_4()
	{
		BoardCell cell = board.getCellAt(4,4);
		board.calcTargets(4, 4, 2);
		Set targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(4, 3)));
		Assert.assertTrue(targets.contains(board.getCellAt(2, 4)));
		Assert.assertTrue(targets.contains(board.getCellAt(3, 5)));
		Assert.assertTrue(targets.contains(board.getCellAt(4, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(5, 5)));
		Assert.assertTrue(targets.contains(board.getCellAt(6, 4)));
	}

	//adjusted
	@Test
	public void testWalkwayNoDoorway11_17()
	{
		BoardCell cell = board.getCellAt(11,17);
		board.calcTargets(11, 17, 2);
		Set targets = board.getTargets();
		Assert.assertEquals(5, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(9, 17)));
		Assert.assertTrue(targets.contains(board.getCellAt(10,18)));
		Assert.assertTrue(targets.contains(board.getCellAt(11, 19)));
		Assert.assertTrue(targets.contains(board.getCellAt(12, 18)));
		Assert.assertTrue(targets.contains(board.getCellAt(13, 17)));
	}
	

	//adjusted
	@Test
	public void testWalkwayNoDoorway12_22()
	{
		BoardCell cell = board.getCellAt(12, 22);
		board.calcTargets(12, 22, 4);
		Set targets = board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(12, 18)));
		Assert.assertTrue(targets.contains(board.getCellAt(11, 19)));
	}

	//adjusted
	@Test
	public void testWalkwayNoDoorway16_8()
	{
		BoardCell cell = board.getCellAt(16, 8);
		board.calcTargets(16, 8, 1);
		Set targets = board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(15, 8)));
		Assert.assertTrue(targets.contains(board.getCellAt(17, 8)));
		Assert.assertTrue(targets.contains(board.getCellAt(16, 7)));
		Assert.assertTrue(targets.contains(board.getCellAt(16, 9)));
	}

	//adjusted
	@Test
	public void testWalkwayNoDoorway0_4()
	{
		BoardCell cell = board.getCellAt(0, 4);
		board.calcTargets(0, 4, 3);
		Set targets = board.getTargets();
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(3, 4)));
	}

	//adjusted
	@Test
	public void testDoor19_6()
	{
		BoardCell cell = board.getCellAt(19, 6);
		board.calcTargets(19, 6, 1);
		Set targets = board.getTargets();
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(19, 7)));
	}

	//adjusted
	@Test
	public void testDoor19_20()
	{
		BoardCell cell = board.getCellAt(19, 20);
		board.calcTargets(19, 20, 1);
		Set targets = board.getTargets();
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(18, 20)));
	}
}

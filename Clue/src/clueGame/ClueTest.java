package clueGame;

import static org.junit.Assert.*;

import java.util.Map;

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
	public static void initialize(){
		ClueGame game = new ClueGame("ClueMap.csv", "legend.txt");
		game.loadRoomConfig();
		board = game.getBoard();
	}

	/*
	//test that we have the correct number of rooms(11)
	@Test
	public void roomsHasCorrectNumRooms() {
		System.out.println("Testing");
		Map<Character, String> rooms = board.getRooms();
		System.out.println("board.getRooms(): " + board.getRooms());
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
<<<<<<< HEAD
*/
=======

>>>>>>> d5740a5824928b931c0a25a5029124877cc9db7e
	//test that we have the correct number of rooms and columns
	@Test
	public void testNumRowsandCols() {
		// Ensure we have the proper number of rows and columns
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());	
	}

	//test that the door directions are correct
	@Test
	public void testForCorrectDoorDirections(){
		// Test one each RIGHT/LEFT/UP/DOWN
		RoomCell room = board.getRoomCellAt(4, 3);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getRoomCellAt(8, 7);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.DOWN, room.getDoorDirection());
		room = board.getRoomCellAt(3, 5);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.LEFT, room.getDoorDirection());
		room = board.getRoomCellAt(3, 5);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.UP, room.getDoorDirection());
		// Test that room pieces that aren't doors know it
		room = board.getRoomCellAt(17, 12);
		assertFalse(room.isDoorway());	
		// Test that walkways are not doors
		BoardCell cell = board.getCellAt(0, 4);
		assertFalse(cell.isDoorway());	
	}
/*
	//test that we have the correct number of doorways that we have on our map
	@Test
	public void testCountOfNumDoorways(){
		//loop to test all BoardCell objects and count the number that return true for isDoorway
		int numDoors = 0;
		int totalCells = board.getNumColumns() * board.getNumRows();
		Assert.assertEquals(506, totalCells);
		for (int row=0; row<board.getNumRows(); row++)
			for (int col=0; col<board.getNumColumns(); col++) {
				BoardCell cell = board.getCellAt(row, col);
				if (cell.isDoorway())
					numDoors++;
			}
		Assert.assertEquals(16, numDoors);
	}

	//test that the room initial is correct for various cells
	@Test
	public void testCorrectInitial(){
		assertEquals('C', board.getRoomCellAt(11, 0).getInitial());
		assertEquals('R', board.getRoomCellAt(7, 19).getInitial());
		assertEquals('B', board.getRoomCellAt(20, 0).getInitial());
		assertEquals('O', board.getRoomCellAt(21, 22).getInitial());
		assertEquals('K', board.getRoomCellAt(7, 1).getInitial());
	}
	//test that en exception is thrown when it should be
	@Test (expected = BadConfigFormatException.class)
	public void testExceptionThrownForBad(){
		//make it throw an exception and test to see if it did
		// overloaded Game ctor takes config file names
		ClueGame game = new ClueGame("ClueLayoutBadColumns.csv", "ClueLegend.txt");
		// You may change these calls if needed to match your function names
		// My loadConfigFiles has a try/catch, so I can't call it directly to
		// see test throwing the BadConfigFormatException
		game.loadRoomConfig();
		game.getBoard().loadBoardConfig();
	}

	@Test (expected = BadConfigFormatException.class)
	public void testExceptionThrownForRoom(){
		//make it throw an exception and test to see if it did
		// overloaded Board ctor takes config file name
		ClueGame game = new ClueGame("ClueLayoutBadRoom.csv", "ClueLegend.txt");
		game.loadRoomConfig();
		game.getBoard().loadBoardConfig();
	}

	@Test (expected = BadConfigFormatException.class)
	public void testExceptionThrownLegend(){
		//make it throw an exception and test to see if it did
		// overloaded Board ctor takes config file name
		ClueGame game = new ClueGame("ClueLayout.csv", "ClueLegendBadFormat.txt");
		game.loadRoomConfig();
		game.getBoard().loadBoardConfig();
	}
<<<<<<< HEAD
	*/
=======
	
>>>>>>> d5740a5824928b931c0a25a5029124877cc9db7e
}

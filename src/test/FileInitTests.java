package test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;
import game.BadConfigFormatException;
import game.Board;
import game.BoardCell;
import game.ClueGame;
import game.RoomCell;

import org.junit.BeforeClass;
import org.junit.Test;

public class FileInitTests {
	private static Board board;
	public static final int NUM_ROOMS = 11;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 24;
	public static final int NUM_DOORS = 23;
	public static final int NUM_CELLS = NUM_ROWS*NUM_COLUMNS;
	
	@BeforeClass
	public static void setUp() {
		ClueGame game = new ClueGame("OurClueLayout.csv", "OurClueLegend.txt");
		game.loadConfigFiles();
		board = game.getBoard();
	}

	@Test
	public void testRoomMap() {
		int expected = NUM_ROOMS;
		int actual = board.getNumRooms();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRoomMapping() {
		assertEquals("Billiard Room", board.getRoom('A'));
		assertEquals("Ballroom", board.getRoom('B'));
		assertEquals("Conservatory", board.getRoom('C'));
		assertEquals("Dining Room", board.getRoom('D'));
		assertEquals("Hall", board.getRoom('H'));
		assertEquals("Kitchen", board.getRoom('K'));
		assertEquals("Library", board.getRoom('L'));
		assertEquals("Lounge", board.getRoom('O'));
		assertEquals("Study", board.getRoom('S'));
		assertEquals("Walkway", board.getRoom('W'));
		assertEquals("Closet", board.getRoom('X'));
	}
	
	@Test
	public void testRowColumns() {
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}
	
	@Test
	public void testDoorDirection(){
		//Testing 1 door for each direction
		RoomCell room = board.getRoomCellAt(1, 0);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.UP, room.getDoorDirection());
		room = board.getRoomCellAt(14, 13);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.DOWN, room.getDoorDirection());
		room = board.getRoomCellAt(21, 20);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.LEFT, room.getDoorDirection());
		room = board.getRoomCellAt(0, 21);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.RIGHT, room.getDoorDirection());
		//testing that walkways aren't doors
		BoardCell hall = board.getCellAt(4, 3);
		assertFalse(hall.isDoorway());
		//testing room spaces that aren't doorways
		room = board.getRoomCellAt(21,23);
		assertFalse(room.isDoorway());
	}
	
	@Test
	public void testNumDoors(){
		int numDoors = 0;
		int totalCells = board.getNumColumns() * board.getNumRows();
		assertEquals(NUM_CELLS, totalCells);
		for (int row = 0; row < board.getNumRows(); row++) {
			for (int col = 0; col < board.getNumColumns(); col++) {
				BoardCell cell = board.getCellAt(row, col);
				if (cell.isDoorway())
					numDoors++;
			}
		}
		assertEquals(NUM_DOORS, numDoors);
	}
	
	@Test
	public void testCorrectInital(){
		assertEquals('A', board.getRoomCellAt(2, 1).getInitial());
		assertEquals('B', board.getRoomCellAt(17, 2).getInitial());
		assertEquals('C', board.getRoomCellAt(21, 18).getInitial());
		assertEquals('D', board.getRoomCellAt(7, 14).getInitial());
		assertEquals('H', board.getRoomCellAt(14, 6).getInitial());
		assertEquals('K', board.getRoomCellAt(5, 16).getInitial());
		assertEquals('L', board.getRoomCellAt(21, 20).getInitial());
		assertEquals('O', board.getRoomCellAt(6, 6).getInitial());
		assertEquals('S', board.getRoomCellAt(2, 13).getInitial());
		assertEquals('X', board.getRoomCellAt(9, 19).getInitial());
	}
	
	//test for an invalid initial in the layout
	@Test (expected = BadConfigFormatException.class)
	public void testBadInitial() throws BadConfigFormatException, IOException {
		ClueGame game = new ClueGame("ClueLayoutBadInitial.csv", "OurClueLegend.txt");
		game.loadRoomConfig();
		game.getBoard().loadBoardConfig("ClueLayoutBadInitial.csv", "OurClueLegend.txt");
	}
	
	//test for bad number of columns in legend file row
	@Test (expected = BadConfigFormatException.class)
	public void testBadLegendRow() throws BadConfigFormatException, IOException {
		ClueGame game = new ClueGame("OurClueLayout.csv", "ClueLegendBadRow.txt");
		game.loadRoomConfig();
		game.getBoard().loadBoardConfig("OurClueLayout.csv", "ClueLegendBadRow.txt");
	}
	
	//test for walkway having a door
	@Test (expected = BadConfigFormatException.class)
	public void testBadWalkway() throws BadConfigFormatException, IOException {
		ClueGame game = new ClueGame("ClueLayoutBadHallway.csv", "OurClueLegend.txt");
		game.loadRoomConfig();
		game.getBoard().loadBoardConfig("ClueLayoutBadHallway.csv", "OurClueLegend.txt");
	}
}

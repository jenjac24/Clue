package experiment;

public class BoardCell {
	//two variables of type int to represent row and column
	private int row;
	private int col;
	
	public BoardCell(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	public BoardCell getCell() {
		return this;
	}
}

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

	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", col=" + col + "]";
	}



	@Override
	//add equals function to tell when cells are equal
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardCell other = (BoardCell) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	
}

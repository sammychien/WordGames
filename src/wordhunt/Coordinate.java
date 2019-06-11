package wordhunt;

public class Coordinate {
	private int row;
	private int col;
	
	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	/*
	 * Given index, return Coordinate based on number of rows and columns from Params
	 * 
	 */
	public static Coordinate calcCoordinate(int paramRows, int paramCols, int index) {
		// check if index is out of range of rows/col
		if (index < 0) return null;
		if (index >= paramRows*paramCols) return null;
		
		int row = index / paramCols;
		int col = index % paramCols;
		return new Coordinate(row, col);
	}
	
	public static int calcIndexfromCoord(int paramCols, int row, int col) {
		return row * paramCols + col;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Coordinate)) return false;
		Coordinate c = (Coordinate) o; // typecast so we can compare without compilation error
		if (this.row == c.getRow() && this.col == c.getCol()) return true;
		return false;
	}
	
	
	
	
}

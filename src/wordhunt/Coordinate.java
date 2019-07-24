package wordhunt;

/**
 * <h1>Coordinate</h1>
 * The Coordinate class represents the location of something on a grid.
 * 
 * @author sammychien
 *
 */
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

	/**
	 * 
	 * @param paramRows
	 * @param paramCols
	 * @param index
	 * @return Coordinate based on number of rows and cols from Params
	 */
	public static Coordinate calcCoordinate(int paramRows, int paramCols, int index) {
		// check if index is out of range of rows/col
		if (index < 0) return null;
		if (index >= paramRows*paramCols) return null;

		int row = index / paramCols;
		int col = index % paramCols;
		return new Coordinate(row, col);
	}
	
	public static int calcIndex(int paramRows, int paramCols, Coordinate coords) {
		return coords.row * paramCols + coords.col;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Coordinate)) return false;
		Coordinate c = (Coordinate) o; // typecast so we can compare without compilation error
		if (this.row == c.getRow() && this.col == c.getCol()) return true;
		return false;
	}

	/**
	 * This method returns the next Coordinate based on input direction
	 * <p>
	 * <b>Note: </b> Coordinate returned may be an invalid Coordinate. Check validity with checkCoordValidity. 
	 * @param currentCoords
	 * @param direction
	 * @return Coordinate based on direction; 0 is E, goes CCW
	 * 
	 */
	public static Coordinate getCoordinateFromDirection(Coordinate currentCoords, int direction) {
		Coordinate newCoords = new Coordinate(currentCoords.row, currentCoords.col);
		switch(direction) {
		case 0: 
			newCoords.col++;
			break;
		case 1: 
			newCoords.col++;
			newCoords.row--;
			break;
		case 2:
			newCoords.row--;
			break;
		case 3:
			newCoords.row--;
			newCoords.col--;
			break;
		case 4:
			newCoords.col--;
			break;
		case 5:
			newCoords.col--;
			newCoords.row++;
			break;
		case 6:
			newCoords.row++;
			break;
		case 7: 
			newCoords.row++;
			newCoords.col++;
			break;
		default:
			return null;
		}
		if (checkCoordValidity(WordHuntParams.ROWS, WordHuntParams.COLS, newCoords)) {
			return newCoords;
		}
		return null;
	}

	/**
	 * This method checks the validity of the input Coordinate based on input dimensions
	 * @param rows
	 * @param cols
	 * @param coords
	 * @return true if Coordinate falls within parameter range; false otherwise
	 */
	public static boolean checkCoordValidity(int rows, int cols, Coordinate coords) {
		if (coords.col < 0 || coords.col >= cols || coords.row < 0 || coords.row >= rows) return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + this.row + ", " + this.col + ")";
	}

}

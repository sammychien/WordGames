package wordhunt;

public class Coordinate {
	private int y; // row
	private int x; // col
	
	public Coordinate(int y, int x) {
		this.y = y;
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	/*
	 * Given index, return Coordinate based on number of rows and columns from Params
	 * 
	 */
	public static Coordinate calcCoordinate(int paramRows, int paramCols, int index) {
		// check if index is out of range of rows/col
		if (index < 0) return null;
		if (index >= paramRows*paramCols) return null;
		
		int y = index / paramCols;
		int x = index % paramCols;
		return new Coordinate(y, x);
	}
	
	public static int calcIndexfromCoord(int paramCols, int y, int x) {
		return y * paramCols + x;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Coordinate)) return false;
		Coordinate c = (Coordinate) o; // typecast so we can compare without compilation error
		if (this.y == c.getY() && this.x == c.getX()) return true;
		return false;
	}
	
	
	
	
}

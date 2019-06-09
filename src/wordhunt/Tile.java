package wordhunt;

public class Tile {
	private String c;
	private Coordinate coords;
	private boolean isUsed;
	
	public String getC() {
		return c;
	}

	public Coordinate getCoordinate() {
		return coords;
	}
	
	public boolean getIsUsed() {
		return isUsed;
	}
	
	public void setUsedFlag() {
		isUsed = true;
	}

	public Tile(String c, int row, int col) {
		this.c = c;
		this.coords = new Coordinate(row, col);
		this.isUsed = false;
	}
	
	public Tile(String c, Coordinate coords) {
		this.c = c;
		this.coords = coords;
		this.isUsed = false;
	}
	
}

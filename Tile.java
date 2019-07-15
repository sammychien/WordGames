package wordhunt;

public class Tile {
	private String letter;
	private Coordinate coords;
	private boolean isUsed;
	
	public String getLetter() {
		return letter;
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

	public Tile(String letter, int row, int col) {
		this.letter = letter;
		this.coords = new Coordinate(row, col);
		this.isUsed = false;
	}
	
	public Tile(String letter, Coordinate coords) {
		this.letter = letter;
		this.coords = coords;
		this.isUsed = false;
	}
	
}

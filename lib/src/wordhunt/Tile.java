package wordhunt;

/**
 * <h1>Tile</h1>
 * The Tile class represents a single Tile (think Scrabble tile).
 * Many Tiles make up a grid, and each tile has one letter (represented as a String).
 * @author sammychien
 *
 */
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
	
	public void removeUsedFlag() {
		isUsed = false;
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
	
	/**
	 * Overridden toString() method used for debugging.
	 * Tiles show their letter; if the letter is followed by a *, then the Tile has been used.
	 */
	@Override
	public String toString() {
		if (this.isUsed) return letter+"*";
		return letter;
	}
}

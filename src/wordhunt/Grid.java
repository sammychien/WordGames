package wordhunt;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Grid</h1>
 * The Grid class represents the the WordHunt grid.
 * The grid is assumed to be rectangular, with dimensions specified in Params
 * 
 * @author sammychien
 *
 */
public class Grid {
	public int row;
	public int col;
	public List<Tile> tiles;
	
	public Grid(ArrayList<String> inputChar) {
		this.row = Params.ROWS;
		this.col = Params.COLS;
		// populate the grid with a bunch of Tiles
		this.tiles = new ArrayList<Tile>();
		for (int i = 0; i < inputChar.size(); i++) {
			Coordinate coords = Coordinate.calcCoordinate(row, col, i);
			tiles.add(new Tile(inputChar.get(i), coords));
		}
	}
	
	/**
	 * This overridden toString method is used mainly for debugging
	 */
	// TODO: Remove hardcoded indices
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(tiles.subList(0, 4).toString());
		s.append(System.getProperty("line.separator"));
		s.append(tiles.subList(4, 8).toString());
		s.append(System.getProperty("line.separator"));
		s.append(tiles.subList(8, 12));
		s.append(System.getProperty("line.separator"));
		s.append(tiles.subList(12, 16));
		return s.toString();
	}
}

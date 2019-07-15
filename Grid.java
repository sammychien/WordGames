package wordhunt;

import java.util.ArrayList;
import java.util.List;


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
}
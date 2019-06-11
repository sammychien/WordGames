package wordhunt;

import java.util.ArrayList;
import java.util.List;


public class Grid {
	public int row;
	public int col;
	public List<Tile> tiles;
	
	public Grid(ArrayList<Character> inputChar) {
		this.row = Params.ROWS;
		this.col = Params.COLS;
		// populate the grid with a bunch of Tiles
		this.tiles = new ArrayList<Tile>();
		for (int i = 0; i < inputChar.size(); i++) {
			Coordinate coords = Coordinate.calcCoordinate(row, col, i);
			tiles.add(new Tile(inputChar.get(i).toString(), coords));
		}
		
	}

	/*
	 * Returns true if Tile at Coordinate coords is unused (i.e. used flag is false)
	 */
	public boolean checkUsedFlag(Coordinate coords) {
		// TODO: finish this function 
		return false;
	}

	
	
	
}

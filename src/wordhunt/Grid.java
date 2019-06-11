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
		// also want to throw an error if something happens here
		int coordsX = coords.getCol(); int coordsY = coords.getRow();
		Tile tileAtCoord = tiles.get(Coordinate.calcIndexfromCoord(Params.COLS, coordsY, coordsX));	
		// this should be the tile that we're talking about.
		// double check that tileAtCoord has the same coordinates as coordsX and coordsY
		if (!(coordsX == tileAtCoord.getCoordinate().getCol() && coordsY == tileAtCoord.getCoordinate().getRow())) {
			// throw new exception here
		}
		
		return false;
	}

	
	
	
}

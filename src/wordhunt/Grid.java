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
	 * Returns true if Tile at Coordinate coords is used (i.e. used flag is true)
	 * 
	 * TODO: Make unit test for these
	 * 
	 */
	public boolean checkUsedFlag(Coordinate coords) throws Exception {
		int coordsX = coords.getX(); int coordsY = coords.getY();
		return checkUsedFlag(coordsX, coordsY);
	}
	
	public boolean checkUsedFlag(int x, int y) throws Exception {
		Tile tileAtCoord = tiles.get(Coordinate.calcIndexfromCoord(Params.COLS, y, x));	
		
		// this should be the tile that we're talking about.
		// double check that tileAtCoord has the same coordinates as coordsX and coordsY
		if (!(x == tileAtCoord.getCoordinate().getX() && y == tileAtCoord.getCoordinate().getY())) {
			throw new Exception();
		}
		return tileAtCoord.getIsUsed();
	}

	public Tile getTile(Coordinate coords) {
		return getTile(coords.getX(), coords.getY());
	}
	
	public Tile getTile(int x, int y) {
		if (isWithinBounds(x, y)) {
			return tiles.get(Coordinate.calcIndexfromCoord(col, y, x));
		} else {
			return null;
		}
	}
	
	/*
	 *  returns true if (x, y) are within the bounds of the grid
	 *  otherwise returns false
	 */
	public boolean isWithinBounds(int x, int y) {
		if (x >= 0 && x < col && y >= 0 && y < row) return true;
		return false;
	}
	
}

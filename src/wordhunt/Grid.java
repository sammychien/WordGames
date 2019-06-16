package wordhunt;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;


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
	
	/*
	 *  Want to pass in current tile and grid
	 *  don't forget about removing all breadcrumbs before exiting!
	 */
	public ArrayList<String> solveBFS(Tile currentTile, SortedSet<String> dictionary) {
		ArrayList<String> list = new ArrayList<String>();
		
		// make prefix
		String prefix = "";
		prefix += currentTile.getLetter();
		/*
		 * TreeSet is already a sorted set
		 * However, I need a way to check whether a prefix of a word exists inside the treeset with a O(log n)
		 * This is difficult since the TreeSet only checks against the entire word. 
		 * For example, if my prefix were walle, the dictionary doesn't have walle and it would give back false
		 * However, I could still get wallet, so I want the function to give me a true
		 * Basically, I want the search function to also return fragments
		 * 
		 * Fuck it
		 * make this not very optimized
		 * check when word is between 3-8 characters long
		 * 
		 * 
		 */
		list.add(prefix);
		return list;
	}
	
	protected ArrayList<String> solveBFS(Tile currentTile, SortedSet<String> dictionary, 
			String prefix, ArrayList<String> resultList) { 
		
		// if prefix is 8 letters long, return
		if (prefix.length() >= Params.maxWordLen) {
			// remove breadcrumb from this tile
			currentTile.setUsedFlag(false);
			return resultList;
		}
		
		// if prefix matches a word in the dictionary, 
		if (WordHunt.isPrefixInDict(dictionary, prefix)) {
			resultList.add(prefix);
		} // keep going since there might be more words
		
		/*
		 * Now check all surrounding tiles
		 * First check whether or not it can go to the next directions
		 */
		
		for (int i = 0; i < 8; i++) {
			Coordinate newCoordinate = ableToMove(i, currentTile.getCoordinate());
			if (newCoordinate != null) {
				// move to next tile
				// add breadcrumb
				Tile newTile = this.getTile(newCoordinate);
				// MAKE SURE THIS IS NOTTTT A SHALLOW COPY
				this.getTile(newCoordinate).setUsedFlag(true);
				prefix = prefix + newTile.getLetter();
				return this.solveBFS(newTile, dictionary, prefix, resultList);
			}
		}
		// remove breadcrumb
		currentTile.setUsedFlag(false);
		return resultList;
	}
	
	/*
	 * Determines ability to move based on current coordinates and direction. 
	 * 0: E
	 * 1: NE
	 * 2: N
	 * 3: NW
	 * 4: W
	 * 5: SW
	 * 6: S
	 * 7: SE
	 * 
	 * if parameter "direction" is not an integer between [0,7], then function will return false.
	 */
	protected Coordinate ableToMove(int direction, Coordinate coords) {
		// use cases to determine movement
		
		int coordsX = coords.getX(); int coordsY = coords.getY();
		switch(direction) {
		case 0:
			coordsX += 1;
		case 1:
			coordsX += 1;
			coordsY -= 1;
		case 2:
			coordsY -= 1;
		case 3:
			coordsX -= 1;
			coordsY -= 1;
		case 4:
			coordsX -= 1;
		case 5:
			coordsX -= 1;
			coordsY += 1;
		case 6:
			coordsY += 1;
		case 7:
			coordsX += 1;
			coordsY += 1;
		default:
			coordsX = -1;
			coordsY = -1;
		}
		
		if (coordsX >= 0 && coordsX < Params.COLS && coordsY >= 0 && coordsY < Params.ROWS) {
			// new coordinate within bounds
			// check if it lands on prior breadcrumb
			try {
				if (!this.checkUsedFlag(coordsX, coordsY)) {
					return new Coordinate(coordsY, coordsX);
				}
			} catch (Exception e) {
				System.out.println("Error in Tile retrieval index");
				e.printStackTrace();
			}
		}
		return null;
	}
	
}

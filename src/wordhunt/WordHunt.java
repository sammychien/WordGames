package wordhunt;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import initializations.FileCreation;
import stringEdits.StringLengthComparator;
import stringEdits.StringMethods;

/**
 * <h1>WordHunt</h1>
 * The WordHunt program solves the Word Hunt game on iMessage
 * <p>
 * Assumes 4x4 configuration for the game
 * 
 * @author sammychien
 *
 */
public class WordHunt {
	
	public static void main(String[] args) {
		// create Trie
		TrieNode root = TrieNode.createRootTrieNode();

		// Init file reader/writers
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = FileCreation.initializeBR(WordHuntParams.inputFile);
			writer = FileCreation.initializeBW(WordHuntParams.outputFile);
			root.populateTrie(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// algorithm
		Set<String> results = solve(root, readInput());
		ArrayList<String> sortedResults = setToSortedArray(results, new StringLengthComparator());
		
		// write and print results
		try {
			for (String s: sortedResults) {
				writer.write(s);
				writer.newLine();
			}
			writer.close();
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().edit(new File(WordHuntParams.outputFile));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method transfers Set to a sorted ArrayList
	 * <p>
	 * Sorting occurs via the input Comparator
	 * @param <T> Usually a String
	 * @param set Set to be sorted
	 * @param comparator 
	 * @return Sorted ArrayList<T>
	 */
	public static <T> ArrayList<T> setToSortedArray(Set<T> set, Comparator<T> comparator) {
		ArrayList<T> sortedResults = new ArrayList<T>();
		for (T s : set) {
			sortedResults.add(s);
		}
		sortedResults.sort(comparator);
		return sortedResults;
	}
	
	/**
	 * This method gets the user input from keyboard
	 * <p>
	 * <b>Note: </b> Assumes rectangular grid for WordHunt
	 * 
	 * @return ArrayList<String> from User
	 */
	private static ArrayList<String> readInput() {
		Scanner input = new Scanner(System.in);
		int cols = WordHuntParams.COLS; int rows = WordHuntParams.ROWS;
		ArrayList<String> list = new ArrayList<String>();
		System.out.println("Enter " + cols + " Letters " + rows+ " times:");
		for (int i = 0; i < cols; i++) {
			String[] stringArray = StringMethods.toStringArray(input.next());
			for (int j = 0; j < rows; j++) {
				list.add(stringArray[j]);
			}
		}
		input.close();
		return list;
	}

	/**
	 * This method is the recursive algorithm that solves WordHunt
	 * @param root The root TrieNode that acts as dictionary
	 * @param input The user input 
	 * @return Set of words that can be created from user input
	 */
	private static Set<String> solve(TrieNode root, ArrayList<String> input) {
		Set<String> results = new TreeSet<String>();
		Grid grid = new Grid(input); // Populate the Grid
		for (int i = 0; i < grid.tiles.size(); i++) {
			Tile t = grid.tiles.get(i);
			// solve the grid starting from this tile
			results = solve(root, grid, results, t, "");
		}
		return results;
	}

	/**
	 * Helper function for the main solve() method
	 * @param root
	 * @param grid
	 * @param results
	 * @param t
	 * @param prefix
	 * @return
	 */
	private static Set<String> solve(TrieNode root, Grid grid, Set<String> results, Tile t, String prefix) {
		
		prefix += t.getLetter();
		t.setUsedFlag();
		
		if (!root.doesPrefixExist(prefix)) {
			// no more you can do here, return 
			t.removeUsedFlag();
			return results;
		}
		if (root.doesWordExist(prefix)) {
			results.add(prefix);
		}
		// prefix exists here 
		// check the surrounding tiles, if the tiles haven't been visited
		for (int direction = 0; direction < 8; direction++) {
			Coordinate currentCoords = t.getCoordinate();
			Coordinate nextCoords = Coordinate.getCoordinateFromDirection(currentCoords, direction);
			if (nextCoords == null) continue;
			int tileIndexInGrid = Coordinate.calcIndex(WordHuntParams.ROWS, WordHuntParams.COLS, nextCoords);
			if (grid.tiles.get(tileIndexInGrid).getIsUsed()) continue;
			else {
				// next tile is not null and isn't used; add this to the prefix and keep going 
				results = solve(root, grid, results, grid.tiles.get(tileIndexInGrid), prefix);
			}
		}
		t.removeUsedFlag();
		return results;
	}
	
}

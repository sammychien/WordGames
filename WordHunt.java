package wordhunt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Scanner;


import sbee.FileCreation;

public class WordHunt {

	// TODO: Replace ArrayList with HashSet

	private static BufferedReader reader;
	private static BufferedWriter writer;


	public static void main(String[] args) {
		reader = null; writer = null;

		TrieNode root = TrieNode.createRootTrieNode();


		try {
			reader = FileCreation.initializeBR("FilteredWords.txt");
			writer = FileCreation.initializeBW("WordHuntSoln.txt");
			root.populateTrie(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<String> input = readInput();


		ArrayList<String> results = new ArrayList<String>();



		System.out.println(input);

	}

	private static ArrayList<String> readInput() {
		Scanner input = new Scanner(System.in);
		int cols = Params.COLS; int rows = Params.ROWS;
		ArrayList<String> list = new ArrayList<String>();
		System.out.println("Enter " + cols + " Letters " + rows+ " times:");
		for (int i = 0; i < cols; i++) {
			String[] stringArray = TrieNode.toStringArray(input.next());
			for (int j = 0; j < rows; j++) {
				list.add(stringArray[j]);
			}
		}
		input.close();
		return list;
	}

	// we want to do Breadth first search
	/* 
	 * start with a tile/prefix
	 * if the prefix is in the dictionary, add to it with the adjacent tiles
	 * now the prefix is larger. Keep doing this until the prefix matches a word
	 * if the prefix matches a word, add the word to the results list.
	 * if the prefix becomes too large or the prefix is not the start to any word in the dictionary, then backtrack and try a different adjacent tile
	 * if there are no more adjacent tiles, backtrack (simply return)
	 * 
	 */
	private static ArrayList<String> solve(TrieNode root, ArrayList<String> input) {
		ArrayList<String> results = new ArrayList<String>();
		Grid grid = new Grid(input); // Populate the Grid
		for (int i = 0; i < grid.tiles.size(); i++) {
			Tile t = grid.tiles.get(i);
			t.setUsedFlag();
			// solve the grid starting from this tile
			results = solve(root, grid, results);
		}
		return results;
	}

	private static ArrayList<String> solve(TrieNode root, Grid grid, ArrayList<String> results) {
		
		
		return results;
	}
	
	
}
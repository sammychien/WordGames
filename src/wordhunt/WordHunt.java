package wordhunt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import sbee.FileCreation;

public class WordHunt {

	// TODO: Replace ArrayList with HashSet
	
	private static BufferedReader reader;
	private static BufferedWriter writer;
	
	public static void main(String[] args) {
		// System.out.println(readInput());
		reader = null; writer = null;
		SortedSet<String> masterWordList = new TreeSet<String>();
		try {
			reader = FileCreation.initializeBR("FilteredWords.txt");
			writer = FileCreation.initializeBW("WordHuntSoln.txt");
			masterWordList = getMasterWordList(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<Character> input = readInput();
		// now we have masterWordList and inputList
		
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
		Set<String> results = solve(input, masterWordList);
		
		System.out.println(results);

	}

	private static ArrayList<Character> readInput() {
		Scanner letterInput = new Scanner(System.in);
		int cols = Params.COLS; int rows = Params.ROWS;
		ArrayList<Character> list = new ArrayList<Character>();
		System.out.println("Enter " + cols + " Letters " + rows+ " times:");
		for (int i = 0; i < cols; i++) {
			char[] userInputCharArray = letterInput.next().toCharArray();
			for (int j = 0; j < rows; j++) {
				list.add(userInputCharArray[j]);
			}
		}
		letterInput.close();
		return list;
	}

	private static SortedSet<String> getMasterWordList(BufferedReader reader) throws IOException {
		SortedSet<String> set = new TreeSet<String>();
		String word = reader.readLine();
		while (word != null) {
			set.add(word);
			word = reader.readLine();
		}
		return set;
	}
	
	private static Set<String> solve(ArrayList<Character> inputChar, SortedSet<String> dictionary) {
		Set<String> results = new HashSet<String>();
		// instantiate the grid
		Grid grid = new Grid(inputChar);
		// grid has Map<Coordinate, Tile> 
		
		
		// go through each tile from anew 
		for (int i = 0; i < grid.tiles.size(); i++) {
			// start with this particular tile
			Tile t = grid.tiles.get(i); 
			results.addAll(solveBFS(t, grid, dictionary));
		}
		return results;
	}
	
	/*
	 *  Want to pass in current tile and grid
	 *  don't forget about removing all breadcrumbs before exiting!
	 */
	private static ArrayList<String> solveBFS(Tile currentTile, Grid grid, SortedSet<String> dictionary) {
		ArrayList<String> list = new ArrayList<String>();
		
		// make prefix
		String prefix = "";
		prefix += currentTile.getC();
		
		// Let's begin to do this BFS stuff
		
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
	
	
	private static ArrayList<String> solveBFS(Tile currentTile, Grid grid, SortedSet<String> dictionary, 
			String prefix, ArrayList<String> resultList) { 
		
		// if prefix is 8 letters long, return
		if (prefix.length() >= Params.maxWordLen) return resultList;
		
		// if prefix matches a word in the dictionary, 
		if (isPrefixInDict(dictionary, prefix)) {
			resultList.add(prefix);
		} // keep going since there might be more words
		
		// now check all surrounding tiles
		
		
		
		
		return null;
	}
	
	/*
	 * determine whether or not the prefix is in the dictionary
	 * NOT VERY OPTIMIZED RIGHT NOW
	 * TODO: Make O(log n) algorithm
	 */
	private static boolean isPrefixInDict(SortedSet<String> dictionary, String prefix) {
		for (String s: dictionary) {
			if (prefix.equals(s)) return true;
		}
		return false;
	}
	
	
	
	
}

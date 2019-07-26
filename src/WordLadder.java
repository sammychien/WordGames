package wordLadder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class WordLadder {



	public static void main(String[] args) throws Exception {

		ArrayList<String> input;	// list containing start and end words
		ArrayList<String> ladder;	// word ladder
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file, for student testing and grading only
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default input from Stdin
			ps = System.out;			// default output to Stdout
		}
		input = parse(kb);
		while (input.size() != 0) {
			// get word ladder via BFS and DFS and print both
			ladder = getWordLadderBFS(input.get(0), input.get(1));
			printLadder(ladder);
			ladder = getWordLadderDFS(input.get(0), input.get(1));
			printLadder(ladder);
			// get new start and end words
			input = parse(kb);
		}

	}

	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		String start = keyboard.next();
		if (start.equals("/quit")) return new ArrayList<String>();
		String end = keyboard.next();
		if(end.equals("/quit")) return new ArrayList<String>();

		ArrayList<String> list = new ArrayList<String>();
		list.add(start);
		list.add(end);
		return list;
	}

	/**
	 * This method uses Depth-First Search to find a wordLadder between
	 * two words of equal length. This may or may not be the smallest
	 * such ladder between the two words.
	 * If no such ladder exists between the two words, then the ArrayList
	 * will simply hold the beginning and end word
	 * @param start 		This is the starting word for the ladder
	 * @param end			This is the ending word for the ladder
	 * @return wordLadder	Returns ArrayList with the wordLadder
	 */
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		// Returned list should be ordered start to end.  Include start and end.
		// If ladder is empty, return list with just start and end.
		Set<String> dict = makeDictionary();
		Graph graph = new Graph(dict);
		ArrayList<String> ladder = new ArrayList<String>();
		Node startNode = graph.findNode(start.toLowerCase());
		Node endNode = graph.findNode(end.toLowerCase());
		if (startNode == null || endNode == null) {
			ladder.add(start);
			ladder.add(end);
			return ladder;
		}
		Stack<Node> stack = new Stack<Node>();
		stack.add(startNode);
		if (getWordLadderDFS(stack, endNode)) {
			return stackToLadder(ladder, stack);
		} else {
			ladder.add(start);
			ladder.add(end);
			return ladder;
		}
	}

	/**
	 * This is a helper function to getWordLadderDFS, and this is 
	 * recursively called to determine whether or not a wordLadder
	 * exists between the two words. The stack parameter is modified
	 * between calls to this function
	 * @param stack		Implements DFS with stack, initialized with start word
	 * @param end		Ending node, remains constant
	 * @return boolean	Whether or not ladder has been found/made
	 */
	public static boolean getWordLadderDFS(Stack<Node> stack, Node end) {
		if (stack.isEmpty()) return false;
		Node thisNode = stack.peek();
		if (thisNode.equals(end)) return true;
		if (!thisNode.discovered) {
			thisNode.discovered = true;
			ArrayList<Node> sorted = WordLadder.reduceDFS(thisNode, end);
			for (Node n : sorted) {
				stack.push(n);
				if (getWordLadderDFS(stack, end)) {
					return true;
				} else {
					stack.pop();
				}
			}
		}
		return false;
	}

	/**
	 * This function creates a new ArrayList of Nodes that are sorted by
	 * the amount of differing letters each neighboring node has from 
	 * the ending node.
	 * 
	 */
	public static ArrayList<Node> reduceDFS(Node startNode, Node end) {
		ArrayList<Node> startNodeNeighbors = new ArrayList<Node>(); 
		startNodeNeighbors.addAll(startNode.getNeighbors());
		startNodeNeighbors.sort(new WordLadderComparator(end));
		return startNodeNeighbors;
	}

	/**
	 * This function populates ladder with the words inside the Nodes in stack
	 * @param ladder
	 * @param stack
	 * @return 
	 */
	public static ArrayList<String> stackToLadder(ArrayList<String> ladder, Stack<Node> stack) {
		for (Node n : stack) {
			ladder.add(n.getWord());
		}
		return ladder;
	}

	/**
	 * get the word ladder via breadth-first search
	 * @param start starting string for word ladder
	 * @param end ending string for word ladder
	 * @return word ladder as array list of strings
	 */
	public static ArrayList<String> getWordLadderBFS(String start, String end) {

		Set<String> dict = makeDictionary();
		ArrayList<String> ladder = new ArrayList<String>();
		Graph graph = new Graph(dict);
		Node startNode = graph.findNode(start.toLowerCase());
		Node endNode = graph.findNode(end.toLowerCase());
		if (startNode == null || endNode == null) {
			ladder.add(start);
			ladder.add(end);
			return ladder;
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(startNode);
		
		if (getWordLadderBFS(endNode, queue, ladder)) return reverseLadder(ladder);
		else {
			ladder.add(start);
			ladder.add(end);
			return ladder;
		}

	}

	/**
	 * Helper function for getting the word ladder. Called recursively. Modifies ladder.
	 * @param end the end Node or "goal"
	 * @param queue a queue containing nodes to examine
	 * @param ladder reference for the final word ladder to return, modified
	 * @return whether word ladder was found
	 */
	private static boolean getWordLadderBFS(Node end, Queue<Node> queue, ArrayList<String> ladder) {
		if (queue.isEmpty()) return false;
		Node thisNode = queue.remove();
		thisNode.discovered = true;
		if (thisNode.equals(end)) {
			getWordLadderBFS(thisNode, ladder);
			return true;
		} else { // thisNode is not the end
			for (Node n : thisNode.getNeighbors()) {
				if (!n.discovered) {
					queue.add(n);
					n.setParent(thisNode);
				}
			}
			return getWordLadderBFS(end, queue, ladder);
		}
	}

	/**
	 * Helper for getWordLadderBFS. Recursively adds nodes to ladder starting with end node.
	 * @param n current node in ladder
	 * @param ladder word ladder. modified.
	 */
	private static void getWordLadderBFS(Node n, ArrayList<String> ladder) {
		// gets word ladder in reverse
		ladder.add(n.getWord());
		if (n.getParent() == null) return;
		getWordLadderBFS(n.getParent(), ladder);
	}

	/**
	 * Reverse a given word ladder in place.
	 * @param ladder the word ladder to be reversed
	 * @return the new word ladder
	 */
	private static ArrayList<String> reverseLadder(ArrayList<String> ladder) {
		ArrayList<String> newLadder = new ArrayList<String>();
		for (int i= ladder.size()-1; i >= 0; i--) {
			newLadder.add(ladder.get(i));
		}
		return newLadder;
	}

	/**
	 * print the word ladder
	 * @param ladder the word ladder in an array list
	 */
	public static void printLadder(ArrayList<String> ladder) {
		if (ladder.size() == 2) {
			// print "No word ladder can be found between <start> and <finish>
			System.out.println("no word ladder can be found between " + ladder.get(0) + " and " + ladder.get(1));
		} else {
			System.out.println("a " + (ladder.size() - 2) + "-rung ladder exists between " + ladder.get(0) + " and " + ladder.get(ladder.size() - 1));
			for (String word : ladder) {
				System.out.println(word);
			}
		}
	}

	/* Do not modify makeDictionary */
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File(WordLadderParams.inputDictFileName));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toLowerCase());
		}
		return words;
	}



}

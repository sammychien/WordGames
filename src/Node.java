package wordLadder;

import java.util.HashSet;
import java.util.Set;

public class Node {

	private String word;
	private Set<Node> neighbors; // neighbors are the words that are same length as word, but differ by 1 letter
	boolean discovered;
	private Node parent;
	
	public Node(String word) {
		neighbors = new HashSet<Node>();
		this.discovered = false;
		this.word = word;
		this.setParent(null);
	}
	
	/*
	 * Returns number of letter differences between nodeA and nodeB
	 * If the two nodes have words of different length, returns -1
	 */
	public static int compareLetters(Node nodeA, Node nodeB) {
		if (nodeA.getWord().length() != nodeB.getWord().length()) return -1;
		// lengths of words should be same now
		int differences = 0;
		char[] nodeAArray = nodeA.getWord().toCharArray();
		char[] nodeBArray = nodeB.getWord().toCharArray();
		for (int index = 0; index < nodeA.getWord().length(); index++) {
			if (nodeAArray[index] != nodeBArray[index]) {
				differences++;
			}
		}
		return differences;
	}
	
	public String getWord() {
		return this.word;
	}
	
	public Set<Node> getNeighbors() {
		return this.neighbors;
	}
	
	public boolean getDiscoveredFlag() {
		return this.discovered;
	}
	
	public void setDiscoveredFlag(boolean bool) {
		this.discovered = bool;
	}
	
	public void addEdge(Node neighboringNode) {
		this.neighbors.add(neighboringNode);
	}
	
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	@Override
	public String toString() {
		return this.word;
	}
	
}

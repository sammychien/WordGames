package wordhunt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import stringEdits.StringMethods;

/**
 * <h1>TrieNode</h1>
 * The TrieNode class is an implementation of a Trie with Strings
 * <p>
 * 
 * @author sammychien
 *
 */
public class TrieNode {

	private String letter;
	private Map<String, TrieNode> children;
	private TrieNode parent;

	public TrieNode(String letter, TrieNode parent) {
		this.letter = letter;
		children = new HashMap<String, TrieNode>();
		this.parent = parent;
	}
	
	/**
	 * This method creates a root node for the Trie
	 * @return The root node
	 */
	public static TrieNode createRootTrieNode() {
		return new TrieNode("*", null);
	}

	/**
	 * This method adds the input String to the Trie and accepts any node in the Trie.
	 * <p>
	 * <b>Note:</b> Acts upon any TrieNode
	 * 
	 * @param word This is the String that will be added to the Trie
	 */
	public void addWordToTrie(String word) {
		// find root node
		TrieNode node = findRootTrieNode(this);

		// if the word already exists, return
		if (node.doesWordExist(word)) return;

		String[] wordArray = StringMethods.toStringArray(word);
		// add stuff to the node
		for (int i = 0; i < wordArray.length; i++) {
			// if the letter already exists, then don't add a new node
			String wordLetter = wordArray[i];
			if (!node.children.containsKey(wordLetter)) {
				// add a new entry in node's children map
				node.children.put(wordLetter, new TrieNode(wordLetter, node));
			}
			// at this point, the node exists
			node = node.children.get(wordLetter);
		}
		// at the end, place a * as one of the children of the previous node
		node.children.put("*", new TrieNode("*", node));
	}

	/**
	 * This is a recursive method to find root node
	 * @param n Any node in the Trie
	 * @return The root TrieNode
	 */
	public static TrieNode findRootTrieNode(TrieNode n) {
		if (n == null) return null;
		if (n.letter == "*" && n.parent == null) return n;
		return findRootTrieNode(n.parent);
	}

	/**
	 * This method determines if the input String exists within the Trie
	 * <p>
	 * <b>Note:</b> Acts upon any TrieNode
	 * 
	 * @param word The search parameter
	 */
	public boolean doesWordExist(String word) {
		TrieNode node = findRootTrieNode(this);
		// word needs to exist completely and the children of the last letter must have "*"
		String[] wordArray = StringMethods.toStringArray(word);
		for (int i = 0; i < wordArray.length; i++) {
			String subWord = wordArray[i];
			if (!node.children.containsKey(subWord)) {
				return false;
			}
			node = node.children.get(subWord);
		}
		// now node is the last TrieNode of the word.
		// check if the children of node has "*"
		if (node.children.containsKey("*")) return true;
		return false;
	}

	/**
	 * This method populates the Trie with a text file using a BufferedReader
	 * <p>
	 * <b>Notes:</b> Acts upon any TrieNode; every word in the text file must be separated by a newline character
	 * 
	 * @param reader BufferedReader which reads the dictionary text file
	 * @throws IOException 
	 */
	public void populateTrie(BufferedReader reader) throws IOException {
		TrieNode root = findRootTrieNode(this);
		String word = reader.readLine();
		while (word != null) {
			root.addWordToTrie(word);
			word = reader.readLine();
		}
	}

	/**
	 * This method determines if the prefix exists within the Trie
	 * <p>
	 * <b>Note:</b> Acts upon any TrieNode
	 * 
	 * @param prefix The search parameter
	 */
	public boolean doesPrefixExist(String prefix) {
		TrieNode node = findRootTrieNode(this);

		String[] prefixArray = StringMethods.toStringArray(prefix);

		for (int i = 0; i < prefixArray.length; i++) {
			String subPrefix = prefixArray[i];
			if (!node.children.containsKey(subPrefix)) {
				return false;
			}
			node = node.children.get(subPrefix);
		}
		return true;
	}
}

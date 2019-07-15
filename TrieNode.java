package wordhunt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TrieNode {

	private String letter;
	private Map<String, TrieNode> children;
	private TrieNode parent;
	
//	public static void main(String[] args) {
//		TrieNode root = createRootTrieNode();
//		root.addWordToTrie("many");
//		root.addWordToTrie("man");
//		root.addWordToTrie("my");
//		root.addWordToTrie("lie");
//		root.addWordToTrie("a");
//		System.out.println(root.doesPrefixExist("ma"));
//		System.out.println(root.doesWordExist("ma"));
//	}

	public TrieNode(String letter, TrieNode parent) {
		this.letter = letter;
		children = new HashMap<String, TrieNode>();
		this.parent = parent;
	}
	
	public static TrieNode createRootTrieNode() {
		return new TrieNode("*", null);
	}
	
	/**
	 * Must act upon a root node
	 * if word already exists in the trie, return false
	 * 
	 * @param word to be added to the trie
	 * @return 
	 */
	public void addWordToTrie(String word) {
		// find root node
		TrieNode node = findRootTrieNode(this);

		String[] wordArray = toStringArray(word);
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

	public static TrieNode findRootTrieNode(TrieNode n) {
		if (n == null) return null;
		if (n.letter == "*" && n.parent == null) return n;
		return findRootTrieNode(n.parent);
	}

	public static String[] toStringArray(String word) {
		String[] returnArr = new String[word.length()];
		for (int i = 0; i < word.length(); i++) {
			returnArr[i] = word.substring(i, i+1);
		}
		return returnArr;
	}

	public boolean doesWordExist(String word) {
		TrieNode node = findRootTrieNode(this);
		// word needs to exist completely and the children of the last letter must have "*"
		String[] wordArray = toStringArray(word);
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
	 * Assume that root is passed in
	 * @param reader
	 * @throws IOException
	 */
	public void populateTrie(BufferedReader reader) throws IOException {
		String word = reader.readLine();
		while (word != null) {
			this.addWordToTrie(word);
			word = reader.readLine();
		}
	}
	
	/**
	 * Determines if prefix exists within the trie
	 * 
	 * @return
	 */
	public boolean doesPrefixExist(String prefix) {
		TrieNode node = findRootTrieNode(this);
		
		String[] prefixArray = toStringArray(prefix);
		
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

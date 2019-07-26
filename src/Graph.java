package wordLadder;

import java.util.HashSet;
import java.util.Set;

public class Graph {

	private Set<Node> nodes;
	
	public Graph(Set<String> dict) {
		nodes = new HashSet<Node>();
		for (String str : dict) {
			Node newNode = new Node(str.toLowerCase());
			nodes.add(newNode);
		}
		createEdges();
	}
	
	private void createEdges() {
		for (Node firstNode : nodes) {
			for (Node secNode : nodes) {
				if (firstNode != secNode) {
					if (Node.compareLetters(firstNode, secNode) == 1) {
						// add the edges
						firstNode.addEdge(secNode);
					}
				}
			}
		}
	}
	
	public Node findNode(String word) {
		for (Node n : nodes) {
			if (n.getWord().toLowerCase().equals(word.toLowerCase())) return n;
		}
		return null;
	}
	
	
}

package wordLadder;

import java.util.Comparator;

public class WordLadderComparator implements Comparator<Node> {

	Node endNode;
	
	public WordLadderComparator(Node endNode) {
		this.endNode = endNode;
	}
	
	@Override
	public int compare(Node arg0, Node arg1) {
		int letterDiff0 = Node.compareLetters(arg0, endNode);
		int letterDiff1 = Node.compareLetters(arg1, endNode);
		return letterDiff0 - letterDiff1;
	}
	
}

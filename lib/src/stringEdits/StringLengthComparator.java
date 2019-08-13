package stringEdits;

import java.util.Comparator;

/**
 * <h1>StringLengthComparator</h1>
 * The StringLengthComparator compares strings by their length (longer strings > smaller strings)
 * <p>
 * If String lengths are the same, sorts them based on default String sort
 * 
 * @author sammychien
 *
 */
public class StringLengthComparator implements Comparator<String> {

	@Override
	public int compare(String arg0, String arg1) {
		/*
		 * What we want to do is first compare by string length
		 * if those are equal, then sort by regular string compare
		 */
		int val = arg1.length() - arg0.length();
		if (val != 0) return val;
		// string lengths are same
		return arg0.compareTo(arg1);
	}
	
}

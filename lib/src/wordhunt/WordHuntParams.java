package wordhunt;

import initializations.FileNames;

/**
 * <h1>Params</h1>
 * The Params class wraps all the configuration parameters needed for WordHunt
 * 
 * @author sammychien
 *
 */
public class WordHuntParams {
	public final static int ROWS = 4;
	public final static int COLS = 4;
	public final static int minWordLen = 3;
	public final static int maxWordLen = 7;
	public final static String inputFile = FileNames.dictionaryDirectory + "/FilteredWords.txt";
	public final static String outputFile = FileNames.solutionsDirectory + "/WordHuntSoln.txt";
}

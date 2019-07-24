package initializations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <h1>Sort</h1>
 * The Sort class filters and sorts raw dictionary text files
 * 
 * @author sammychien
 *
 */
public class Sort {

	private static BufferedReader input;
	private static BufferedWriter filteredWriter;
	private static String inputFile = "files/Dictionaries/Most_Common_100K.txt";
	private static String filteredOutput = "files/Dictionaries/FilteredWords.txt";
	private final static int wordCap = 8;
	private final static int wordMin = 3;
	
	public static void main(String[] args) {
		try {
			initialize();
			ArrayList<String> list = filter();
			writeToFile(list, filteredWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes readers/writers
	 * @throws IOException
	 */
	private static void initialize() throws IOException { 
		input = FileCreation.initializeBR(inputFile);
		filteredWriter = FileCreation.initializeBW(filteredOutput);
	}
	
	/**
	 * Filters out words without letters and makes all letters lowercase
	 */
	private static ArrayList<String> filter() throws IOException {
		
		ArrayList<String> list = new ArrayList<String>();
		boolean isLetterFlag = true;
		String word = input.readLine();
		while (word != null) {
			for (int i = 0; i < word.length(); i++) {
				char ch = word.charAt(i);
				if (!Character.isLetter(ch)) {
					isLetterFlag = false;
					break;
				}
			}
			if (isLetterFlag) {
				list.add(word.toLowerCase());
			}
			isLetterFlag = true;
			word = input.readLine();
		}
		list.sort(null);
		list = wordLenCap(list);
		return list;
	}
	
	/**
	 * 
	 * @return ArrayList with words from file
	 * @throws IOException
	 */
	private static void writeToFile(ArrayList<String> list, BufferedWriter writer) throws IOException {
		for (String s : list) {
			writer.write(s);
			writer.newLine();
		}
	}
	
	/**
	 * Returns ArrayList with word length between WordMin and WordCap
	 * Deletes duplicate words
	 * @param list
	 * @return
	 */
	private static ArrayList<String> wordLenCap(ArrayList<String> list) {
		ArrayList<String> returnList = new ArrayList<String>();
		String lastElem = "";
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			if (s.length() <= wordCap && s.length() >= wordMin && !lastElem.equals(s)) returnList.add(s);
			lastElem = s;
		}
		return returnList;
	}

}

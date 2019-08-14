package spellingBee;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import initializations.FileCreation;
import initializations.FileNames;
import stringEdits.StringMethods;


public class SpellingBee {

	public static final int NUMLETTERS = 7;
	public static final String dictionaryName = FileNames.dictionaryDirectory + "/FilteredWords.txt";
	public static final String outputFile = FileNames.solutionsDirectory + "/SpellingBeeSoln.txt";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] wholeWord = SpellingBee.getWholeWord(scanner);
		String middleLetter = SpellingBee.getMiddleLetter(scanner);
		scanner.close();
		File dictionary = new File(dictionaryName);
		ArrayList<String> list = null; //init
		try {
			list = SpellingBee.solve(wholeWord, middleLetter, dictionary);
			list.sort(new stringEdits.StringLengthComparator());
			BufferedWriter writer = FileCreation.initializeBW(outputFile);
			for (String s : list) {
				writer.write(s);
				writer.newLine();
			}
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		System.out.println(list);

		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().edit(new File(outputFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String[] getWholeWord(Scanner scanner) {
		System.out.println("Enter " + NUMLETTERS + " Letters: ");
		String wholeWord = scanner.next();
		String[] letterList = StringMethods.toStringArray(wholeWord);
		return letterList;
	}

	public static String getMiddleLetter(Scanner scanner) {
		System.out.println("Enter the middle letter: ");
		String middleLetter = "";
		while (middleLetter.isEmpty()) {
			middleLetter = scanner.next();
		}
		return middleLetter;
	}

	public static ArrayList<String> solve(String[] wholeWord, String middleLetter, File dictionary) throws IOException {
		ArrayList<String> list = new ArrayList<String>();

		Scanner input = new Scanner(dictionary);
		System.out.println("Here are today's words: ");

		while (input.hasNextLine()) {
			//check if the word at input[d_index] has the necessary things
			String currentWord = input.nextLine();
			if (StringMethods.hasMiddleLetter(currentWord, middleLetter)) {
				// check if word has only the given letters
				if (StringMethods.hasOnlyLetterList(currentWord, wholeWord)) {
					list.add(currentWord);
				}
			}
		}
		input.close();
		return list;
	}

}


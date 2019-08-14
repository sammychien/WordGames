package anagrams;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import initializations.FileCreation;
import initializations.FileNames;

public class Anagrams {

	public final static int MAXLEN = 8;
	public final static int MINLEN = 4;
	public final static int USERINPUT = 8; // must be same as MAXLEN
	public final static String OUTPUTFILE = FileNames.solutionsDirectory + "/AnagramsSoln.txt";
	public final static String INPUTFILEPREFIX = FileNames.xLetterWords;

	public static void main(String[] args) {
		char[] userInput = readInput();
		
		try {
			BufferedWriter writer = FileCreation.initializeBW(OUTPUTFILE);
			BufferedReader reader = null; //placeholder
			ArrayList<String> list = solve(userInput, writer, reader);
			
			System.out.println(list);
			
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().edit(new File (OUTPUTFILE));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static char[] readInput() {
		Scanner letterInput = new Scanner(System.in);
		System.out.println("Enter " + USERINPUT + " Letters: ");
		char[] userInputCharArray = letterInput.next().toCharArray();
		letterInput.close();
		return userInputCharArray;
	}
	
	public static ArrayList<String> solve(char[] userInput, BufferedWriter writer, BufferedReader reader) throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		for (int numLetters = MAXLEN; numLetters >= MINLEN; numLetters--) {
			String fileName = INPUTFILEPREFIX + numLetters + "LetterWords.txt";
			reader = FileCreation.initializeBR(fileName);
			writeToFile(userInput, reader, writer, numLetters, list);
		}
		reader.close();
		return list;
	}
	
	public static ArrayList<String> writeToFile(char[] inputArray, BufferedReader reader, BufferedWriter writer, int numLetters, ArrayList<String> list) {
		try {
			list.addAll(appendToFile(inputArray, reader, writer, numLetters, list));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<String> appendToFile(char[] array, BufferedReader reader, BufferedWriter writer, int numLetters, ArrayList<String> list) throws IOException {
		String line = reader.readLine().toLowerCase();

		int numMatches = 0;
		while (line != null) {
			line = line.toLowerCase();
			char[] userArray = array.clone();
			char[] lineArray = line.toCharArray();
			numMatches = 0;
			for (int i = 0; i < USERINPUT; i++) {
				for (int j = 0; j < numLetters; j++) {
					if (userArray[i] == lineArray[j]) {
						numMatches++;
						// set both those letters to something weird
						userArray[i] = '&';
						lineArray[j] = '%';
						break;
					}
				}
			}
			if (numMatches == numLetters) {
				writer.write(line);
				writer.newLine();
				list.add(line);
			}
			line = reader.readLine();
		}
		return list;
	}
}
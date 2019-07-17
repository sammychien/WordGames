package sbee;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Anagrams {
	
	public static int numLetters = 6;
	private static int userInput = 6;
	private static BufferedReader reader;
	private static BufferedWriter writer;
	private static ArrayList<String> list;
	
	/*
	 * Read the file
	 * Get user input for letters
	 * print 
	 */
	
	public static void main(String[] args) {
		char[] userInput = readInput();
		list = new ArrayList<String>();
		
		try {
			reader = FileCreation.initializeBR("files/6LetterWords.txt");
			writer = FileCreation.initializeBW("files/LetterWordsSoln.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeToFile(userInput);
		
		numLetters = 5;
		try {
			reader = FileCreation.initializeBR("files/5LetterWords.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeToFile(userInput);
		
		numLetters = 4;
		try {
			reader = FileCreation.initializeBR("files/4LetterWords.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeToFile(userInput);
		
		System.out.println(list);
		
		try {
			reader.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().edit(new File ("files/LetterWordsSoln.txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void writeToFile(char[] input) {
		try {
			appendToFile(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static char[] readInput() {
		Scanner letterInput = new Scanner(System.in);
		System.out.println("Enter 6 Letters: ");
		char[] userInputCharArray = letterInput.next().toCharArray();
		letterInput.close();
		return userInputCharArray;
	}
	
	public static void appendToFile(char[] array) throws IOException {
		String line = reader.readLine().toLowerCase();
		
		int numMatches = 0;
//		boolean letterFound = false;
		while (line != null) {
			line = line.toLowerCase();
			char[] userArray = array.clone();
			char[] lineArray = line.toCharArray();
			numMatches = 0;
			for (int i = 0; i < userInput; i++) {
				for (int j = 0; j < Anagrams.numLetters; j++) {
					if (userArray[i] == lineArray[j]) {
						numMatches++;
						// set both those letters to something weird
						userArray[i] = '&';
						lineArray[j] = '%';
						break;
					}
				}
			}
			if (numMatches == Anagrams.numLetters) {
				writer.write(line);
				writer.newLine();
				list.add(line);
			}
			line = reader.readLine();
		}
		
	}
}
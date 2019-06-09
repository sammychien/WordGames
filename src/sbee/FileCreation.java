package sbee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileCreation {

	private static BufferedReader input;
	private static String fileName1 = "FilteredWords.txt";

	public static void main(String[] args) {
		try {
			for (int i = 4; i < 9; i++) {
				input = initializeBR(fileName1);
				writeLetterWords(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Scanner initializeScanner(String fileName) throws FileNotFoundException {
		return new Scanner(new File(fileName));
	}
	
	public static BufferedReader initializeBR(String fileName) throws FileNotFoundException {
		return new BufferedReader(new FileReader(new File(fileName)));
	}
	
	public static FileWriter initializeFW(String fileName) throws IOException {
		return new FileWriter(new File(fileName));
	}

	public static BufferedWriter initializeBW(String fileName) throws IOException {
		return new BufferedWriter(new FileWriter(fileName));
	}
	
	public static void writeLetterWords(int i) throws IOException {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(new Integer(i).toString() + "LetterWords.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean isLetterFlag = true;
		String word = input.readLine();
		while (word != null) {
			if (word.length() == i) {
				for (int j = 0; j < i; j++) {
					char ch = word.charAt(j);
					if (!Character.isLetter(ch) /*|| Character.isUpperCase(ch)*/) {
						// is not a letter, break
						isLetterFlag = false;
						break;
					}
				}
				if (isLetterFlag) {
					out.write(word);
					out.newLine();
				}
				isLetterFlag = true;
			}
			word = input.readLine();
		}
		System.out.println();
		out.close();
	}
}



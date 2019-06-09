package sbee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Driver {

	public static void main(String[] args) {
		
		String[] letterList = new String[SBeeLetters.numLetters];
		String middleLetter = "";
		
		Scanner letterInput = new Scanner(System.in);
		System.out.println("Enter 7 Letters: ");
		for (int i = 0; i < SBeeLetters.numLetters; i++) { 
			letterList[i] = letterInput.next();
		}
		
		System.out.println("Enter the middle letter: ");
		
		while (middleLetter.isEmpty()) {
			middleLetter = letterInput.nextLine();
		}
		
		letterInput.close();
		
		File dictionary = new File("dictionary.txt");
		
		try {
			
			Scanner input = new Scanner(dictionary);
			System.out.println("Here are today's words: ");
			
			while (input.hasNextLine()) {
				//check if the word at input[d_index] has the necessary things
				String currentWord = input.nextLine();
				if (wordChecker.hasMiddleLetter(currentWord, middleLetter)) {
					// check if word has only the given letters
					if (wordChecker.hasOnlyLetterList(currentWord, letterList)) {
						System.out.println(currentWord);
					}
				}
			}
			input.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

}

/*
 * Base case: entire maze is solved (return true)
 * Base case: current input does not work (involves checking rows, columns, and current box) (return false)
 * 
 * 
 * if base cases are not met, then try adding a number (1-9) to the next index of the sudoku puzzle
 * return search(##, next index)
 * 
 * 
 * 
 */





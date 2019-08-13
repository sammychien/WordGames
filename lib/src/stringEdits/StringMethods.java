package stringEdits;

public class StringMethods {
	
	/**
	 * This method splits the input String into an array of Strings. Each String in the array has 1 character.
	 * @param word The String to be split
	 * @return Array of the split Strings 
	 */
	public static String[] toStringArray(String word) {
		String[] returnArr = new String[word.length()];
		for (int i = 0; i < word.length(); i++) {
			returnArr[i] = word.substring(i, i+1);
		}
		return returnArr;
	}
	
	/**
	 * This method determines whether a letter is present in the input String
	 * @param inputWord
	 * @param middleLetter
	 * @return
	 */
	public static boolean hasMiddleLetter(String inputWord, String middleLetter) {
		for (int i = 0; i < inputWord.length(); i++) {
			if (middleLetter.equals(inputWord.substring(i, i+1).toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method determines whether the inputWord has only letters in the LetterList String array.
	 * <p>
	 * For example: 
	 * <p>
	 * inputWord = "abcde" | LetterList = [a, b, c, d, e, f] returns true
	 * <p>
	 * inputWord = "abcde" | LetterList = [a, b, d, e, f] returns false
	 *  
	 * @param inputWord
	 * @param LetterList
	 * @return
	 */
	public static boolean hasOnlyLetterList(String inputWord, String[] LetterList) {
		for (int i = 0; i < inputWord.length(); i++) {
			
			boolean hasLetter = false;
			for (int j = 0; j < LetterList.length; j++) {
				if (LetterList[j].equals(inputWord.substring(i, i+1).toLowerCase())) {
					hasLetter = true;
					break;
				}
			}
			if (!hasLetter) {
				return false;
			}
			
		}
		return true;
	}
	
}

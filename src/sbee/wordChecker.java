package sbee;

public class wordChecker {
	
	public static boolean hasMiddleLetter(String inputWord, String middleLetter) {
		if (inputWord.length() < 4) {
			return false;
		}
		
		for (int i = 0; i < inputWord.length(); i++) {
			if (middleLetter.equals(inputWord.substring(i, i+1).toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
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

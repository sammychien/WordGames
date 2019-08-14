package initializations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileEditing {
	
	/**
	 *  This basically just writes edits to a new file, not very optimal but whatever
	 */

	private static BufferedReader reader;
	private static BufferedWriter writer;
	
	public static void main(String[] args) throws IOException {
		try {
			reader = FileCreation.initializeBR(FileNames.dictionaryDirectory + "/wiki-100k.txt");
			writer = FileCreation.initializeBW(FileNames.dictionaryDirectory + "/Most_Common_100K.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		removeComments();
	}
	
	public static void removeComments() throws IOException {
		String line = reader.readLine();
		while (line != null) {
			if (!line.startsWith("#!comment")) {
				writer.write(line);
				writer.newLine();
			}
			line = reader.readLine();
		}
	}

}

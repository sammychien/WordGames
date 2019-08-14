package initializations;

import java.nio.file.Paths;

/**
 * <h1>FileNames</h1> The FileNames class defines file paths for methods
 * 
 * @author sammychien
 *
 */
public class FileNames {
    public final static String rootDirectory = Paths.get("").toAbsolutePath().toString();
    public final static String fileDirectory = rootDirectory + "/lib/files";
    public final static String dictionaryDirectory = fileDirectory + "/Dictionaries";
    public final static String solutionsDirectory = fileDirectory + "/Solutions";
    public final static String xLetterWords = fileDirectory + "/XLetterWords";

}
package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class PartsOfSpeech {

    private final String STOPLIST_FILE_PATH = "src/main/data/Stoplist.english.txt";

    private HashSet<String> commonSpeech;

    /**
     * Create new PartsOfSpeech object
     * 
     * @throws FileNotFoundException if file cannot be found
     * @throws IOException           if error occurs while reading from file
     */
    public PartsOfSpeech() throws FileNotFoundException, IOException {
        this.commonSpeech = new HashSet<String>();
        loadData(STOPLIST_FILE_PATH);
    }

    /**
     * Takes words from .txt file and stores in HashSet
     * 
     * @param filePath path of file containing common parts of speech
     * @throws FileNotFoundException if file cannot be found
     * @throws IOException           if error occurs while reading from file
     */
    private void loadData(String filePath) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        while (reader.ready()) {
            commonSpeech.add(reader.readLine());
        }
        reader.close();
    }

    /**
     * Check to see if value is in list of parts of speech
     * 
     * @param valueToCheck single word to check
     * @return true if value matches any words in list of parts of speech
     */
    public boolean isPartOfSpeech(String valueToCheck) {
        valueToCheck = valueToCheck.toLowerCase();
        for (String word : commonSpeech) {
            if (word.equals(valueToCheck))
                return true;
        }
        return false;
    }
}

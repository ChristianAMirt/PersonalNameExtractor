package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Jaylen Wheeler
 *         Determines if a word is part of the English dictionary
 */
public class DictionaryFeature {

    /**
     * instantiation of this class as an object.
     */
    public DictionaryFeature() {}

    /**
     * Runs a string through a list of (up to) 120,000 words in the English Language
     * for comparison between the token's value and the word.
     * 
     * Source: aspell English dictionary, with affixes expanded
     * 
     * @param myWord
     * 
     * @throws FileNotFoundException 
     * @throws IOException
     * 
     * @return boolean value
     */
    public boolean determineDictionaryFeature(String myWord) throws FileNotFoundException, IOException {

        BufferedReader myReader = null;

        myReader = new BufferedReader(new FileReader("src/main/data/Dictionary.english.txt"));

        String dictionaryLine = myReader.readLine();

        // Compares every line of the dictionary file with the string value
        // and returns true if they are equal.
        // Otherwise, it returns false.
        while (dictionaryLine != null) {

            if (dictionaryLine.equals(myWord)) {
                myReader.close();
                return true;
            }

            dictionaryLine = myReader.readLine();
        }
        myReader.close();
        return false;
    }

}

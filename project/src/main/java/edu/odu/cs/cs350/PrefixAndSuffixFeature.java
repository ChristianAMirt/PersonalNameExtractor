package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Jaylen Wheeler
 *         Determines if a word is a prefix or suffix
 */
public class PrefixAndSuffixFeature {

    /**
     * instantiation of this class as an object.
     */
    public PrefixAndSuffixFeature() {}

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
    public boolean determinePrefixFeature(String myWord) throws FileNotFoundException, IOException {

        BufferedReader myReader = null;

        myReader = new BufferedReader(new FileReader("src/main/data/Dictionary.prefixes.txt"));

        String prefixLine = myReader.readLine();

        // Compares every line of the dictionary file with the string value
        // and returns true if they are equal.
        // Otherwise, it returns false.
        while (prefixLine != null) {

            if (prefixLine.equals(myWord)) {
                myReader.close();
                return true;
            }

            prefixLine = myReader.readLine();
        }
        myReader.close();
        return false;
    }
}

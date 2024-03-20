package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Jaylen Wheeler
 *         Determines if a word is part of the English dictionary
 */
public class DictionaryFeature {

    /**
     * instantiation of this class as an object.
     */
    public DictionaryFeature() {

    }

    /**
     * Runs a token threw a list of (up to) 120,000 words in the English Language
     * for comparison between the token's value and the word.
     * 
     * Source: aspell English dictionary, with affixes expanded
     * 
     * @return boolean value
     */
    public boolean determineDictionaryFeature(Token myWord) throws FileNotFoundException, IOException {

        BufferedReader myReader = null;

        File myFIle = new File("Dictionary.english.txt");
        String path = myFIle.getAbsolutePath();
        // System.out.println(f.exists());
        // System.out.println(f.isDirectory());
        // System.out.println(f.canRead());
        // System.out.println(f.getAbsolutePath());
        // System.out.println(new File(".").getAbsolutePath());
        

        myReader = new BufferedReader(new FileReader(path));

        String dictionaryLine = myReader.readLine();

        // Compares every line of the dictionary file with the token's value
        // and returns true if they are equal
        while ((dictionaryLine = myReader.readLine()) != null) {
            if (dictionaryLine == myWord.getValue()) {
                myReader.close();
                return true;
            }
        }
        myReader.close();
        return false;
    }

}

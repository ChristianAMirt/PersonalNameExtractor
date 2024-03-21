package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Jaylen Wheeler
 *         Determines if a word is a kill word or not
 */
public class KillWordFeature {

    /**
     * instantiation of this class as an object.
     */
    public KillWordFeature() {
    }

    /**
     * Runs a singular token through a list of designated kill words
     * for comparison between the token's value and the word.
     * 
     * Source: collected by ODU Extract project team, primarily from
     * experience with DTIC documents.
     * 
     * @param word
     * 
     * @throws FileNotFoundException
     * @throws IOException
     * 
     * @return boolean value
     */
    public boolean determineKillWordFeature(Token word) throws FileNotFoundException, IOException {

        BufferedReader myReader = null;

        myReader = new BufferedReader(new FileReader("src/main/data/Dictionary.nonPersonalProperNames.txt"));

        String killWordLine = myReader.readLine();

        // Compares every line of the kill word file with the token's value
        // and returns true if they are equal.
        // Otherwise, it returns false.\
        while (killWordLine != null) {
            if (killWordLine.contains(word.getValue())) {
                myReader.close();
                return true;
            }
            killWordLine = myReader.readLine();
        }
        myReader.close();
        return false;

    }

    /**
     * Runs two tokens through a list of designated kill words
     * for comparison between the token's value and the word.
     * 
     * Source: collected by ODU Extract project team, primarily from
     * experience with DTIC documents.
     * 
     * @param firstWord
     * @param secondWord
     * 
     * @throws FileNotFoundException
     * @throws IOException
     * 
     * @return boolean value
     */
    public boolean determineMultipleKillWordFeatures(Token firstWord, Token secondWord)
            throws FileNotFoundException, IOException {

        BufferedReader myReader = null;

        myReader = new BufferedReader(new FileReader("src/main/data/Dictionary.nonPersonalProperNames.txt"));

        String killWordLine = myReader.readLine();

        // Compares every line of the kill word file with the 
        // values of both tokens and returns
        // true if they are equal.
        // Otherwise, it returns false.\
        while (killWordLine != null) {
            if (killWordLine.contains(firstWord.getValue()) && killWordLine.contains(secondWord.getValue())) {
                myReader.close();
                return true;
            }
            killWordLine = myReader.readLine();
        }
        myReader.close();

        return false;
    }
}

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
    public KillWordFeature(){}

    public boolean determineKillWordFeature(Token word) throws FileNotFoundException, IOException{

        BufferedReader myReader = null;

        myReader = new BufferedReader(new FileReader("src/main/data/Dictionary.nonPersonalProperNames.txt"));

        String killWordLine = myReader.readLine();
        System.out.println(killWordLine);

        // Compares every line of the dictionary file with the token's value
        // and returns true if they are equal.
        // Otherwise, it returns false.\
        while (killWordLine != null) {
            if (killWordLine.contains(word.getValue())){
                myReader.close();
                return true;
            }
            killWordLine = myReader.readLine();
        }
        myReader.close();
        return false;

    }

    public boolean determineMultipleKillWordFeatures(Token word) throws FileNotFoundException, IOException{
        // implementation in progress
        return false;
    }
}

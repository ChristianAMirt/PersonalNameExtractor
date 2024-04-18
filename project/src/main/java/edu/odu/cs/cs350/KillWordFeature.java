package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Determines if a word is a kill word or not.
 * @author Jaylen Wheeler
 */
public class KillWordFeature{

    /**
     * Constructor for killWordFeature
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public KillWordFeature() throws FileNotFoundException, IOException{
        this.killWordSet = new HashSet<>();
        loadFile();
    }

    /**
     * Contains a list of kill words
     */
    private Set<String> killWordSet;

    /**
     * Opens a file of equal name to
     * param myFile, returning a reader
     * for that file.
     * 
     * @param myFile
     * @return FileReader
     * @throws FileNotFoundException
     */
    public FileReader openFile(String myFile) throws FileNotFoundException{
        return new FileReader(new File(myFile));
    }

    /**
     * Returns the killWordSet
     * 
     * @return Set<String>
     */
    public Set<String> getKillWordSet(){
        return killWordSet;
    }

    /**
     * Loads information from a file into a hashset
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void loadFile() throws FileNotFoundException, IOException{
        BufferedReader myReader = null;
        myReader = new BufferedReader(openFile("src/main/data/Dictionary.nonPersonalProperNames.txt"));
        String fileLine = myReader.readLine();
        while(fileLine != null){
            killWordSet.add(fileLine);
            fileLine = myReader.readLine();
        }
        myReader.close();
    }

    /**
     * Runs a singular string through a list of designated kill words
     * for comparison between the string value and the word.
     * 
     * Source: collected by ODU Extract project team, primarily from
     * experience with DTIC documents.
     * 
     * @param word
     * 
     * @throws FileNotFoundException
     * @throws IOException
     * 
     * @return boolean value.
     */
    public boolean determineKillWordFeature(String word) 
            throws FileNotFoundException, IOException {

        for(String line: killWordSet)
        {
            if(line.equals(word))
            {
                return true;
            }
        }
        return false;

    }

    /**
     * Runs two strings through a list of designated kill words
     * for comparison between the string values and the word.
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
     * @return boolean value.
     */
    public boolean determineMultipleKillWordFeatures(String firstWord, String secondWord)
            throws FileNotFoundException, IOException {

        for(String line: killWordSet)
        {
            if(line.equals(firstWord + " " + secondWord))
            {
                return true;
            }
        }
        return false;
    }
}

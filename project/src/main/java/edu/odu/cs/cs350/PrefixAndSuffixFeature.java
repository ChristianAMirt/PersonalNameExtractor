package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Determines if a word is a prefix or suffix.
 * @author Jaylen Wheeler
 */
public class PrefixAndSuffixFeature{

    /**
     * Constructor for PrefixAndSuffixFeature.
     */
    public PrefixAndSuffixFeature() throws FileNotFoundException, IOException{
        this.prefixSet = new HashSet<>();
        this.suffixSet = new HashSet<>();
        loadPrefix();
        loadSuffix();
    }

    /**
     * Contains the prefixes.
     */
    private Set<String> prefixSet;

    /**
     * Contains the suffixes.
     */
    private Set<String> suffixSet;

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
     * Returns the prefixSet.
     * 
     * @return Set
     */
    public Set<String> getPrefixSet(){
        return prefixSet;
    }

    /**
     * Returns the suffixSet.
     * 
     * @return Set
     */
    public Set<String> getSuffixSet(){
        return suffixSet;
    }

    /**
     * Loads information from the prefix file into a hashset.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void loadPrefix() throws FileNotFoundException, IOException{
        
        BufferedReader myReader = null;
        myReader = new BufferedReader(openFile("src/main/data/Dictionary.prefixes.txt"));
        String fileLine = myReader.readLine();
        while(fileLine != null){
            prefixSet.add(fileLine);
            fileLine = myReader.readLine();
        }
        myReader.close();
    }

    /**
     * Loads information from the suffix file into a hashset.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void loadSuffix() throws FileNotFoundException, IOException{
        
        BufferedReader myReader = null;
        myReader = new BufferedReader(openFile("src/main/data/Dictionary.suffixes.txt"));
        String fileLine = myReader.readLine();
        while(fileLine != null){
            suffixSet.add(fileLine);
            fileLine = myReader.readLine();
        }
        myReader.close();
    }


    /**
     * Runs a string through a list of prefixes
     * for comparison between the token's value and the word.
     * 
     * Source: collected by ODU Extract project team, primarily from
     * experience with DTIC documents.
     * 
     * @param myWord
     * 
     * @throws FileNotFoundException 
     * @throws IOException
     * 
     * @return boolean value.
     */
    public boolean determinePrefixFeature(String myWord) throws FileNotFoundException, IOException {

        for(String line: prefixSet)
        {
            if(line.equals(myWord))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Runs a string through a list of suffixes
     * for comparison between the token's value and the word.
     * 
     * Source: collected by ODU Extract project team, primarily from
     * experience with DTIC documents.
     * 
     * @param myWord
     * 
     * @throws FileNotFoundException 
     * @throws IOException
     * 
     * @return boolean value.
     */
    public boolean determineSuffixFeature(String myWord) throws FileNotFoundException, IOException {
        
        for(String line: suffixSet)
        {
            if(line.equals(myWord))
            {
                return true;
            }
        }
        return false;
    }
}

package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Determines if a word is part of the English dictionary.
 * 
 * @author Jaylen Wheeler
 */
public class DictionaryFeature {

    /**
     * Constructor for DictionaryFeature
     */
    public DictionaryFeature() throws FileNotFoundException, IOException {
        this.englishDictionarySet = new HashSet<>();
        loadFile();
    }

    /**
     * Contains around 120,000 words from the english dictionary
     */
    private Set<String> englishDictionarySet;

    /**
     * Opens a file of equal name to
     * param myFile, returning a reader
     * for that file.
     * 
     * @param myFile
     * @return FileReader
     * @throws FileNotFoundException
     */
    public FileReader openFile(String myFile) throws FileNotFoundException {
        return new FileReader(new File(myFile));
    }

    /**
     * Returns the englishDictionarySet
     * 
     * @return
     */
    public Set<String> getEnglishDictionarySet() {
        return englishDictionarySet;
    }

    /**
     * Loads information from a file into a hashset
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void loadFile() throws FileNotFoundException, IOException {
        BufferedReader myReader = null;
        myReader = new BufferedReader(openFile("src/main/data/Dictionary.english.txt"));
        String fileLine = myReader.readLine();
        while (fileLine != null) {
            englishDictionarySet.add(fileLine);
            fileLine = myReader.readLine();
        }
        myReader.close();
    }

    /**
     * Runs a string through a list of (up to) 120,000 words in the English Language
     * for comparison between the token's value and the word.
     * 
     * Source: aspell English dictionary, with affixes expanded.
     * 
     * @param myWord
     * 
     * @throws FileNotFoundException
     * @throws IOException
     * 
     * @return boolean value.
     */
    public boolean determineDictionaryFeature(String myWord) throws FileNotFoundException, IOException {
        return englishDictionarySet.contains(myWord.trim().toLowerCase());

        // for(String dictionaryLine: englishDictionarySet)
        // {
        // if(dictionaryLine.equals(myWord))
        // {
        // return true;
        // }
        // }
        // return false;
    }

}

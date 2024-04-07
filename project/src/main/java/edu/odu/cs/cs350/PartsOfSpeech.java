package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 * Determines the parts of speech of a token
 */
public class PartsOfSpeech {

    /**
     * Constant to hold filepath for list of conjuctions
     */
    private final String CONJUNCTIONS_FILE_PATH = "src/main/data/Conjunctions.txt";

    /**
     * Hashset to store list of common conjuctions
     */
    private HashSet<String> commonConjunctions;

    /**
     * Create new PartsOfSpeech object
     * 
     * @throws FileNotFoundException if file cannot be found
     * @throws IOException           if error occurs while reading from file
     */
    public PartsOfSpeech() throws FileNotFoundException, IOException {
        this.commonConjunctions = new HashSet<String>();
        loadData(CONJUNCTIONS_FILE_PATH);
    }

    /**
     * Checks for all parts of speech
     * 
     * @param valueToCheck single Token value to check
     * @return what part of speech value is or other if none apply
     */
    public String checkForPartsOfSpeech(String valueToCheck) {
        if (isPeriod(valueToCheck))
            return "period";
        if (isComma(valueToCheck))
            return "comma";
        if (isHyphen(valueToCheck))
            return "hyphen";
        if (isConjunction(valueToCheck))
            return "conjunction";
        if (isArticle(valueToCheck))
            return "article";
        return "other";
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
            commonConjunctions.add(reader.readLine());
        }
        reader.close();
    }

    /**
     * Check to see if value is in list of conjunctions
     * 
     * @param valueToCheck single Token value to check
     * @return true if value matches any words in list of conjunctions
     */
    public boolean isConjunction(String valueToCheck) {
        valueToCheck = valueToCheck.toLowerCase();
        for (String word : commonConjunctions) {
            if (word.equals(valueToCheck))
                return true;
        }
        return false;
    }

    /**
     * Checks to see if value is one of the three English articles (a, an, the)
     * function may mark the initial "A" as an article
     * 
     * @param valueToCheck single Token value to check
     * @return true if value is an article
     */
    public boolean isArticle(String valueToCheck) {
        valueToCheck = valueToCheck.toLowerCase();
        if (valueToCheck.equals("a") || valueToCheck.equals("an")
                || valueToCheck.equals("the"))
            return true;
        return false;
    }

    /**
     * Check to see if value is a period
     * 
     * @param valueToCheck single Token value to check
     * @return true if value is a period
     */
    public boolean isPeriod(String valueToCheck) {
        if (valueToCheck.equals("."))
            return true;
        return false;
    }

    /**
     * Check to see if value is a comma
     * 
     * @param valueToCheck single Token value to check
     * @return true if value is a comma
     */
    public boolean isComma(String valueToCheck) {
        if (valueToCheck.equals(","))
            return true;
        return false;
    }

    /**
     * Check to see if value is a hyphen
     * 
     * @param valueToCheck single Token value to check
     * @return true if value is a hyphen
     */
    public boolean isHyphen(String valueToCheck) {
        if (valueToCheck.equals("-"))
            return true;
        return false;
    }
}

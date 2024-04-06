package edu.odu.cs.cs350;

import weka.core.Instances;
//import weka.core.pmml.jaxbbindings.Attribute;
import weka.core.Attribute;
import weka.core.FastVector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * The Trainer class is responsible for:
 * - Creating a data set for WEKA
 */
@SuppressWarnings({"deprecation", "unchecked"})
public class TrainingDataInstances {
    
    /**
     * Stores the common first names.
     */
    String[] commonFirstNames = readFile("src/main/data/CommonFirstNames.txt");

    /**
     * Attribute for common first names.
     */
    Attribute commonFirstNameValue = new Attribute("commonFirstNameValue", fastV(commonFirstNames));

    /**
     * Stores the common last names.
     */
    String[] commonLastNames = readFile("src/main/data/CommonLastNames.txt");

    /**
     * Attribute for common last names.
     */
    Attribute commonLastNameValue = new Attribute("commonLastNameValue", fastV(commonLastNames));

    /**
     * Stores the author first names.
     */
    String[] authorFirstNames = readFile("src/main/data/Dictionary.firstNames.txt");

    /**
     * Attribute for author first names.
     */
    Attribute authorFirstNameValue = new Attribute("authorFirstNameValue", fastV(authorFirstNames));

    /**
     * Stores the author last names.
     */
    String[] authorLastNames = readFile("src/main/data/Dictionary.lastNames.txt");

    /**
     * Attribute for the author last names.
     */
    Attribute authorLastNameValue = new Attribute("authorLastNameValue", fastV(authorLastNames));

    /**
     * Stores the honorifics.
     */
    String[] honorifics = readFile("src/main/data/Dictionary.honorifics.txt"); 

    /**
     * Attribute for the honorifics.
     */
    Attribute honorificsValue = new Attribute("honorificsValue", fastV(honorifics));

    /**
     * Stores the English dictionary words.
     */
    String[] englishDictionaryWords = readFile("src/main/data/Dictionary.english.txt");

    /**
     * Attribute for the English dictionary words.
     */
    Attribute englishDictionary = new Attribute("englishDictionary", fastV(englishDictionaryWords));

    /**
     * Stores the kill words.
     */
    String[] killWords = readFile("src/main/data/Dictionary.nonPersonalProperNames.txt");

    /**
     * Attribute for the kill words.
     */
    Attribute kill = new Attribute("kill", fastV(killWords));

    /**
     * Stores the prefixes.
     */
    String[] prefixWords = readFile("src/main/data/Dictionary.prefixes.txt");

    /**
     * Attribute for the prefixes.
     */
    Attribute prefix = new Attribute("prefix", fastV(prefixWords));

    /**
     * Stores the suffixes.
     */
    String[] suffixWords = readFile("src/main/data/Dictionary.suffixes.txt");

    /**
     * Attribute for the suffixes.
     */
    Attribute suffix = new Attribute("suffix", fastV(suffixWords));

    /**
     * Stores the first group of locations.
     */
    String[] locationWords1 = readFile("src/main/data/Dictionary.location1.txt");

    /**
     * Attribute for the first group of locations.
     */
    Attribute location1 = new Attribute("location1", fastV(locationWords1));

    /**
     * Stores the second group of locations.
     */
    String[] locationWords2 = readFile("src/main/data/Dictionary.location2.txt");

    /**
     * Attribute for the second group of locations.
     */
    Attribute location2 = new Attribute("location2", fastV(locationWords2));

    /**
     * Stores the third group of locations.
     */
    String[] locationWords3 = readFile("src/main/data/Dictionary.location3.txt");

    /**
     * Attribute for the third group of locations.
     */
    Attribute location3 = new Attribute("location1", fastV(locationWords3));

    /**
     * Groups the lexicals.
     */
    String[] lexicalWords = {"number", "punct", "CapLetter", "capitalized", "AllCaps", "other"};

    //Attribute for the lexicals
    Attribute lexical = new Attribute("lexical", fastV(lexicalWords));

    /**
     * Groups the parts of speech.
     */
    String[] partOfSpeechWords = {"period", "comma", "hyphen", "conjunction", "article", "other"};

    /**
     * Attribute for the parts of speech.
     */
    Attribute partOfSpeech = new Attribute("lexical", fastV(partOfSpeechWords));


    
    /**
     * Create an ARFF file with attributes and data from raw text file.
     */
    public void CreateDataSet() {
        // Call AccumulateLargeString

        //n-gram process data (aka shingling)

        //apply attributes

        //transform data for Trainer
    }

    /**
     * Build a large data stream for processing text file. 
     */
    public void AccumulateLargeString(inputStream) {
        StringBuffer buffer = new StringBuffer;
        while (!done) {
            string line = readALineFrom(inputStream);
            buffer.append(line);
            done =- moreInputIn(inputStream);
        }
        String accumulated = buffer.toString();
    }

    /**
     * Places the file data into a fast vector
     * 
     * Source: Steven J Zeil - Name Extraction -- Design Notes - 6.1 Setting Up the Data
     * 
     * @param data
     * @return FastVector
     */
    @Deprecated
    private FastVector fastV(String[] data) {
        FastVector result = new FastVector(data.length);
        for (String s: data) {
            result.addElement(s);
        }
        return result;
    }

    /**
     * Read lines from a file and store them in an array
     * 
     * @param filePath 
     * @return An array containing each line from the file
     * @throws IOException 
     */
    public String[] readFile(String filePath) throws IOException {
        ArrayList<String> lines = new ArrayList<>(); 

        try (BufferedReader myBufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = myBufferedReader.readLine()) != null) {
                lines.add(line); // Added lines to the array
            }
        }

        String[] linesArray = new String[lines.size()]; 
        return lines.toArray(linesArray);
    }
}

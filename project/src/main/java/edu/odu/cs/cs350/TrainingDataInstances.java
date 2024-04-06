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
@SuppressWarnings("deprecation")
public class TrainingDataInstances {
    
    // //Attributes to look for:
    // //first
    // String[] firstNames;
    
    // String[] lastNames;

    //Attributes to look for:
    //first
    String[] commonFirstNames = readFile("src/main/data/CommonFirstNames.txt");
    Attribute commonFirstNameValue = new Attribute("commonFirstNameValue", fastV(commonFirstNames));

    String[] commonLastNames = readFile("src/main/data/CommonLastNames.txt");
    Attribute commonLastNameValue = new Attribute("commonLastNameValue", fastV(commonLastNames));

    String[] authorFirstNames = readFile("src/main/data/Dictionary.firstNames.txt");
    Attribute authorFirstNameValue = new Attribute("authorFirstNameValue", fastV(authorFirstNames));

    String[] authorLastNames = readFile("src/main/data/Dictionary.lastNames.txt");
    Attribute authorLastNameValue = new Attribute("authorLastNameValue", fastV(authorLastNames));

    String[] honorifics = readFile("src/main/data/Dictionary.honorifics.txt"); 
    Attribute honorificsValue = new Attribute("honorificsValue", fastV(honorifics));
    
    String[] englishDictionaryWords = readFile("src/main/data/Dictionary.english.txt");
    Attribute englishDictionary = new Attribute("englishDictionary", fastV(englishDictionaryWords));

    String[] killWords = readFile("src/main/data/Dictionary.nonPersonalProperNames.txt");
    Attribute kill = new Attribute("kill", fastV(killWords));

    String[] prefixWords = readFile("src/main/data/Dictionary.prefixes.txt");
    Attribute prefix = new Attribute("prefix", fastV(prefixWords));

    String[] suffixWords = readFile("src/main/data/Dictionary.suffixes.txt");
    Attribute suffix = new Attribute("prefix", fastV(suffixWords));

    String[] lexicalWords = {"number", "punct", "CapLetter", "capitalized", "AllCaps", "other"};
    Attribute lexical = new Attribute("lexical", fastV(lexicalWords));
    
    

    //honorifics

    //prefixes

    //suffixes

    //killwords

    /* 
    ****Not sure if we need this...****
    ****Zeil included it in his code, but WEKA docs say its deprecated****

    FastVector attrInfo = new FastVector();
    private FastVector fastV(String[] data) {
        FastVector result = new FastVector(data.length);
        for (String s: data) {
            result.addElement(s);
        }
        return result;
    } */
    
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
     * @param data
     * @return
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

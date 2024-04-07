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
import java.io.IOException;


/**
 * The Trainer class is responsible for:
 * - Creating a data set for WEKA
 */
@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
public class TrainingDataInstances {

    /**
     * instantiation of this class as an object.
     */
    public TrainingDataInstances()throws IOException{};

    /**
     * Stores the common first names.
     */
    String[] commonFirstNames = {"TRUE", "FALSE"};

    /**
     * Attribute for common first names.
     */
    Attribute commonFirstNameValue = new Attribute("commonFirstNameValue", fastV(commonFirstNames));

    /**
     * Stores the common last names.
     */
    String[] commonLastNames = {"TRUE", "FALSE"};

    /**
     * Attribute for common last names.
     */
    Attribute commonLastNameValue = new Attribute("commonLastNameValue", fastV(commonLastNames));

    /**
     * Stores the author first names.
     */
    String[] authorFirstNames = {"TRUE", "FALSE"};

    /**
     * Attribute for author first names.
     */
    Attribute authorFirstNameValue = new Attribute("authorFirstNameValue", fastV(authorFirstNames));

    /**
     * Stores the author last names.
     */
    String[] authorLastNames = {"TRUE", "FALSE"};

    /**
     * Attribute for the author last names.
     */
    Attribute authorLastNameValue = new Attribute("authorLastNameValue", fastV(authorLastNames));

    /**
     * Stores the honorifics.
     */
    String[] honorifics = {"TRUE", "FALSE"};

    /**
     * Attribute for the honorifics.
     */
    Attribute honorificsValue = new Attribute("honorificsValue", fastV(honorifics));

    /**
     * Stores the English dictionary words.
     */
    String[] englishDictionaryWords = {"TRUE", "FALSE"};

    /**
     * Attribute for the English dictionary words.
     */
    Attribute englishDictionary = new Attribute("englishDictionary", fastV(englishDictionaryWords));

    /**
     * Stores the kill words.
     */
    String[] killWords = {"TRUE", "FALSE"};

    /**
     * Attribute for the kill words.
     */
    Attribute kill = new Attribute("kill", fastV(killWords));

    /**
     * Stores the prefixes.
     */
    String[] prefixWords = {"TRUE", "FALSE"};

    /**
     * Attribute for the prefixes.
     */
    Attribute prefix = new Attribute("prefix", fastV(prefixWords));

    /**
     * Stores the suffixes.
     */
    String[] suffixWords = {"TRUE", "FALSE"};

    /**
     * Attribute for the suffixes.
     */
    Attribute suffix = new Attribute("suffix", fastV(suffixWords));

    /**
     * Stores the first group of locations.
     */
    String[] locationWords1 = {"TRUE", "FALSE"};

    /**
     * Attribute for the first group of locations.
     */
    Attribute location1 = new Attribute("location1", fastV(locationWords1));

    /**
     * Stores the second group of locations.
     */
    String[] locationWords2 = {"TRUE", "FALSE"};

    /**
     * Attribute for the second group of locations.
     */
    Attribute location2 = new Attribute("location2", fastV(locationWords2));

    /**
     * Stores the third group of locations.
     */
    String[] locationWords3 = {"TRUE", "FALSE"};

    /**
     * Attribute for the third group of locations.
     */
    Attribute location3 = new Attribute("location1", fastV(locationWords3));

    /**
     * Groups the lexicals.
     */
    String[] lexicalWords = { "number", "punct", "CapLetter", "capitalized", "AllCaps", "other" };

    // Attribute for the lexicals
    Attribute lexical = new Attribute("lexical", fastV(lexicalWords));

    /**
     * Groups the parts of speech.
     */
    String[] partOfSpeechWords = { "period", "comma", "hyphen", "conjunction", "article", "other" };

    /**
     * Attribute for the parts of speech.
     */
    Attribute partOfSpeech = new Attribute("lexical", fastV(partOfSpeechWords));

    /**
     * Holds all of the attributes
     */
    ArrayList<Attribute> attributeInfo = new ArrayList<Attribute>();
    
    /**
     * Method to add the attributes to the attributeInfo array
     * @param attributeArray
     */
    public void attributeAdding( ArrayList<Attribute> attributeArray){
        attributeArray.add(commonFirstNameValue);
        attributeArray.add(commonLastNameValue);
        attributeArray.add(authorFirstNameValue);
        attributeArray.add(authorLastNameValue);
        attributeArray.add(honorificsValue);
        attributeArray.add(englishDictionary);
        attributeArray.add(kill);
        attributeArray.add(prefix);
        attributeArray.add(suffix);
        attributeArray.add(location1);
        attributeArray.add(location2);
        attributeArray.add(location3);
        attributeArray.add(lexical);
        attributeArray.add(partOfSpeech);
    }

    /**
     * Stores the number of attributes
     */
    final int numberOfAttributes = attributeInfo.size();

    /**
     * Gets the number of lines in the files
     * @param filePath
     * @return
     * @throws IOException
     */
    public int getFileSize(String filePath) throws IOException{

        BufferedReader myReader = null;
        myReader = new BufferedReader(new FileReader(filePath));

        String dataLine = myReader.readLine();
        int lineCounter = 1;
        while (dataLine != null) {
            dataLine = myReader.readLine();
            lineCounter += 1;
        }
        myReader.close();
        return lineCounter;
    }

    /**
     * Stores the number of lines in the file
     */
    int dataFileSize = getFileSize("src/main/data/trainingDataSmol.txt");

    // creates an instance holding the trainer information
    Instances trainerInformation = new Instances("data", attributeInfo, dataFileSize);

    

    /**
     * Places the file data into a fast vector
     * 
     * Source: Steven J Zeil - Name Extraction -- Design Notes - 6.1 Setting Up the
     * Data
     * 
     * @param data
     * @return FastVector
     */
    @Deprecated
    private FastVector fastV(String[] data) {
        FastVector result = new FastVector(data.length);
        for (String s : data) {
            result.addElement(s);
        }
        return result;
    }

}

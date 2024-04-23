package edu.odu.cs.cs350;

import weka.core.Instance;
import weka.core.Instances;
//import weka.core.pmml.jaxbbindings.Attribute;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.functions.SMO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.Files;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

//INDEX ATTRIBUTE {VALUES}
//---------------------------------------
//0 @ATTRIBUTE commonfirstname1 {T, F}
//1 @ATTRIBUTE commonlastname1 {T, F}
//2 @ATTRIBUTE dictionaryfeature1 {T, F}
//3 @ATTRIBUTE killword1 {T, F}
//4 @ATTRIBUTE honorific1 {T, F}
//5 @ATTRIBUTE location1 {T, F}
//6 @ATTRIBUTE prefix1 {T, F}
//7 @ATTRIBUTE suffix1 {T, F}
//8 @ATTRIBUTE authorfirstname1 {T, F}
//9 @ATTRIBUTE authorlastname1 {T, F}
//10 @ATTRIBUTE partofspeech1 {period, comma, hyphen, conjunction, article, other}
//11 @ATTRIBUTE lexicalfeature1 {number, punct, CapLetter, capitalized, AllCaps, other}
//12 @ATTRIBUTE commonfirstname2 {T, F}
//13 @ATTRIBUTE commonlastname2 {T, F}
//14 @ATTRIBUTE dictionaryfeature2 {T, F}
//15 @ATTRIBUTE killword2 {T, F}
//16 @ATTRIBUTE honorific2 {T, F}
//17 @ATTRIBUTE location2 {T, F}
//18 @ATTRIBUTE prefix2 {T, F}
//19 @ATTRIBUTE suffix2 {T, F}
//20 @ATTRIBUTE authorfirstname2 {T, F}
//21 @ATTRIBUTE authorlastname2 {T, F}
//22 @ATTRIBUTE partofspeech2 {period, comma, hyphen, conjunction, article, other}
//23 @ATTRIBUTE lexicalfeature2 {number, punct, CapLetter, capitalized, AllCaps, other}
//24 @ATTRIBUTE commonfirstname3 {T, F}
//25 @ATTRIBUTE commonlastname3 {T, F}
//26 @ATTRIBUTE dictionaryfeature3 {T, F}
//27 @ATTRIBUTE killword3 {T, F}
//28 @ATTRIBUTE honorific3 {T, F}
//29 @ATTRIBUTE location3 {T, F}
//30 @ATTRIBUTE prefix3 {T, F}
//31 @ATTRIBUTE suffix3 {T, F}
//32 @ATTRIBUTE authorfirstname3 {T, F}
//33 @ATTRIBUTE authorlastname3 {T, F}
//34 @ATTRIBUTE partofspeech3 {period, comma, hyphen, conjunction, article, other}
//35 @ATTRIBUTE lexicalfeature3 {number, punct, CapLetter, capitalized, AllCaps, other}
//36 @ATTRIBUTE commonfirstname4 {T, F}
//37 @ATTRIBUTE commonlastname4 {T, F}
//38 @ATTRIBUTE dictionaryfeature4 {T, F}
//39 @ATTRIBUTE killword4 {T, F}
//40 @ATTRIBUTE honorific4 {T, F}
//41 @ATTRIBUTE location4 {T, F}
//42 @ATTRIBUTE prefix4 {T, F}
//43 @ATTRIBUTE suffix4 {T, F}
//44 @ATTRIBUTE authorfirstname4 {T, F}
//45 @ATTRIBUTE authorlastname4 {T, F}
//46 @ATTRIBUTE partofspeech4 {period, comma, hyphen, conjunction, article, other}
//47 @ATTRIBUTE lexicalfeature4 {number, punct, CapLetter, capitalized, AllCaps, other}
//48 @ATTRIBUTE commonfirstname5 {T, F}
//49 @ATTRIBUTE commonlastname5 {T, F}
//50 @ATTRIBUTE dictionaryfeature5 {T, F}
//51 @ATTRIBUTE killword5 {T, F}
//52 @ATTRIBUTE honorific5 {T, F}
//53 @ATTRIBUTE location5 {T, F}
//54 @ATTRIBUTE prefix5 {T, F}
//55 @ATTRIBUTE suffix5 {T, F}
//56 @ATTRIBUTE authorfirstname5 {T, F}
//57 @ATTRIBUTE authorlastname5 {T, F}
//58 @ATTRIBUTE partofspeech5 {period, comma, hyphen, conjunction, article, other}
//59 @ATTRIBUTE lexicalfeature5 {number, punct, CapLetter, capitalized, AllCaps, other}
//60 @ATTRIBUTE classification {0, 1, 2}

/**
 * The NameLearningMachine is responsible for running the machine learning model on an input txt file.
 * input: Line of comma-separated values for attributes
 * output: integer [0, 1, 2] signifying "beginning of name", "continuation of name", or "not a name"
 */
//@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
public class NameLearningMachine {

    /**
     * Input file arff format
     */
    String inputFile;

    /**
     * Trained model for use on new data.
     */
    String modelFile = "src/main/data/pnetag_v2_poly.model";

    /**
     * Hold array of attribute data from inputString. 
     */
    String[] dataStrings;

    int arraySize;

    private Vector<String> dataAttributes;

    /**
     * NameLearningMachine Constructor
     */
    public NameLearningMachine() throws IOException {
        DataSource source = new DataSource("src/main/data/trainingARFF2.arff";
        Instances trainingData = source.getDataSet();
        trainingData.setClassIndex(trainingData.numAttributes()-1);

        SMO smo = new SMO();
        weka.core.SerializationHelper.read(modelFile);
        smo.buildClassifier(trainingData);

        Evaluation eval = new Evaluation(trainingData);
        eval.evaluateModel(smo, trainingData);
        
        int classification = classify(trainingData);

        
    };

    public int classify(String inputString) {
        //Split the incoming inputString at commas
        splitInput(inputString);
        arraySize = dataStrings.length;

        return 0; 
    }


    /**
     * Splits inputString at each comma.
     * @param textString
     * @return String[]
     */
    public void splitInput(String textString) {
        dataStrings = textString.split(", ");
    }


    /**
     * Provides access to dataStrings vector
     * 
     * @param index
     * @return String of dataString at index
     */
    public String getDataStringAt(int index) {
        return dataStrings[index];
    }










    // Run machine learning model on arff file. 

    // Return array of text and text classification.

    // For each word in array,
        // Get word[]
        // Get word.Classification
        // Get word.Index

    // Case word.Classification = 1   (first name)
    
        // If perTagInProgress == True
            // Do nothing. Continue to next word.

        // ElseIf perTagInProgress == False
            // word = <PER> + word
            // perTagInProgress = True
    
    // Case word.Classification = 2   (middle or last name)
            // If perTagInProgress == True
                // word = word + </PER>
                // perTagInProgress = False

        // ElseIf perTagInProgress == False
            // word = <PER> + word
            // perTagInProgress = True
    
    // Case wordClassification = 0   (not a name)
        // If perTagInProgress == True
            // word[word.Index - 1] = word[word.Index - 1] + </PER>
            // perTagInProgress = False

        // ElseIf perTagInProgress == False
            // Do nothing. Continue to next word.
        
    

    // /**
    //  * Import raw text data for processing.
    //  * 
    //  * @throws FileNotFoundException
    //  * @throws IOException
    //  */
    // public String importTestData() throws FileNotFoundException, IOException {
        
    //     String wholeFile = null;

    //     try {
    //         // Read all bytes from a file into a byte array
    //         byte[] bytes = Files.readAllBytes(Paths.get(inputTextFile));

    //         // Convert byte array to a string
    //         wholeFile = new String(bytes);

    //     } catch (IOException e) {
    //         System.err.println("Error reading file: " + e.getMessage());
    //     }
    //     return wholeFile;
    // }

    // /**
    //  * Tokenizes all of the text data and sets it's features.
    //  * @param wholeFile
    //  * @throws IOException
    //  */
    // public void convertTextToArff(String wholeFile) throws IOException {
    //     Vector<Token> allTokens = new Vector<Token>();
    //     Scanner scanner = new Scanner(wholeFile);

    //     scanner.useDelimiter("\\s");

    //     CommonNames commonNames = new CommonNames();
    //     DictionaryFeature dictionaryFeature = new DictionaryFeature();
    //     Honorifics honorifics = new Honorifics();
    //     KillWordFeature killWordFeature = new KillWordFeature();
    //     KnownAuthors knownAuthors = new KnownAuthors();
    //     LexicalFeature lexicalFeature = new LexicalFeature();
    //     LocationLookup locationLookup = new LocationLookup();
    //     PartsOfSpeech partsOfSpeech = new PartsOfSpeech();
    //     PrefixAndSuffixFeature prefixAndSuffixFeature = new PrefixAndSuffixFeature();

    //     while (scanner.hasNext()) { // This loop takes way too long for some reason

    //         String nextWord = scanner.next();
    //         Token nextToken;

    //         // split if string contains punctuation
    //         String separatedNextWord[] = nextWord
    //                 .split("(?=[.,!?;:()\"&-])|(?<=[.,!?;:()\"&-])|(?=<\\/?(PER)>)|(?<=<PER>)|(?=<\\/?(NER)>)|(?<=<NER>)|(?<=<\\/?(PER)>)|(?=<PER>)|(?<=<\\/?(NER)>)|(?=<=<NER>)");
    //         for (String phrase : separatedNextWord) {
    //             nextToken = new Token(phrase);

    //             nextToken.setCommonFirstName(commonNames.commonFirstName(phrase));
    //             nextToken.setCommonLastName(commonNames.commonLastName(phrase));
    //             nextToken.setDictionaryFeature(dictionaryFeature.determineDictionaryFeature(phrase));
    //             nextToken.setKillWordFeature(killWordFeature.determineKillWordFeature(phrase));
    //             nextToken.setHonorificsValue(honorifics.containsHonorifics(phrase));
    //             nextToken.setIsLocation(locationLookup.checkLocation(phrase));
    //             nextToken.setPrefixFeature(prefixAndSuffixFeature.determinePrefixFeature(phrase));
    //             nextToken.setSuffixFeature(prefixAndSuffixFeature.determineSuffixFeature(phrase));
    //             nextToken.setAuthorFirstName(knownAuthors.firstName(phrase));
    //             nextToken.setAuthorLastName(knownAuthors.lastName(phrase));
    //             nextToken.setPartOfSpeech(partsOfSpeech.checkForPartsOfSpeech(phrase));
    //             nextToken.setLexicalFeature(lexicalFeature.determineLexicalFeature(phrase));
    //             if (phrase.equals("diagnoses")) {
    //                 System.out.println("do stuff");
    //             }
    //             allTokens.add(nextToken);
    //         }
    //     }
    //     scanner.close();

    //     Token[] window = new Token[5];
    //     for (int index = 0; index < allTokens.size(); index++) {
    //         window[2] = allTokens.elementAt(index);
    //         if (index == 0) {
    //             window[0] = null;
    //             window[1] = null;
    //             window[3] = allTokens.elementAt(index + 1);
    //             window[4] = allTokens.elementAt(index + 2);
    //         } else if (index == 1) {
    //             window[0] = null;
    //             window[1] = allTokens.elementAt(index - 1);
    //             window[3] = allTokens.elementAt(index + 1);
    //             window[4] = allTokens.elementAt(index + 2);
    //         } else if (index == allTokens.size() - 2) {
    //             window[0] = allTokens.elementAt(index - 2);
    //             window[1] = allTokens.elementAt(index - 1);
    //             window[3] = allTokens.elementAt(index + 1);
    //             window[4] = null;
    //         } else if (index == allTokens.size() - 1) {
    //             window[0] = allTokens.elementAt(index - 2);
    //             window[1] = allTokens.elementAt(index - 1);
    //             window[3] = null;
    //             window[4] = null;
    //         } else {
    //             window[0] = allTokens.elementAt(index - 2);
    //             window[1] = allTokens.elementAt(index - 1);
    //             window[3] = allTokens.elementAt(index + 1);
    //             window[4] = allTokens.elementAt(index + 2);
    //         }

    //         if (window[0] != null && window[0].getValue().equals("<NER>")) {
    //             window[0] = null;
    //         }
    //         if (window[1] != null && window[1].getValue().equals("<NER>")) {
    //             window[0] = null;
    //             window[1] = null;
    //         }
    //         if (window[2] != null && window[2].getValue().equals("<NER>") || window[2].getValue().equals("</NER>")) {
    //             continue; // no dataString gets set
    //         }
    //         if (window[3] != null && window[3].getValue().equals("</NER>")) {
    //             window[3] = null;
    //             window[4] = null;
    //         }
    //         if (window[4] != null && window[4].getValue().equals("</NER>")) {
    //             window[4] = null;
    //         }

    //         createDataString(window);
    //     }

    //     populateOutputFile(dataStrings);
    // }

    // /**
    //  * Creates a data string from the tokenized window.
    //  * 
    //  * @param window
    //  */
    // private void createDataString(Token[] window) {
    //     StringBuffer buffer = new StringBuffer();

    //     // Catch any null @ATTRIBUTE WORD values
    //     String tokenWord = window[2].getValue();
    //     if (tokenWord.length() == 0) {
    //         tokenWord = " ";
    //     }
    //     // Single quotes (') for @ATTRIBUTE WORD value
    //     // tokenWord = "'" + tokenWord + "', ";
    //     // buffer.append(tokenWord);

    //     for (Token token : window) {
    //         if (token == null) {
    //             buffer.append("F, F, F, F, F, F, F, F, F, F, other, other, ");
    //         } else {
    //             buffer.append(booleanToString(token.getCommonFirstName()) + ", ");
    //             buffer.append(booleanToString(token.getCommonLastName()) + ", ");
    //             buffer.append(booleanToString(token.getDictionaryFeature()) + ", ");
    //             buffer.append(booleanToString(token.getKillWordFeature()) + ", ");
    //             buffer.append(booleanToString(token.getHonorificsValue()) + ", ");
    //             buffer.append(booleanToString(token.getIsLocation()) + ", ");
    //             buffer.append(booleanToString(token.getPrefixFeature()) + ", ");
    //             buffer.append(booleanToString(token.getSuffixFeature()) + ", ");
    //             buffer.append(booleanToString(token.getAuthorFirstName()) + ", ");
    //             buffer.append(booleanToString(token.getAuthorLastName()) + ", ");
    //             buffer.append(token.getPartOfSpeech() + ", ");
    //             buffer.append(token.getLexicalFeature() + ", ");
    //         }
    //     }
    //     buffer.append("?");

    //     dataStrings.add(buffer.toString());
    // }

    // /**
    //  * Convert boolean value to String value for ARFF file.
    //  * @param itIsTrue
    //  * @return
    //  */
    // private String booleanToString(boolean itIsTrue) {
    //     if (itIsTrue)
    //         return "T";
    //     return "F";
    // }

    // /**
    //  * Populates ARFF output file for use by learning machine
    //  * Not sure where to incorporate function call yet.
    //  * 
    //  * @param dataStrings
    //  * @throws IOException
    //  */
    // private void populateOutputFile(Vector<String> dataStrings) throws IOException {
    //     BufferedWriter out = new BufferedWriter(new FileWriter(tempArffFile, true));
    //     for (String data : dataStrings) {
    //         out.write(data);
    //         out.write("\n");
    //     }
    //     out.close();
    // }

    // /**
    //  * Initializes formated ARFF file.
    //  */
    // private void initializeOutputFile() {
    //     try {
    //         Files.deleteIfExists(Paths.get(tempArffFile));
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }

    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempArffFile))) {

    //         writer.write("@RELATION TEST_DATA\n");
    //         writer.write("\n");
    //         // writer.write("@ATTRIBUTE word STRING\n");
    //         for (Integer i = 0; i < 5; i++) {
    //             Integer temp = i + 1;
    //             writer.write("@ATTRIBUTE commonfirstname" + temp + " {T, F}\n");
    //             writer.write("@ATTRIBUTE commonlastname" + temp + " {T, F}\n");
    //             writer.write("@ATTRIBUTE dictionaryfeature" + temp + " {T, F}\n");
    //             writer.write("@ATTRIBUTE killword" + temp + " {T, F}\n");
    //             writer.write("@ATTRIBUTE honorific" + temp + " {T, F}\n");
    //             writer.write("@ATTRIBUTE location" + temp + " {T, F}\n");
    //             writer.write("@ATTRIBUTE prefix" + temp + " {T, F}\n");
    //             writer.write("@ATTRIBUTE suffix" + temp + " {T, F}\n");
    //             writer.write("@ATTRIBUTE authorfirstname" + temp + " {T, F}\n");
    //             writer.write("@ATTRIBUTE authorlastname" + temp + " {T, F}\n");
    //             writer.write(
    //                     "@ATTRIBUTE partofspeech" + temp + " {period, comma, hyphen, conjunction, article, other}\n");
    //             writer.write("@ATTRIBUTE lexicalfeature" + temp
    //                     + " {number, punct, CapLetter, capitalized, AllCaps, other}\n");
    //         }
    //         writer.write("@ATTRIBUTE classification {0, 1, 2}\n");
    //         writer.write("\n");
    //         writer.write("@data\n");
    //         writer.write("\n");
    //         writer.close();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }



















}

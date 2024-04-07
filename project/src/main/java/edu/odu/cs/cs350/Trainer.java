package edu.odu.cs.cs350;

import java.util.Scanner;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

//For ARFF data
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.pmml.Array;
import weka.core.Instance;
import weka.core.Instances;

//For classifier
import weka.core.FastVector;
import weka.core.SerializationHelper;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.supportVector.RBFKernel;
import weka.filters.supervised.attribute.AddClassification;

//For evaluation
import weka.classifiers.Evaluation;
import java.util.Random;

/**
 * The Trainer class is responsible for:
 * - Running the WEKA training session
 * - Creating a classifier model for NameLearningMachine
 * - https://github.com/charlesSeek/weka-example/
 */
public class Trainer {

    private final String TRAINING_DATA_FILEPATH = "src/main/data/trainingDataSmol.txt";

    static double gamma = 0.01; // initial guess
    static double C = 1.0; // initial guess

    // // Create classifier
    // public void createClassifier(training) {
    // SMO svm = new SMO(); // new classifier instance
    // svm.setOptions(options); // set the options
    // svm.setKernel(new RBFKernel(training, 25007, gamma));
    // svm.setC(C);

    // Number of training attributes
    int numberofAttributes;

    // Set WEKA options
    String[] options = { "-N", "0", "-V", "-1" };

    /**
     * Tokenizes all of the training data and sets it's features
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void importTrainingData() throws FileNotFoundException, IOException {
        Vector<Token> allTokens = new Vector<Token>();
        String wholeFile = null;

        try {
            // Read all bytes from a file into a byte array
            byte[] bytes = Files.readAllBytes(Paths.get(TRAINING_DATA_FILEPATH));

            // Convert byte array to a string
            wholeFile = new String(bytes);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        Scanner scanner = new Scanner(wholeFile);

        scanner.useDelimiter("\\s");

        CommonNames commonNames = new CommonNames();
        DictionaryFeature dictionaryFeature = new DictionaryFeature();
        Honorifics honorifics = new Honorifics();
        KillWordFeature killWordFeature = new KillWordFeature();
        KnownAuthors knownAuthors = new KnownAuthors();
        LexicalFeature lexicalFeature = new LexicalFeature();
        LocationLookup locationLookup = new LocationLookup();
        PartsOfSpeech partsOfSpeech = new PartsOfSpeech();
        PrefixAndSuffixFeature prefixAndSuffixFeature = new PrefixAndSuffixFeature();

        Boolean inPER = false;
        Boolean startPER = false;

        while (scanner.hasNext()) {

            String nextWord = scanner.next();
            Token nextToken;

            // split if string contains punctuation
            String separatedNextWord[] = nextWord.split("(?<=\")|(?=[.,!?;:()\"&-])|(?=<\\/?(PER)>)|(?<=<PER>)");
            for (String phrase : separatedNextWord) {
                nextToken = new Token(phrase);

                nextToken.setCommonFirstName(commonNames.commonFirstName(phrase));
                nextToken.setCommonLastName(commonNames.commonLastName(phrase));
                nextToken.setDictionaryFeature(dictionaryFeature.determineDictionaryFeature(phrase));
                nextToken.setKillWordFeature(killWordFeature.determineKillWordFeature(phrase));
                nextToken.setHonorificsValue(honorifics.containsHonorifics(phrase));
                nextToken.setIsLocation(locationLookup.checkLocation(phrase));
                nextToken.setPrefixFeature(prefixAndSuffixFeature.determinePrefixFeature(phrase));
                nextToken.setSuffixFeature(prefixAndSuffixFeature.determineSuffixFeature(phrase));
                nextToken.setAuthorFirstName(knownAuthors.firstName(phrase));
                nextToken.setAuthorFirstName(knownAuthors.lastName(phrase));
                nextToken.setPartOfSpeech(partsOfSpeech.checkForPartsOfSpeech(phrase));
                nextToken.setLexicalFeature(lexicalFeature.determineLexicalFeature(phrase));

                if (phrase.equals("</PER>")) {
                    inPER = false;
                    continue;
                }

                if (startPER)
                    nextToken.setClassification(1);
                else if (inPER)
                    nextToken.setClassification(2);
                else
                    nextToken.setClassification(0);

                startPER = false;

                if (phrase.equals("<PER>")) {
                    inPER = true;
                    startPER = true;
                    continue;
                }
                allTokens.add(nextToken);
            }
        }
        scanner.close();

        Token[] window = new Token[5];
        for (int index = 0; index > allTokens.size(); index++) {
            window[2] = allTokens.elementAt(index);
            if (index == 0) {
                window[0] = null;
                window[1] = null;
                window[3] = allTokens.elementAt(index + 1);
                window[4] = allTokens.elementAt(index + 2);
            } else if (index == 1) {
                window[0] = null;
                window[1] = allTokens.elementAt(index - 1);
                window[3] = allTokens.elementAt(index + 1);
                window[4] = allTokens.elementAt(index + 2);
            } else if (index == allTokens.size() - 2) {
                window[0] = allTokens.elementAt(index - 2);
                window[1] = allTokens.elementAt(index - 1);
                window[3] = allTokens.elementAt(index + 1);
                window[4] = null;
            } else if (index == allTokens.size() - 1) {
                window[0] = allTokens.elementAt(index - 2);
                window[1] = allTokens.elementAt(index - 1);
                window[3] = null;
                window[4] = null;
            } else {
                window[0] = allTokens.elementAt(index - 2);
                window[1] = allTokens.elementAt(index - 1);
                window[3] = allTokens.elementAt(index + 1);
                window[4] = allTokens.elementAt(index + 2);
            }
            createDataString(window);
        }
    }

    private void createDataString(Token[] window) {
        StringBuffer buffer = new StringBuffer();
        // print the value of the token
        // print the attributes of the token
        // print the window attributes
        // if the window values are null, set as zero
        // if the window values are <NER>, set to null
    }

    /**
     * Get Instances dataset
     * @param instances
     */
    public static void createInstances(TrainingDataInstances instances) {
        // Need to finish after we determine datasource format
    }

    /**
     * Create classifier
     * @param training
     * @throws Exception
     */
    public void createClassifier(Instances training) throws Exception {

        SMO svm = new SMO(); // new classifier instance
        svm.setOptions(options); // set the options
        svm.setKernel(new RBFKernel(training, 25007, gamma));
        svm.setC(C);
    }

    public void SaveModel(SMO svm) throws Exception {

        weka.core.SerializationHelper.write("smo.model", svm);
    }

    /**
     * Evaluate clasification prediction
     * @param training
     * @throws Exception
     */
    public void EvaluateClassification(Instances training) throws Exception {

        SMO svm = new SMO(); // new classifier instance
        svm.setOptions(options); // set the options
        svm.setKernel(new RBFKernel(training, 25007, gamma));
        svm.setC(C);

        Evaluation eval = new Evaluation(training);
        final int numberofCrossClasses = 10;

        eval.crossValidateModel(svm, training, numberofCrossClasses, new Random(1));

        double score = eval.pctCorrect();
        System.out.println("Score: " + score + "%");

    }

}

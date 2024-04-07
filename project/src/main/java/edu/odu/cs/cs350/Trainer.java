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

    private Vector<String> dataStrings;

    static double gamma = 0.01; // initial guess
    static double C = 1.0; // initial guess

    // // Create classifier
    // public void createClassifier(training) {
    // SMO svm = new SMO(); // new classifier instance
    // svm.setOptions(options); // set the options
    // svm.setKernel(new RBFKernel(training, 25007, gamma));
    // svm.setC(C);

    // Number of training attributes
    int numberOfAttributes;

    // Set WEKA options
    String[] options = { "-N", "0", "-V", "-1" };

    public Trainer() {
        dataStrings = new Vector<String>();
    }

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

            if (window[0].getValue().equals("<NER>")) {
                window[0] = null;
            }
            if (window[1].getValue().equals("<NER>")) {
                window[0] = null;
                window[1] = null;
            }
            if (window[2].getValue().equals("<NER>") || window[2].getValue().equals("</NER>")) {
                continue; // no dataString gets set
            }
            if (window[3].getValue().equals("</NER>")) {
                window[3] = null;
                window[4] = null;
            }
            if (window[4].getValue().equals("</NER>")) {
                window[4] = null;
            }

            createDataString(window);
        }
    }

    private void createDataString(Token[] window) {
        StringBuffer buffer = new StringBuffer();

        buffer.append(window[2].getValue() + ", ");

        int index = 0;
        for (Token token : window) {
            if (token == null) {
                buffer.append("FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, other, other, 0");
            } else {
                buffer.append(booleanToString(token.getCommonFirstName()) + ", ");
                buffer.append(booleanToString(token.getCommonLastName()) + ", ");
                buffer.append(booleanToString(token.getDictionaryFeature()) + ", ");
                buffer.append(booleanToString(token.getKillWordFeature()) + ", ");
                buffer.append(booleanToString(token.getHonorificsValue()) + ", ");
                buffer.append(booleanToString(token.getIsLocation()) + ", ");
                buffer.append(booleanToString(token.getPrefixFeature()) + ", ");
                buffer.append(booleanToString(token.getSuffixFeature()) + ", ");
                buffer.append(booleanToString(token.getAuthorFirstName()) + ", ");
                buffer.append(booleanToString(token.getAuthorLastName()) + ", ");
                buffer.append(token.getPartOfSpeech() + ", ");
                buffer.append(token.getLexicalFeature() + ", ");
                buffer.append(token.getClassification());
            }

            if (index != 4)
                buffer.append(", ");

            index++;
        }
        dataStrings.add(buffer.toString());
    }

    private String booleanToString(boolean itIsTrue) {
        if (itIsTrue)
            return "TRUE";
        return "FALSE";
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
    public void createClassification(Instances training) throws Exception {

        SMO supportVectorModel = new SMO(); // new classifier instance
        supportVectorModel.setOptions(options); // set the options
        supportVectorModel.setKernel(new RBFKernel(training, 25007, gamma));
        supportVectorModel.setC(C);
    }

    public void SaveModel(SMO supportVectorModel) throws Exception {

        weka.core.SerializationHelper.write("smo.model", supportVectorModel);
    }

    /**
     * Evaluate clasification prediction
     * @param training
     * @throws Exception
     */
    public void EvaluateClassification(Instances training) throws Exception {

        SMO supportVectorModel = new SMO(); // new classifier instance
        supportVectorModel.setOptions(options); // set the options
        supportVectorModel.setKernel(new RBFKernel(training, 25007, gamma));
        supportVectorModel.setC(C);

        Evaluation trainingEvaluation = new Evaluation(training);
        final int numberOfCrossClasses = 10;

        trainingEvaluation.crossValidateModel(supportVectorModel, training, numberOfCrossClasses, new Random(1));

        double score = trainingEvaluation.pctCorrect();
        System.out.println("Score: " + score + "%");

    }

}

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

//For classifier
import weka.core.FastVector;
import weka.core.SerializationHelper;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.supportVector.RBFKernel;
import weka.filters.supervised.attribute.AddClassification;

//For evaluation
import weka.classifiers.Evaluation;


/**
 * The Trainer class is responsible for:
 * - Running the WEKA training session
 * - Creating a classifier model for NameLearningMachine
 * - https://github.com/charlesSeek/weka-example/
 */
public class Trainer {

    private final String TRAINING_DATA_FILEPATH = "src/main/data/trainingDataSmol.txt";

    double gamma = 0.01; // initial guess
    double C = 1.0; // initial guess

    // // Create classifier
    // public void createClassifier(training) {
    // SMO svm = new SMO(); // new classifier instance
    // svm.setOptions(options); // set the options
    // svm.setKernel(new RBFKernel(training, 25007, gamma));
    // svm.setC(C);

    //Number of training attributes
    int numberofAttributes;

    //Set WEKA options
    String[] options = {"-N", "0", "-V", "-1"};

    FastVector attrInfo = new FastVector();
    //List all attributes


    /**
     * Get Instances dataset
     */
    public Instances createInstances(Datasource source) {
        //Need to finish after we determine datasource format
    }

    /**
     * Create classifier
     */
    public void createClassifier(Instances training) {


        SMO svm = new SMO();        // new classifier instance
        svm.setOptions(options);    // set the options
        svm.setKernel(new RBFKernel(training, 25007, gamma));
        svm.setC(C);
    }

    // // Train classifier
    // svm.buildClassifier(training);
    // }

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

                if (phrase.equals("</PER>"))
                    inPER = false;

                if (startPER)
                    nextToken.setClassification(1);
                else if (inPER)
                    nextToken.setClassification(2);
                else
                    nextToken.setClassification(0);

                allTokens.add(nextToken);

                startPER = false;

                if (phrase.equals("<PER>")) {
                    inPER = true;
                    startPER = true;
                }
            }
        }
        scanner.close();

    }

        // Train classifier
        svm.buildClassifier(training);

    }

    public static void SaveModel(Instances traindata) {
        
        SMOreg smo = new SMOreg;
        SerializationHelper.write("smo.model", smo);
    }

    /**
     * Evaluate clasification prediction
     */
    public static void EvaluateClassification(Instances training) {

        SMO svm = new SMO();        // new classifier instance
        svm.setOptions(options);    // set the options
        svm.setKernel(new RBFKernel(training, 25007, gamma));
        svm.setC(C);
        
        Evaluation eval = new Evaluation(training);
        final int numberofCrossClasses = 10;

        eval.crossValidateModel(svm, training, numberofCrossClasses, new Random(1));

        double score = eval.pctCorrect();
        System.out.println("Score: " + score + "%");

    }

   





    
}

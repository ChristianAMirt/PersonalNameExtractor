package edu.odu.cs.cs350;

import java.util.Scanner;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

//For ARFF data
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.pmml.Array;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;

//For classifier
import weka.core.FastVector;
import weka.core.SerializationHelper;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.supportVector.RBFKernel;
import weka.classifiers.functions.supportVector.PolyKernel;
import weka.filters.supervised.attribute.AddClassification;
import weka.filters.unsupervised.attribute.StringToWordVector;

//For evaluation
import weka.classifiers.Evaluation;
import java.util.Random;

/**
 * The Trainer class is responsible for:
 * - Running the WEKA training session
 * - Creating a classifier model for NameLearningMachine
 * - https://github.com/charlesSeek/weka-example/.
 */
public class Trainer {

    /**
     * String for trainingData filepath.
     */
    private final String TRAINING_DATA_FILEPATH = "src/main/data/trainingData.txt";

    /**
     * String for trainingARFF filepath.
     */
    private final String OUTPUT_ARFF_FILEPATH = "src/main/data/trainingARFF2.arff";

    /**
     * Stores data.
     */
    private Vector<String> dataStrings;

    /**
     * initial guess.
     */
    static double gamma = 0.01;

    /**
     * initial guess.
     */
    static double C = 1.0;

    /**
     * Number of training attributes.
     */
    int numberOfAttributes;

    /**
     * Set WEKA options.
     */
    String[] options = { "-N", "0", "-V", "-1" };

    /**
     * constructor for trainer.
     */
    public Trainer() throws FileNotFoundException, IOException {
        dataStrings = new Vector<String>();
        initializeOutputFile();
        importTrainingData();
    }

    /**
     * Provides access to dataStrings vector
     * 
     * @param index
     * @return String of dataString at index
     */
    public String getDataStringAt(int index) {
        return dataStrings.elementAt(index);
    }

    /**
     * Tokenizes all of the training data and sets it's features.
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

        while (scanner.hasNext()) { // This loop takes way too long for some reason

            String nextWord = scanner.next();
            Token nextToken;

            // split if string contains punctuation
            String separatedNextWord[] = nextWord
                    .split("(?=[.,!?;:()\"&-])|(?<=[.,!?;:()\"&-])|(?=<\\/?(PER)>)|(?<=<PER>)|(?=<\\/?(NER)>)|(?<=<NER>)|(?<=<\\/?(PER)>)|(?=<PER>)|(?<=<\\/?(NER)>)|(?=<=<NER>)");
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
                nextToken.setAuthorLastName(knownAuthors.lastName(phrase));
                nextToken.setPartOfSpeech(partsOfSpeech.checkForPartsOfSpeech(phrase));
                nextToken.setLexicalFeature(lexicalFeature.determineLexicalFeature(phrase));

                if (phrase.equals("</PER>")) {
                    inPER = false;
                    continue;
                }

                if (startPER)
                    nextToken.setClassification(1); // start of name
                else if (inPER)
                    nextToken.setClassification(2); // Continuing a name
                else
                    nextToken.setClassification(0); // Not a name

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
        for (int index = 0; index < allTokens.size(); index++) {
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

            if (window[0] != null && window[0].getValue().equals("<NER>")) {
                window[0] = null;
            }
            if (window[1] != null && window[1].getValue().equals("<NER>")) {
                window[0] = null;
                window[1] = null;
            }
            if (window[2] != null && window[2].getValue().equals("<NER>") || window[2].getValue().equals("</NER>")) {
                continue; // no dataString gets set
            }
            if (window[3] != null && window[3].getValue().equals("</NER>")) {
                window[3] = null;
                window[4] = null;
            }
            if (window[4] != null && window[4].getValue().equals("</NER>")) {
                window[4] = null;
            }

            createDataString(window);
        }

        // MARK: Call populateOutputFile
        populateOutputFile(dataStrings);
    }

    /**
     * Creates a data string, returning a token window.
     * 
     * @param window
     */
    private void createDataString(Token[] window) {
        StringBuffer buffer = new StringBuffer();

        // Catch any null @ATTRIBUTE WORD values
        String tokenWord = window[2].getValue();
        if (tokenWord.length() == 0) {
            tokenWord = " ";
        }
        // Single quotes (') for @ATTRIBUTE WORD value
        // tokenWord = "'" + tokenWord + "', ";
        // buffer.append(tokenWord);

        for (Token token : window) {
            if (token == null) {
                buffer.append("F, F, F, F, F, F, F, F, F, F, other, other, ");
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
            }
        }
        buffer.append(window[2].getClassification());

        dataStrings.add(buffer.toString());
    }

    private String booleanToString(boolean itIsTrue) {
        if (itIsTrue)
            return "T";
        return "F";
    }

    /**
     * Initializes formated ARFF file.
     */
    private void initializeOutputFile() {
        try {
            Files.deleteIfExists(Paths.get(OUTPUT_ARFF_FILEPATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_ARFF_FILEPATH))) {

            writer.write("@RELATION ARFF_TRAINING\n");
            writer.write("\n");
            // writer.write("@ATTRIBUTE word STRING\n");
            for (Integer i = 0; i < 5; i++) {
                Integer temp = i + 1;
                writer.write("@ATTRIBUTE commonfirstname" + temp + " {T, F}\n");
                writer.write("@ATTRIBUTE commonlastname" + temp + " {T, F}\n");
                writer.write("@ATTRIBUTE dictionaryfeature" + temp + " {T, F}\n");
                writer.write("@ATTRIBUTE killword" + temp + " {T, F}\n");
                writer.write("@ATTRIBUTE honorific" + temp + " {T, F}\n");
                writer.write("@ATTRIBUTE location" + temp + " {T, F}\n");
                writer.write("@ATTRIBUTE prefix" + temp + " {T, F}\n");
                writer.write("@ATTRIBUTE suffix" + temp + " {T, F}\n");
                writer.write("@ATTRIBUTE authorfirstname" + temp + " {T, F}\n");
                writer.write("@ATTRIBUTE authorlastname" + temp + " {T, F}\n");
                writer.write(
                        "@ATTRIBUTE partofspeech" + temp + " {period, comma, hyphen, conjunction, article, other}\n");
                writer.write("@ATTRIBUTE lexicalfeature" + temp
                        + " {number, punct, CapLetter, capitalized, AllCaps, other}\n");
            }
            writer.write("@ATTRIBUTE classification {0, 1, 2}\n");
            writer.write("\n");
            writer.write("@data\n");
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Populates ARFF output file for use by learning machine
     * Not sure where to incorporate function call yet.
     * 
     * @param dataStrings
     * @throws IOException
     */
    private void populateOutputFile(Vector<String> dataStrings) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(OUTPUT_ARFF_FILEPATH, true));
        // MARK: OUTPUT TO FILE HERE
        for (String data : dataStrings) {
            out.write(data);
            out.write("\n");
        }

        out.close();
    }

    /**
     * Get Instances dataset.
     * 
     * @param instances
     */
    public static void createInstances(NameLearningMachine instances) {
        // Need to finish after we determine datasource format
    }

    /**
     * Create classifier.
     * 
     * @param training
     * @throws Exception
     */
    public void createClassification(Instances training) throws Exception {

        SMO supportVectorModel = new SMO(); // new classifier instance
        supportVectorModel.setOptions(options); // set the options
        supportVectorModel.setKernel(new RBFKernel(training, 25007, gamma));
        supportVectorModel.setC(C);
    }

    /**
     * Saves the model.
     * 
     * @param supportVectorModel
     * @throws Exception
     */
    public void SaveModel(SMO supportVectorModel) throws Exception {

        weka.core.SerializationHelper.write("smo.model", supportVectorModel);
    }

    /**
     * Evaluate clasification prediction.
     * 
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

    // ==============================================================================
    // THE FOLLOWING CODE SHOULD BE EVERYTHING NEEDED TO CREATE A .MODEL FILE FROM A
    // .ARFF FILE.
    // THERE ARE SOME REDUNDANT VARIABLES & FUNCTIONS THAT STILL NEED TO BE ROLLED
    // INTO OUR
    // EXISTING CODE. STILL WORKING ON THAT PART.
    // AS-IS, THE BELOW CODE SHOULD BE TESTABLE IN CREATING A .MODEL FILE FROM A
    // .ARFF FILE.

    /**
     * Stores all training data Instances from ARFF file.
     */
    Instances trainingData;

    /**
     * Store Preprocessing Filter for trainingData Instances.
     */
    StringToWordVector dataPrepper;

    /**
     * Store Classifier for trainingData Instances.
     */
    FilteredClassifier dataClassifier;

    /**
     * Load ARFF training dataset.
     * 
     * @param filenameARFF
     */
    public void loadARFFfile(String filenameARFF) {
        try {
            FileReader file = new FileReader(filenameARFF);
            BufferedReader reader = new BufferedReader(file);
            ArffReader arff = new ArffReader(reader);
            trainingData = arff.getData();
            System.out.println("Loaded file: " + filenameARFF);
            reader.close();
        } catch (IOException err) {
            System.out.println("Error. Could not load file: " + filenameARFF);
        }
    }

    /**
     * Create Classifier use to evaluate trainingData.
     */
    public void createClassifier() {
        int numFolds = 4; // number of folds to use during cross-validation testing.

        try {
            trainingData.setClassIndex(0);
            dataPrepper = new StringToWordVector();
            dataPrepper.setAttributeIndices("last");

            dataClassifier = new FilteredClassifier();
            dataClassifier.setFilter(dataPrepper);
            dataClassifier.setClassifier(new SMO());

            /*
             * Need to check if this is still required:
             * - Run evaluation on untrained classifier before training
             */
            Evaluation eval = new Evaluation(trainingData);
            eval.crossValidateModel(dataClassifier, trainingData, numFolds, new Random(1));

            System.out.println(eval.toSummaryString());
            System.out.println(eval.toClassDetailsString());
            System.out.println("Evaluation on training data complete.");
        } catch (Exception err) {
            System.out.println("Error. Count not create Classifier.");
        }
    }

    /**
     * Run training session on Classifier.
     */
    public void trainClassifier() {
        try {
            trainingData.setClassIndex(0);
            dataPrepper = new StringToWordVector();
            dataPrepper.setAttributeIndices("last");

            dataClassifier = new FilteredClassifier();
            dataClassifier.setFilter(dataPrepper);
            dataClassifier.setClassifier(new SMO());
            dataClassifier.buildClassifier(trainingData);

            System.out.println("Training on Classifier complete.");
        } catch (Exception err) {
            System.out.println("Error. Count not complete training on Classifier.");
        }
    }

    /**
     * Save the trained model to some/file/path/fileName.model
     * 
     * @param fileName
     */
    public void saveModel(String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(dataClassifier);
            out.close();
            System.out.println("Model saved: " + fileName);
        } catch (IOException err) {
            System.out.println("Error. Could not create file: " + fileName);
        }
    }

    /**
     * Load arff file and create a trained model file.
     * 
     * @param arffFile
     * @param modelFile
     */
    public void createModelFromDataset(String arffFile, String modelFile) {
        loadARFFfile(arffFile);
        createClassifier();
        trainClassifier();
        saveModel(arffFile);
    }
}

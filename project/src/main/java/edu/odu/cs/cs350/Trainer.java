package edu.odu.cs.cs350;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

//For ARFF data
import weka.core.Instance;
import weka.core.Instances;
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
    
    double gamma = 0.01;
    final double C = 1.0;

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

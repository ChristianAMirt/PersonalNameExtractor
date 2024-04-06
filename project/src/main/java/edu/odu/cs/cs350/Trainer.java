package edu.odu.cs.cs350;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import weka.core.Instance;
import weka.core.Instances;

/**
 * The Trainer class is responsible for:
 * - Running the WEKA training session
 * - Creating a classifier model for Librarian
 */
public class Trainer {
    
    double gamma = 0.01;  // initial guess
    double C = 1.0;       // initial guess

    // Create classifier
    public void createClassifier(training) {
        SMO svm = new SMO();        // new classifier instance
        svm.setOptions(options);    // set the options
        svm.setKernel(new RBFKernel(training, 25007, gamma));
        svm.setC(C);

        // Train classifier
        svm.buildClassifier(training);
    }

    /**
     * Generate the instances of training data
     */


    /**
     * Use features classes and token class to classify text
     */
    public static void runClassifier(training) {
        
        //Set shingle size

        //Found <PER>

        //Found </PER> tag

        //Found anything else


    }

    




    
}

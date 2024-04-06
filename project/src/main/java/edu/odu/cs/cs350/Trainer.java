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

        //Attributes to look for:
    //first
    String[] firstNames;
    
    String[] lastNames;

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




    
}

package edu.odu.cs.cs350;

import weka.core.Instances;
import weka.core.FastVector;

/**
 * The Trainer class is responsible for:
 * - Creating a data set for WEKA
 */
public class TrainingDataInstances {
    
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

    @Deprecated
    private FastVector fastV(String[] data) {
        FastVector result = new FastVector(data.length);
        for (String s: data) {
            result.addElement(s);
        }
        return result;
    }

}

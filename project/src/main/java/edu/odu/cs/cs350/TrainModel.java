package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Driver to train the machine learning model
 */
public class TrainModel {

    public static void main(String[] arguments) {
        Trainer trainer = null;

        String inputText = String.join(" ", arguments);
        try {
            trainer = new Trainer();
        } catch (IOException e) {
            System.err.println("Training data file was not found");
        }

        if (trainer == null) {
            System.err.println("Trainer failed to initialize.");
        }
    }
}

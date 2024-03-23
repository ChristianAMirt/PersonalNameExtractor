package edu.odu.cs.cs350;

import java.io.IOException;

public class PNE {

    public static void main(String[] args) {
        Librarian librarian = null;

        if (args.length == 0) {
            System.err.println("PNE.jar [input text]");
        } else {
            String inputText = String.join(" ", args);
            try {
                librarian = new Librarian(inputText);
                librarian.markNames();
            } catch (IOException e) {
                System.err.println("Character limit exceeded (4000). Try again with a shorter text");
            }
        }
        if (librarian != null) {
            System.out.println(librarian.toString());
        } else
            System.err.println("Librarian failed to initialize.");
    }
}
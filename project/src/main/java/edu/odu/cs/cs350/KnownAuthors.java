package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Determins if a name is a known author or not
 */
public class KnownAuthors {

    
    private static final String FILE1 = "src/main/data/Dictionary.firstNames.txt";
    private static final String FILE2 = "src/main/data/Dictionary.lastNames.txt";

    /**
     * Read lines from a file and store them in an array
     * 
     * @param filePath 
     * @return An array containing each line from the file
     * @throws IOException 
     */
    public String[] readFile(String filePath) throws IOException {
        ArrayList<String> lines = new ArrayList<>(); 

        try (BufferedReader myBufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = myBufferedReader.readLine()) != null) {
                lines.add(line); // Added lines to the array
            }
        }

        String[] linesArray = new String[lines.size()]; 
        return lines.toArray(linesArray);
    }

    /**
     * Read lines from the first file and store them in an array
     * @return An array containing each line from the first file
     * @throws IOException 
     */
    public String[] readFirstNameToArray() throws IOException {
        return readFile(FILE1);
    }

    /**
     * Read lines from the second file and store them in an array named lastName
     * @return An array containing each line from the second file
     * @throws IOException 
     */
    public String[] readLastNameToArray() throws IOException {
        return readFile(FILE2);
    }
    
    /**
     * Check if a token has any known author's last name
     * @param value 
     * @return true if the token has known authors, false otherwise
     * @throws IOException 
     */
    public boolean lastName(String value) throws IOException {
       
        String[] lastNameArray = readLastNameToArray();

        return Arrays.asList(lastNameArray).contains(value);
    }

 /**
     * Check if a token has any known author's first name
     * @param value 
     * @return true if the token has known authors, false otherwise
     * @throws IOException 
     */
    public boolean firstName(String value) throws IOException {
       
        String[] firstNameArray = readFirstNameToArray();
        
        return Arrays.asList(firstNameArray).contains(value);
    }



}





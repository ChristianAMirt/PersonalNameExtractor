package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Determines if a name is a known author or not.
 * 
 * @author Rahul Malik and Brendan Hearrell
 * 
 */
public class KnownAuthors {

    /**
     * String for first names filepath.
     */
    private static final String FILE1 = "src/main/data/Dictionary.firstNames.txt";

    /**
     * String for last names filepath.
     */
    private static final String FILE2 = "src/main/data/Dictionary.lastNames.txt";

    /**
     * Hash set of known author first names.
     */
    private Set<String> firstNames;

    /**
     * Hash set of known author last names.
     */
    private Set<String> lastNames;

    /**
     * Creates a new object storing a list of known author names.
     * 
     * @throws IOException
     */
    public KnownAuthors() throws IOException {
        this.firstNames = new HashSet<>();
        this.lastNames = new HashSet<>();

        loadFile(FILE1, firstNames);
        loadFile(FILE2, lastNames);
    }

    /**
     * Loads information from a file into a hashset.
     * 
     * @param filePath
     * @param set
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void loadFile(String filePath, Set<String> set) throws FileNotFoundException, IOException {
        BufferedReader myReader = null;
        myReader = new BufferedReader(openFile(filePath));
        String fileLine = myReader.readLine();
        while (fileLine != null) {
            set.add(fileLine);
            fileLine = myReader.readLine();
        }
        myReader.close();
    }

    /**
     * Opens a file of equal name to
     * param myFile, returning a reader
     * for that file.
     * 
     * @param myFile
     * @return FileReader
     * @throws FileNotFoundException
     */
    public FileReader openFile(String myFile) throws FileNotFoundException {
        return new FileReader(new File(myFile));
    }

    /**
     * Compares parameters hash value to the hashset to see if string
     * exisits in set.
     * 
     * @param myWord
     * 
     * @throws FileNotFoundException
     * @throws IOException
     * 
     * @return boolean value.
     */
    public boolean firstName(String myWord) throws FileNotFoundException, IOException {
        return firstNames.contains(myWord.trim().toLowerCase());
    }

    /**
     * Compares parameters hash value to the hashset to see if string
     * exisits in set.
     * 
     * @param myWord
     * 
     * @throws FileNotFoundException
     * @throws IOException
     * 
     * @return boolean value.
     */
    public boolean lastName(String myWord) throws FileNotFoundException, IOException {
        long debug = myWord.hashCode();
        return lastNames.contains(myWord.trim().toLowerCase());
    }

    // /**
    // * Read lines from a file and store them in an array.
    // *
    // * @param filePath
    // * @return An array containing each line from the file
    // * @throws IOException
    // */
    // public Set<String> readFile(String filePath) throws IOException {
    // ArrayList<String> lines = new ArrayList<>();

    // try (BufferedReader myBufferedReader = new BufferedReader(new
    // FileReader(filePath))) {
    // String line;
    // while ((line = myBufferedReader.readLine()) != null) {
    // lines.add(line); // Added lines to the array
    // }
    // }

    // String[] linesArray = new String[lines.size()];
    // return lines.toArray(linesArray);
    // }

    // /**
    // * Read lines from the first file and store them in an array
    // *
    // * @return An array containing each line from the first file
    // * @throws IOException
    // */
    // public String[] readFirstNameToArray() throws IOException {
    // return readFile(FILE1);
    // }

    // /**
    // * Read lines from the second file and store them in an array named lastName
    // *
    // * @return An array containing each line from the second file
    // * @throws IOException
    // */
    // public String[] readLastNameToArray() throws IOException {
    // return readFile(FILE2);
    // }

    // /**
    // * Check if a token has any known author's last name
    // *
    // * @param value
    // * @return true if the token has known authors, false otherwise
    // * @throws IOException
    // */
    // public boolean lastName(String value) throws IOException {

    // String[] lastNameArray = readLastNameToArray();

    // return Arrays.asList(lastNameArray).contains(value);
    // }

    // /**
    // * Check if a token has any known author's first name
    // *
    // * @param value
    // * @return true if the token has known authors, false otherwise
    // * @throws IOException
    // */
    // public boolean firstName(String value) throws IOException {

    // String[] firstNameArray = readFirstNameToArray();

    // return Arrays.asList(firstNameArray).contains(value);
    // }

}

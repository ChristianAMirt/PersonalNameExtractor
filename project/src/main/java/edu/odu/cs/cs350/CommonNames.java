package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.io.FileNotFoundException;

/**
 *  Determines if token contains a common name
 */
public class CommonNames {

    // Contains first names loaded from CommonFirstNames.txt
    private Set<String> firstNames;

    // Contains last names loaded from CommonLastNames.txt
    private Set<String> lastNames;

    /**
     * Constructor for CommonNames
     * @throws IOException 
     */
    public CommonNames() throws IOException{
        this.firstNames = new HashSet<>();
        this.lastNames = new HashSet<>();
        loadFirstNames();
        loadLastNames();
    }

    /**
     * Opens input file dependent on string that 
     * contains file name
     * 
     * @param fileName
     * @throws FileNotFoundException
     * @return FileReader
     */
    public FileReader openFile(String fileName)
        throws FileNotFoundException
    {
        return new FileReader(new File(fileName));
    }

    /**
     * Recieves token string value and compares
     * contained words with common US first names
     * 
     * @param value
     * @throws IOException
     * @throws FileNotFoundException
     * @return boolean value
     */
    public boolean commonFirstName(String value) 
        throws IOException
    {
        if(firstNames.contains(value.trim().toLowerCase())){
            return true;
        }
        else
            return false;
    }

    /**
     * Recieves token string value and compares
     * contained words with common US last names
     * 
     * @param value
     * @throws IOException 
     * @throws FileNotFoundException
     * @return boolean value
     */
    public boolean commonLastName(String value) 
        throws IOException
    {
        if(lastNames.contains(value.trim().toLowerCase())){
            return true;
        }
        else
            return false;
    }

    /**
     * Loads the common first names from a file
     * 
     * @param inputString
     * @throws IOException
     */
    public void loadFirstNames() throws IOException{
        String inputString = null;
        String fileName = "src/main/data/CommonFirstNames.txt";
        BufferedReader reader = new BufferedReader(openFile(fileName));

        while ((inputString = reader.readLine()) != null){
            firstNames.add(inputString.trim().toLowerCase());
        }
    }

    /**
     * Loads the common last names from a file
     * 
     * @param inputString
     * @throws IOException
     */
    public void loadLastNames() throws IOException{
        String inputString = null;
        String fileName = "src/main/data/CommonLastNames.txt";
        BufferedReader reader = new BufferedReader(openFile(fileName));
        
        while ((inputString = reader.readLine()) != null){
            lastNames.add(inputString.trim().toLowerCase());
        }
    }

}

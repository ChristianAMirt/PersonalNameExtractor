package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 *  Determines if token contains a common name
 */
public class CommonNames {
    /**
     * Constructor for CommonNames
     */
    public CommonNames(){
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
        String fileName = "src/main/data/CommonFirstNames.txt";
        BufferedReader commonFirstNames = new BufferedReader(openFile(fileName));
        String inputString = commonFirstNames.readLine();

        while (inputString != null){
            if (inputString.contains(value.toLowerCase())){
                commonFirstNames.close();
                return true;
            }
            inputString = commonFirstNames.readLine();
        }
        
        commonFirstNames.close();
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
        String fileName = "src/main/data/CommonLastNames.txt";
        BufferedReader commonLastNames = new BufferedReader(openFile(fileName));
        String inputString = commonLastNames.readLine();

        while (inputString != null){
            if (inputString.contains(value.toLowerCase())){
                commonLastNames.close();
                return true;
            }
            inputString = commonLastNames.readLine();
        }

        commonLastNames.close();
        return false; 
    }

}

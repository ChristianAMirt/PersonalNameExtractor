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

    public FileReader openFile(String fileName)
        throws FileNotFoundException
    {
        return new FileReader(new File(fileName));
    }

    /**
     * Recieves token and compares contained
     * words with common US first names
     * 
     * @param token
     * @throws IOException
     * @throws FileNotFoundException
     * @return boolean value
     */
    public boolean commonFirstName(Token token) 
        throws IOException
    {
        String fileName = "src/main/data/CommonFirstNames.txt";
        BufferedReader commonFirstNames = new BufferedReader(openFile(fileName));
        String inputString = commonFirstNames.readLine();

        while (inputString != null){
            if (inputString.contains(token.getValue().toLowerCase())){
                commonFirstNames.close();
                return true;
            }
            inputString = commonFirstNames.readLine();
        }
        
        commonFirstNames.close();
        return false; 
    }

    /**
     * Recieves token and compares contained
     * words with common US last names
     * 
     * @param token
     * @throws IOException 
     * @throws FileNotFoundException
     * @return boolean value
     */
    public boolean commonLastName(Token token) 
        throws IOException
    {
        String fileName = "src/main/data/CommonLastNames.txt";
        BufferedReader commonLastNames = new BufferedReader(openFile(fileName));
        String inputString = commonLastNames.readLine();

        while (inputString != null){
            if (inputString.contains(token.getValue().toLowerCase())){
                commonLastNames.close();
                return true;
            }
            inputString = commonLastNames.readLine();
        }

        commonLastNames.close();
        return false; 
    }

}

package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 *  Determines if token contains a common name
 */
public class CommonNames {
    /**
     * 
     */
    public CommonNames(){

    }

    /**
     * Recieves token and compares contained
     * words with common US first names
     * 
     * @param token
     * @throws IOException
     * @return boolean value
     */
    public boolean commonFirstName(Token token) 
        throws IOException 
    {
        BufferedReader commonFirstNames = new BufferedReader(new FileReader("src/main/data/CommonFirstNames.txt"));
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
     * @return boolean value
     */
    public boolean commonLastName(Token token) 
        throws IOException
    {
        BufferedReader commonLastNames = new BufferedReader(new FileReader("src/main/data/CommonLastNames.txt"));
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

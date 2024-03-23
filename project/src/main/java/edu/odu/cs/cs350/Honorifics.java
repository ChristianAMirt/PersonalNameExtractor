package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Determines if token string value contains honorifics
 */
public class Honorifics {
    /**
     * Constructor for Honorifics
     */
    public Honorifics(){
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
     * contained words with honorifics which precede 
     * personal names
     * 
     * @param value
     * @throws IOException
     * @throws FileNotFoundException
     * @return boolean value
     */
    public boolean containsHonorifics(String value) 
        throws IOException
    {
        String fileName = "src/main/data/Dictionary.honorifics.txt";
        BufferedReader honorifics = new BufferedReader(openFile(fileName));
        String inputString = honorifics.readLine();

        while (inputString != null){
            if (inputString.contains(value)){
                honorifics.close();
                return true;
            }
            inputString = honorifics.readLine();
        }
        
        honorifics.close();
        return false; 
    }

}

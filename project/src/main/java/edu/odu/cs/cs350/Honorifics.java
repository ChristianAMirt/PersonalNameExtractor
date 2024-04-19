package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Set;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Determines if token string value contains honorifics.
 */
public class Honorifics {

    /**
     * Contains string values loaded from Dictionary.honorifics.txt.
     */
    private Set<String> honorifics;

    /**
     * Constructor for Honorifics.
     * @throws IOException 
     */
    public Honorifics() throws IOException{
        this.honorifics = new HashSet<>();
        loadHonorifics();
    }

    /**
     * Opens input file dependent on string that 
     * contains file name.
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
     * personal names.
     * 
     * @param value
     * @throws IOException
     * @return boolean value
     */
    public boolean containsHonorifics(String value) 
        throws IOException
    {
        if(honorifics.contains(value.trim().toLowerCase())){
            return true;
        }
        else
            return false;
        
    }

    /**
     * Loads data from Dictionary.honorifics.txt to be compaired against.
     * 
     * @throws IOException
     */
    public void loadHonorifics() throws IOException{
        String inputString = null;
        String fileName = "src/main/data/Dictionary.honorifics.txt";
        BufferedReader reader = new BufferedReader(openFile(fileName));

        while ((inputString = reader.readLine()) != null){
            honorifics.add(inputString.trim().toLowerCase());
        }
        reader.close();
    }

}

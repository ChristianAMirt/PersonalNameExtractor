package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Jaylen Wheeler
 * 
 * Tests for the two methods of DictionaryFeature.
 * (The constructor and "determineDictionaryFeature")
 */
public class TestDictionaryFeature{
    
    private DictionaryFeature myDictionaryFeature;

    /**
     * Tests the constructor for DictionaryFeature, making sure that
     * everything is initialized properly.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testDictionaryFeatureConstructor() throws FileNotFoundException, IOException{
        myDictionaryFeature = new DictionaryFeature();
        assertNotNull(myDictionaryFeature);
        assertTrue(myDictionaryFeature.getEnglishDictionarySet().contains("tablespoonfuls"));
    }

    /**
     * Tests the openFile method for DictionaryFeature to see
     * if the fileReader is reading a file
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testOpenFile() throws FileNotFoundException, IOException{
        myDictionaryFeature = new DictionaryFeature();
        FileReader myReader = myDictionaryFeature.openFile("src/main/data/Dictionary.english.txt");
        assertNotNull(myReader);   
    }

    /**
     * Tests the determineDictionaryFeature method for the accuracy
     * of the returned boolean values.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testDetermineDictionaryFeature() throws FileNotFoundException, IOException
    {
        myDictionaryFeature = new DictionaryFeature();
        assertEquals(true , myDictionaryFeature.determineDictionaryFeature("syntax"));
        assertNotEquals(true, myDictionaryFeature.determineDictionaryFeature("bdhiskThisWordShouldNotBeInTheDictionaryJmjhp"));
    }
}

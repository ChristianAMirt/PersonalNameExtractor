package edu.odu.cs.cs350;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jaylen Wheeler
 * 
 * Tests for the method of DictionaryFeature
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDictionaryFeature{
    
    /**
     * DictionaryFeature object that will be used for the testing.
     */
    private DictionaryFeature myDictionaryFeature;

    /**
     * Calls the constructor of dictionary feature only
     * once. This allows for the same DictionaryFeature 
     * object to be used for every test here.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @BeforeAll
    public void callConstructor()throws FileNotFoundException, IOException{
        myDictionaryFeature = new DictionaryFeature();
    }

    /**
     * Tests the constructor for DictionaryFeature, making sure that
     * everything is initialized properly.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testDictionaryFeatureConstructor() throws FileNotFoundException, IOException{
        assertNotNull(myDictionaryFeature);
        assertTrue(myDictionaryFeature.getEnglishDictionarySet().contains("tablespoonfuls"));
    }

    /**
     * Tests the openFile method for DictionaryFeature to see
     * if the fileReader is reading a file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testOpenFile() throws FileNotFoundException, IOException{
        FileReader myReader = myDictionaryFeature.openFile("src/main/data/Dictionary.english.txt");
        assertNotNull(myReader);   
    }

    /**
     * Tests the getEnglishDictionarySet method to confirm
     * that a non-null hashset is returned.
     */
    @Test
    public void testGetEnglishDictionary(){
        assertNotNull(myDictionaryFeature.getEnglishDictionarySet());
        assertEquals(HashSet.class, myDictionaryFeature.getEnglishDictionarySet().getClass());
    }

    @Test
    public void testLoadFile(){
        String[] testDictionary = myDictionaryFeature.getEnglishDictionarySet()
        .toArray(new String[myDictionaryFeature.getEnglishDictionarySet().size()]);
        //assertTrue()
        // I am going to finish this
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
        assertEquals(true , myDictionaryFeature.determineDictionaryFeature("syntax"));
        assertNotEquals(true, myDictionaryFeature.determineDictionaryFeature("bdhiskThisWordShouldNotBeInTheDictionaryJmjhp"));
    }
}

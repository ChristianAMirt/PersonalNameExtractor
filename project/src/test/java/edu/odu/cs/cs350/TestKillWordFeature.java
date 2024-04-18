package edu.odu.cs.cs350;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jaylen Wheeler
 * 
 * Tests for the methods of KillWordFeature.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestKillWordFeature {

    /**
     * KillWordFeature object that will be used for testing.
     */
    private KillWordFeature myKillWordFeature;

    /**
     * Calls the constructor of KillWordFeature only
     * once. This allows for the same KillWordFeature 
     * object to be used for every test here.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @BeforeAll
    public void callConstructor() throws FileNotFoundException, IOException{
        myKillWordFeature = new KillWordFeature();
    }

    /**
     * Tests the constructor for KillWordFeature, making sure that
     * the instantiation of the class of as an object actually happens.
     */
    @Test
    public void testKillWordFeatureConstructor(){
        assertNotNull(myKillWordFeature);
        assertTrue(myKillWordFeature.getKillWordSet().contains("space"));
    }

    /**
     * Tests the openFile method for KillWordFeatire to see
     * if the fileReader is reading a file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testOpenFile() throws FileNotFoundException, IOException{
        FileReader myReader = myKillWordFeature.openFile("src/main/data/Dictionary.nonPersonalProperNames.txt");
        assertNotNull(myReader);   
    }

    /**
     * Tests the getKillWordSet method to confirm
     * that a non-null hashset is returned.
     */
    @Test
    public void testGetKillWordSet(){
        assertNotNull(myKillWordFeature.getKillWordSet());
        assertEquals(HashSet.class, myKillWordFeature.getKillWordSet().getClass());
    }

    /**
     * Tests the loadFile method to confirm that
     * the kill words were loaded into the file.
     */
    @Test
    public void testLoadFile(){
        Set<String> testSet = new HashSet<>();
        testSet.add("technology");
        testSet.add("world");
        testSet.add("laboratory");
        assertTrue(myKillWordFeature.getKillWordSet().containsAll(testSet));
    }

    /**
     * Tests the determineKillWordFeature method for the accuracy
     * of the returned boolean values.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testDetermineKillWordFeature() throws FileNotFoundException, IOException
    {
        assertEquals(true, myKillWordFeature.determineKillWordFeature("pacific"));
        assertEquals(false, myKillWordFeature.determineKillWordFeature("sage"));
    }
    
    /**
     * Tests the determineMultipleKillWordFeatures method for the accuracy
     * of the returned boolean values.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testDetermineMultipleKillWordFeatures() throws  FileNotFoundException, IOException
    {
        assertEquals(true, myKillWordFeature.determineMultipleKillWordFeatures("united", "states"));
        assertEquals(false, myKillWordFeature.determineMultipleKillWordFeatures("Naruto", "Uzumaki"));
        assertEquals(false, myKillWordFeature.determineMultipleKillWordFeatures("mirmar", "snacks"));
    }
}

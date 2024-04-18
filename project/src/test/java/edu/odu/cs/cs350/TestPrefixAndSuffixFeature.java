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
 * Tests the methods of PrefixAndSuffixFeature.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPrefixAndSuffixFeature {

    /**
     * PrefixAndSuffixFeature object that will be used for testing.
     */
    private PrefixAndSuffixFeature myPrefixAndSuffixFeature;

    /**
     * Calls the constructor of PrefixAndSuffixFeature only
     * once. This allows for the same PrefixAndSuffixFeature 
     * object to be used for every test here.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @BeforeAll
    public void callConstructor() throws FileNotFoundException, IOException{
        myPrefixAndSuffixFeature = new PrefixAndSuffixFeature();
    }

    /**
     * Tests the constructor for PrefixAndSuffixFeature, making sure that
     * everything is initialized properly
     */
    @Test
    public void testPrefixAndSuffixFeatureConstructor(){
        assertNotNull(myPrefixAndSuffixFeature);
        assertTrue(myPrefixAndSuffixFeature.getPrefixSet().contains("silva"));
        assertTrue(myPrefixAndSuffixFeature.getSuffixSet().contains("VI"));
    }

    /**
     * Tests the openFile method for PrefixAndSuffixFeature to see
     * if the fileReader is reading a file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testOpenFile() throws FileNotFoundException, IOException{
        FileReader myReader = myPrefixAndSuffixFeature.openFile("src/main/data/Dictionary.prefixes.txt");
        assertNotNull(myReader);   
    }

    /**
     * Tests the getPrefixSet method to confirm
     * that a non-null hashset is returned.
     */
    @Test
    public void testGetPrefixSet(){
        assertNotNull(myPrefixAndSuffixFeature.getPrefixSet());
        assertEquals(HashSet.class, myPrefixAndSuffixFeature.getPrefixSet().getClass());
    }

    /**
     * Tests the getPrefixSet method to confirm
     * that a non-null hashset is returned.
     */
    @Test
    public void testGetSuffixSet(){
        assertNotNull(myPrefixAndSuffixFeature.getSuffixSet());
        assertEquals(HashSet.class, myPrefixAndSuffixFeature.getSuffixSet().getClass());
    }

    /**
     * Tests the loadPrefix method to confirm that
     * the prefix words were loaded into the file.
     */
    @Test
    public void testLoadPrefix(){
        Set<String> testSet = new HashSet<>();
        testSet.add("von");
        testSet.add("zur");
        testSet.add("lopes");
        assertTrue(myPrefixAndSuffixFeature.getPrefixSet().containsAll(testSet));
    }

    /**
     * Tests the loadSuffix method to confirm that
     * the suffix words were loaded into the file.
     */
    @Test
    public void testLoadSuffix(){
        Set<String> testSet = new HashSet<>();
        testSet.add("Jr");
        testSet.add("Sr.");
        testSet.add("VI");
        assertTrue(myPrefixAndSuffixFeature.getSuffixSet().containsAll(testSet));
    }

    /**
     * Tests the determinePrefixFeature method for the accuracy
     * of the returned boolean values.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testDeterminePrefixFeature() throws FileNotFoundException, IOException
    {
        assertEquals(true , myPrefixAndSuffixFeature.determinePrefixFeature("berg"));
        assertNotEquals(true, myPrefixAndSuffixFeature.determinePrefixFeature("HalfLife3IsntReal"));
    }

    /**
     * Tests the determineSuffixFeature method for the accuracy
     * of the returned boolean values.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testDetermineSuffixFeature() throws FileNotFoundException, IOException
    {
        assertEquals(true, myPrefixAndSuffixFeature.determineSuffixFeature("Sr"));
        assertNotEquals(true, myPrefixAndSuffixFeature.determineSuffixFeature("TheCakeIsALie"));
    }
}

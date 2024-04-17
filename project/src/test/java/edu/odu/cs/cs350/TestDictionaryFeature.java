package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Jaylen Wheeler
 * 
 * Tests for the two methods of DictionaryFeature.
 * (The constructor and "determineDictionaryFeature")
 */
public class TestDictionaryFeature {
    
    /**
     * Tests the constructor for DictionaryFeature, making sure that
     * everything is initialized properly.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testDictionaryFeatureConstructor() throws FileNotFoundException, IOException{
        DictionaryFeature firstDictionaryFeature = new DictionaryFeature();
        assertNotNull(firstDictionaryFeature);
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
        DictionaryFeature secondDictionaryFeature = new DictionaryFeature();
        Token firstToken = new Token("syntax");

        assertEquals(true , secondDictionaryFeature.determineDictionaryFeature(firstToken.getValue()));
        
        DictionaryFeature thirdDictionaryFeature = new DictionaryFeature();
        Token secondToken = new Token("bdhiskThisWordShouldNotBeInTheDictionaryJmjhp");

        assertNotEquals(true, thirdDictionaryFeature.determineDictionaryFeature(secondToken.getValue()));
    }
}

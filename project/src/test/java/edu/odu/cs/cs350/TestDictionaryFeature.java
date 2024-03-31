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
     * the instantiation of the class of as an object actually happens.
     */
    @Test
    public void testDictionaryFeatureConstructor(){
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

        assertEquals(secondDictionaryFeature.determineDictionaryFeature(firstToken.getValue()) , true);
        
        DictionaryFeature thirdDictionaryFeature = new DictionaryFeature();
        Token secondToken = new Token("bdhiskThisWordShouldNotBeInTheDictionaryJmjhp");

        assertNotEquals(thirdDictionaryFeature.determineDictionaryFeature(secondToken.getValue()), true);
    }
}

// Note to self: switch the expected and actual values around
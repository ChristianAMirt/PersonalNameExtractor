package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Jaylen Wheeler
 * 
 * Tests for the three methods of KillWordFeature.
 * (The constructor, "determineKillWordFeature," 
 * and "determineMultipleKillWordFeatures")
 */
public class TestKillWordFeature {

    /**
     * Tests the constructor for KillWordFeature, making sure that
     * the instantiation of the class of as an object actually happens.
     */
    @Test
    public void testKillWordFeatureConstructor(){
        KillWordFeature firstKillWordFeature = new KillWordFeature();
        assertNotNull(firstKillWordFeature);
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
        KillWordFeature secondKillWordFeature = new KillWordFeature();
        Token firstToken = new Token("pacific");

        assertEquals(secondKillWordFeature.determineKillWordFeature(firstToken), true);

        Token secondToken = new Token("sage");

        assertEquals(secondKillWordFeature.determineKillWordFeature(secondToken), false);
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
        KillWordFeature thirdKillWordFeature = new KillWordFeature();
        Token thirdToken = new Token("united");
        Token fourthToken = new Token("states");

        assertEquals(thirdKillWordFeature.determineMultipleKillWordFeatures(thirdToken, fourthToken), true);

        Token fifthToken = new Token("Naruto");
        Token sixthToken = new Token("Uzumaki");

        assertEquals(thirdKillWordFeature.determineMultipleKillWordFeatures(fifthToken, sixthToken), false);

        Token seventhToken = new Token("mirmar");
        Token eighthToken = new Token("snacks");

        assertEquals(thirdKillWordFeature.determineMultipleKillWordFeatures(seventhToken, eighthToken), false);
    }
}

package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestToken {

    @Test
    public void testConstructors() {
        Token emptyToken = new Token("");

        assertThat(emptyToken.getValue(), is(""));
        assertThat(emptyToken.getClassification(), is(-1));

        Token firstWordOfSentence = new Token("Although");

        assertThat(firstWordOfSentence.getValue(), is("Although"));
        assertThat(firstWordOfSentence.getClassification(), is(-1));
    }

    /**
     * Tests the setter method for the dictionaryFeature value within Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testSetDictionaryFeature() throws FileNotFoundException, IOException{
        Token dictionaryFeatureToken = new Token("AkiraToriyama");
        dictionaryFeatureToken.setDictionaryFeature();

        assertThat(dictionaryFeatureToken.getDictionaryFeature(), is(false));
    }

    /**
     * Tests the getter method for the dictionaryFeature value within Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testGetDictionaryFeature() throws FileNotFoundException, IOException{
        Token dictionaryFeatureToken = new Token("goat");
        dictionaryFeatureToken.setDictionaryFeature();

        assertNotNull(dictionaryFeatureToken.getDictionaryFeature());
        assertEquals(dictionaryFeatureToken.getDictionaryFeature(), true);
    }
}

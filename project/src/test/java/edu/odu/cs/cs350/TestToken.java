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

        assertNotNull(dictionaryFeatureToken.getDictionaryFeature());
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

        assertEquals(dictionaryFeatureToken.getDictionaryFeature(), true);
    }

    /**
     * Tests the setter method for killWordFeature value within Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testSetKillWordFeature() throws FileNotFoundException, IOException{
        Token killWordFeatureToken = new Token("fuel");
        killWordFeatureToken.setKillWordFeature();

        assertNotNull(killWordFeatureToken.getKillWordFeature());
        assertThat(killWordFeatureToken.getKillWordFeature(), is(true));
    }

    /**
     * Tests the getter method for the killWordFeature value within Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testGetKillWordFeature() throws FileNotFoundException, IOException{
        Token killWordFeatureToken = new Token("Luffy");
        killWordFeatureToken.setKillWordFeature();

        assertEquals(killWordFeatureToken.getKillWordFeature(), false);
    }

    /**
     * Test for setter method for commonFirstName
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testSetCommonFirstName()throws FileNotFoundException, IOException{
        Token commonFirstNameToken = new Token("brendan");
        commonFirstNameToken.setCommonFirstName();

        assertNotNull(commonFirstNameToken.getCommonFirstName());
        assertThat(commonFirstNameToken.getCommonFirstName(), is(true));
    }

    /**
     * Test for getter method for commonFirstName
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testGetCommonFirstName() throws FileNotFoundException, IOException{
        Token commonFirstNameToken = new Token("hearrell");
        commonFirstNameToken.setCommonFirstName();

        assertEquals(commonFirstNameToken.getCommonFirstName(), false);
    }

    /**
     *  Test for setter method for commonLastName
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testSetCommonLastName()throws FileNotFoundException, IOException{
        Token commonLastNameToken = new Token("smith");
        commonLastNameToken.setCommonLastName();

        assertNotNull(commonLastNameToken.getCommonLastName());
        assertThat(commonLastNameToken.getCommonLastName(), is(true));
    }

    /**
     * Test for getter method for commonLastName
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testGetCommonLastName() throws FileNotFoundException, IOException{
        Token commonLastNameToken = new Token("hearrell");
        commonLastNameToken.setCommonLastName();

        assertEquals(commonLastNameToken.getCommonLastName(), false);
    }
}

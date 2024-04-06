package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestToken {

    /**
     * Tests the constructor for Token.
     * 
     * @throws IOException
     */
    @Test
    public void testConstructors() throws IOException {
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
    public void testSetDictionaryFeature() throws FileNotFoundException, IOException {
        Token dictionaryFeatureToken = new Token("AkiraToriyama"); // DBZ reference
        dictionaryFeatureToken.setDictionaryFeature();

        assertNotNull(dictionaryFeatureToken.getDictionaryFeature());
        assertEquals(false, dictionaryFeatureToken.getDictionaryFeature());
    }

    /**
     * Tests the getter method for the dictionaryFeature value within Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testGetDictionaryFeature() throws FileNotFoundException, IOException {
        Token dictionaryFeatureToken = new Token("goat");
        dictionaryFeatureToken.setDictionaryFeature();

        assertEquals(true, dictionaryFeatureToken.getDictionaryFeature());
    }

    /**
     * Tests the setter method for killWordFeature value within Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testSetKillWordFeature() throws FileNotFoundException, IOException {
        Token killWordFeatureToken = new Token("fuel");
        killWordFeatureToken.setKillWordFeature();

        assertNotNull(killWordFeatureToken.getKillWordFeature());
        assertEquals(true, killWordFeatureToken.getKillWordFeature());
    }

    /**
     * Tests the getter method for the killWordFeature value within Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testGetKillWordFeature() throws FileNotFoundException, IOException {
        Token killWordFeatureToken = new Token("Luffy");
        killWordFeatureToken.setKillWordFeature();

        assertEquals(false, killWordFeatureToken.getKillWordFeature());
    }

    /**
     * Test for setter method for commonFirstName
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testSetCommonFirstName() throws FileNotFoundException, IOException {
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
    public void testGetCommonFirstName() throws FileNotFoundException, IOException {
        Token commonFirstNameToken = new Token("hearrell");
        commonFirstNameToken.setCommonFirstName();

        assertEquals(false, commonFirstNameToken.getCommonFirstName());
    }

    /**
     * Test for setter method for commonLastName
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testSetCommonLastName() throws FileNotFoundException, IOException {
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
    public void testGetCommonLastName() throws FileNotFoundException, IOException {
        Token commonLastNameToken = new Token("hearrell");
        commonLastNameToken.setCommonLastName();

        assertEquals(false, commonLastNameToken.getCommonLastName());
    }

    /**
     * Test for setter method for honorifics feature
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testSetHonorifics() throws FileNotFoundException, IOException {
        Token honorificsToken = new Token("Mr");
        honorificsToken.setHonorificsValue();

        assertNotNull(honorificsToken.getHonorificsValue());
        assertThat(honorificsToken.getHonorificsValue(), is(true));
    }

    /**
     * Test for getter method for honorifics feature
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testGetHonorificsFeature() throws FileNotFoundException, IOException {
        Token honorificsToken = new Token("SyntaxSages");
        honorificsToken.setHonorificsValue();

        assertEquals(false, honorificsToken.getHonorificsValue());
    }

    /**
     * Tests the setter method for the prefixFeature value within Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testSetPrefixFeature() throws FileNotFoundException, IOException {
        Token prefixFeatureToken = new Token("costa");
        prefixFeatureToken.setPrefixFeature();

        assertNotNull(prefixFeatureToken.getPrefixFeature());
        assertEquals(true, prefixFeatureToken.getPrefixFeature());
    }

    /**
     * Tests the getter method for the prefixFeature value within Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testGetPrefixFeature() throws FileNotFoundException, IOException {
        Token prefixFeatureToken = new Token("Aegislash");
        prefixFeatureToken.setPrefixFeature();

        assertEquals(false, prefixFeatureToken.getPrefixFeature());
    }

    /**
     * Tests the setter method for the suffixFeature value within Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testSetSuffixFeature() throws FileNotFoundException, IOException {
        Token suffixFeatureToken = new Token("VI");
        suffixFeatureToken.setSuffixFeature();

        assertNotNull(suffixFeatureToken.getSuffixFeature());
        assertEquals(true, suffixFeatureToken.getSuffixFeature());
    }

    /**
     * Tests the getter method for the suffixFeature value within Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testGetSuffixFeature() throws FileNotFoundException, IOException {
        Token suffixFeatureToken = new Token("Exodia");
        suffixFeatureToken.setSuffixFeature();

        assertEquals(false, suffixFeatureToken.getSuffixFeature());
    }

    /**
     * Test for setter method for Author first name feature
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testSetAuthorFirstName() throws FileNotFoundException, IOException {
        Token firstName = new Token("brendan");
        firstName.setAuthorFirstName();

        assertNotNull(firstName.getAuthorFirstName());
        assertThat(firstName.getAuthorFirstName(), is(true));
    }

    /**
     * Test for getter method for Author first name feature
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testGetAuthorFirstName() throws FileNotFoundException, IOException {
        Token firstName = new Token("SyntaxSages");
        firstName.setAuthorFirstName();

        assertEquals(false, firstName.getAuthorFirstName());
    }

    /**
     * Test for setter method for Author last name feature
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testSetAuthorLastName() throws FileNotFoundException, IOException {
        Token lastName = new Token("hearrell");
        lastName.setAuthorLastName();

        assertNotNull(lastName.getAuthorLastName());
        assertThat(lastName.getAuthorLastName(), is(true));
    }

    /**
     * Test for getter method for Author last name feature
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testGetAuthorLastName() throws FileNotFoundException, IOException {
        Token lastName = new Token("SyntaxSages");
        lastName.setAuthorLastName();

        assertEquals(false, lastName.getAuthorLastName());
    }

    @Test
    public void testSetPartOfSpeech() throws FileNotFoundException,
            IOException {
        PartsOfSpeech partsOfSpeech = new PartsOfSpeech();

        Token t1 = new Token("thus");
        Token t2 = new Token("willing");

        String t1Result = partsOfSpeech.checkForPartsOfSpeech(t1.getValue());

        t1.setPartOfSpeech(t1);
        t2.setPartOfSpeech();

        assertThat(t1.getPartOfSpeech(), is("conjunction"));
        assertThat(t2.getPartOfSpeech(), is("other"));
    }

    /**
     * Tests the setter method for the lexicalFeature value in Token
     * 
     * @throws IOException
     */
    @Test
    public void testSetLexicalFeature() throws IOException {
        Token lexicalFeatureToken = new Token("HELLO");
        lexicalFeatureToken.setLexicalFeature();

        assertNotNull(lexicalFeatureToken.getLexicalFeature());
        assertEquals("AllCaps", lexicalFeatureToken.getLexicalFeature());
    }

    /**
     * Tests the getter method for the lexicalFeature value in Token
     * 
     * @throws IOException
     */
    @Test
    public void testGetLexicalFeature() throws IOException {
        Token lexicalFeatureToken = new Token("World");
        lexicalFeatureToken.setLexicalFeature();

        assertEquals("capitalized", lexicalFeatureToken.getLexicalFeature());
    }
}

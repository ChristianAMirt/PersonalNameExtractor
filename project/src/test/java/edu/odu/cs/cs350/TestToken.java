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
        DictionaryFeature dictionaryFeature = new DictionaryFeature();

        boolean dictionaryFeatureTokenResult = dictionaryFeature
                .determineDictionaryFeature(dictionaryFeatureToken.getValue());
        dictionaryFeatureToken.setDictionaryFeature(dictionaryFeatureTokenResult);

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
        DictionaryFeature dictionaryFeature = new DictionaryFeature();

        boolean dictionaryFeatureTokenResult = dictionaryFeature
                .determineDictionaryFeature(dictionaryFeatureToken.getValue());
        dictionaryFeatureToken.setDictionaryFeature(dictionaryFeatureTokenResult);

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
        KillWordFeature killWordFeature = new KillWordFeature();

        boolean killWordFeatureResult = killWordFeature.determineKillWordFeature(killWordFeatureToken.getValue());
        killWordFeatureToken.setKillWordFeature(killWordFeatureResult);

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
        KillWordFeature killWordFeature = new KillWordFeature();

        boolean killWordFeatureResult = killWordFeature.determineKillWordFeature(killWordFeatureToken.getValue());
        killWordFeatureToken.setKillWordFeature(killWordFeatureResult);

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
        CommonNames commonFirstName = new CommonNames();

        boolean expectedName = commonFirstName.commonFirstName(commonFirstNameToken.getValue());
        commonFirstNameToken.setCommonFirstName(expectedName);

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
        CommonNames commonFirstName = new CommonNames();

        boolean expectedName = commonFirstName.commonFirstName(commonFirstNameToken.getValue());
        commonFirstNameToken.setCommonFirstName(expectedName);

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
        CommonNames commonLastName = new CommonNames();

        boolean expectedName = commonLastName.commonFirstName(commonLastNameToken.getValue());
        commonLastNameToken.setCommonLastName(expectedName);

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
        CommonNames commonLastName = new CommonNames();

        boolean expectedName = commonLastName.commonFirstName(commonLastNameToken.getValue());
        commonLastNameToken.setCommonLastName(expectedName);

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
        Honorifics honorifics = new Honorifics();

        boolean expectedValue = honorifics.containsHonorifics(honorificsToken.getValue());
        honorificsToken.setHonorificsValue(expectedValue);

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
        Honorifics honorifics = new Honorifics();

        boolean expectedValue = honorifics.containsHonorifics(honorificsToken.getValue());
        honorificsToken.setHonorificsValue(expectedValue);

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
        PrefixAndSuffixFeature prefixAndSuffixFeature = new PrefixAndSuffixFeature();

        boolean prefixFeatureResult = prefixAndSuffixFeature.determinePrefixFeature(prefixFeatureToken.getValue());
        prefixFeatureToken.setPrefixFeature(prefixFeatureResult);

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
        PrefixAndSuffixFeature prefixAndSuffixFeature = new PrefixAndSuffixFeature();

        boolean prefixFeatureResult = prefixAndSuffixFeature.determinePrefixFeature(prefixFeatureToken.getValue());
        prefixFeatureToken.setPrefixFeature(prefixFeatureResult);

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
        PrefixAndSuffixFeature prefixAndSuffixFeature = new PrefixAndSuffixFeature();

        boolean suffixFeatureResult = prefixAndSuffixFeature.determineSuffixFeature(suffixFeatureToken.getValue());
        suffixFeatureToken.setSuffixFeature(suffixFeatureResult);

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
        PrefixAndSuffixFeature prefixAndSuffixFeature = new PrefixAndSuffixFeature();

        boolean suffixFeatureResult = prefixAndSuffixFeature.determineSuffixFeature(suffixFeatureToken.getValue());
        suffixFeatureToken.setSuffixFeature(suffixFeatureResult);

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
        KnownAuthors authorFirstName = new KnownAuthors();

        boolean expectedName = authorFirstName.firstName(firstName.getValue());
        firstName.setAuthorFirstName(expectedName);

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
        KnownAuthors authorFirstName = new KnownAuthors();

        boolean expectedName = authorFirstName.firstName(firstName.getValue());
        firstName.setAuthorFirstName(expectedName);

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
        KnownAuthors authorLastName = new KnownAuthors();

        boolean expectedName = authorLastName.lastName(lastName.getValue());
        lastName.setAuthorLastName(expectedName);

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
        KnownAuthors authorLastName = new KnownAuthors();

        boolean expectedName = authorLastName.lastName(lastName.getValue());
        lastName.setAuthorLastName(expectedName);

        assertEquals(false, lastName.getAuthorLastName());
    }

    @Test
    public void testSetPartOfSpeech() throws FileNotFoundException, IOException {
        PartsOfSpeech partsOfSpeech = new PartsOfSpeech();

        Token t1 = new Token("thus");
        Token t2 = new Token("willing");

        String t1Result = partsOfSpeech.checkForPartsOfSpeech(t1.getValue());
        String t2Result = partsOfSpeech.checkForPartsOfSpeech(t2.getValue());

        t1.setPartOfSpeech(t1Result);
        t2.setPartOfSpeech(t2Result);

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
        LexicalFeature lexicalFeature = new LexicalFeature();

        String lexicalFeatureResult = lexicalFeature.determineLexicalFeature(lexicalFeatureToken.getValue());
        lexicalFeatureToken.setLexicalFeature(lexicalFeatureResult);

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
        LexicalFeature lexicalFeature = new LexicalFeature();

        String lexicalFeatureResult = lexicalFeature.determineLexicalFeature(lexicalFeatureToken.getValue());
        lexicalFeatureToken.setLexicalFeature(lexicalFeatureResult);

        assertEquals("capitalized", lexicalFeatureToken.getLexicalFeature());
    }

    @Test
    public void testSetLocation() throws IOException {
        String mystring = new String("Indianna");
        Token token = new Token(mystring);
        LocationLookup location = new LocationLookup();

        var result =  location.checkLocation(mystring);
        token.setIsLocation(result);
        assertFalse(token.getIsLocation());

        token.setIsLocation(true);
        assertTrue(token.getIsLocation());
    }
}

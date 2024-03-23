package edu.odu.cs.cs350;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Tokens represent each word and punction mark from a given block of text.
 * The purpose of seperating the text is to allow for easy traversal
 * over the text.
 */
public class Token {

    /**
     * Word or punctuation mark that is being held by token.
     */
    private String value;

    /**
     * Represents if the token is the beginning or end of name, not a name,
     * or not yet classified yet.
     */
    private int classification;

    /**
     * An identifier for whether or not the token is part
     * of the English dictionary
     */
    private boolean dictionaryFeature;

    /**
     * An identifier for whether or not the token is
     * a kill word
     */
    private boolean killWordFeature;

    /**
     * An identifier for whether or not token is a common first name
     */
    private boolean commonFirstName;

    /**
     * An identifier for whether or not token is a common Last name
     */
    private boolean commonLastName;

    /**
     * An identifier for whether or not token contains honorfiics
     * preceding personal name
     */
    private boolean honorificsValue;

    /**
     * Collection of features that is contains characteristics about the token.
     */
    // private Feature features[NUMBER OF CHARACTERISTICS]; Need feature classes

    /**
     * Create a new Token.
     * 
     * @param value word or punctuation mark represented by the token.
     */
    public Token(String value) throws IOException {
        this.value = value;
        this.classification = -1;

        setCommonFirstName();
        setCommonLastName();
    }

    /**
     * @return value in Token.
     */
    public String getValue() {
        return value;
    }

    /**
     * @return classification of Token.
     */
    public int getClassification() {
        return classification;
    }

    /**
     * sets the dictionaryFeature value of Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void setDictionaryFeature() throws FileNotFoundException, IOException {
        dictionaryFeature = new DictionaryFeature().determineDictionaryFeature(value);
    }

    /**
     * Returns the dictionaryFeature value of Token
     * 
     * @return boolean
     */
    public boolean getDictionaryFeature() {
        return dictionaryFeature;
    }

    /**
     * sets the killWordFeature value of Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void setKillWordFeature() throws FileNotFoundException, IOException {
        killWordFeature = new KillWordFeature().determineKillWordFeature(value);
    }

    /**
     * Returns the killWordFeature value of Token
     * 
     * @return boolean
     */
    public boolean getKillWordFeature() {
        return killWordFeature;
    }

    /**
     * Sets commonFirstName value of Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void setCommonFirstName() throws FileNotFoundException, IOException {
        commonFirstName = new CommonNames().commonFirstName(value);
    }

    /**
     * Returns commonFirstName value of Token
     * 
     * @return boolean value
     */
    public boolean getCommonFirstName() {
        return commonFirstName;
    }

    /**
     * Sets commonLastName value of Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void setCommonLastName() throws FileNotFoundException, IOException {
        commonLastName = new CommonNames().commonLastName(value);
    }

    /**
     * Returns commonLastName value of Token
     * 
     * @return boolean value
     */
    public boolean getCommonLastName() {
        return commonLastName;
    }

    /**
     * Sets honorifics value of Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void setHonorificsValue() throws FileNotFoundException, IOException {
        honorificsValue = new Honorifics().containsHonorifics(value);
    }

    /**
     * Returns commonLastName value of Token
     * 
     * @return boolean value
     */
    public boolean getHonorificsValue() {
        return honorificsValue;
    }
}
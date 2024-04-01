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
     * An identifier for wether or not token contains locations
     * or geographical features
     */
    private boolean location;

    /**
     * An identifier for whether or not the token is a prefix
     */
    private boolean prefixFeature;

    /**
     * An identifier for whether or not the token is a suffix
     */    
    private boolean suffixFeature;

    /**
     * An identifier for known Author first names
     */
    private boolean authorFirstName;

    /**
     * An identifier for known Author last names
     */
    private boolean authorLastName;

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

    /**
     * Sets honorifics value of Token
     * 
     * @throws IOException
     */
    public void setIsLocation() throws IOException {
        location = new LocationLookup().checkLocation(value);
    }

    /**
     * Returns commonLastName value of Token
     * 
     * @return boolean value
     */
    public boolean getIsLocation() {
        return location;
    }

    /**
     * sets the prefixFeature value of Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void setPrefixFeature() throws FileNotFoundException, IOException {
        prefixFeature = new PrefixAndSuffixFeature().determinePrefixFeature(value);
    }

    /**
     * Returns the prefixFeature value of Token
     * 
     * @return boolean
     */
    public boolean getPrefixFeature() {
        return prefixFeature;
    }

    /**
     * sets the suffixFeature value of Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void setSuffixFeature() throws FileNotFoundException, IOException {
        suffixFeature = new PrefixAndSuffixFeature().determineSuffixFeature(value);
    }

    /**
     * Returns the suffixFeature value of Token
     * 
     * @return boolean
     */
    public boolean getSuffixFeature() {
        return suffixFeature;
    }

    /**
     * Sets Author first name value of Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void setAuthorFirstName() throws FileNotFoundException, IOException {
        authorFirstName = new KnownAuthors().firstName(value);
    }

    /**
     * Returns Author first name value of Token
     * 
     * @return boolean value
     */
    public boolean getAuthorFirstName() {
        return authorFirstName;
    }

    /**
     * Sets Author last name value of Token
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void setAuthorLastName() throws FileNotFoundException, IOException {
        authorLastName = new KnownAuthors().lastName(value);
    }

    /**
     * Returns Author last name value of Token
     * 
     * @return boolean value
     */
    public boolean getAuthorLastName() {
        return authorLastName;
    }
}
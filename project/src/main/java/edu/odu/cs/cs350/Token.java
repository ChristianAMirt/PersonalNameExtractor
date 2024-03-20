package edu.odu.cs.cs350;

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
     * Collection of features that is contains characteristics about the token.
     */
    // private Feature features[NUMBER OF CHARACTERISTICS]; Need feature classes

    /**
     * Create a new Token.
     * 
     * @param value word or punctuation mark represented by the token.
     */
    public Token(String value) {
        this.value = value;
        this.classification = -1;
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
}
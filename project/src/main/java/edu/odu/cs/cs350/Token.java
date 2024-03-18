package edu.odu.cs.cs350;

import java.util.Iterator;

/**
 * Tokens represent each word and punction mark from a given block of text.
 * The purpose of seperating the text is to allow for easy traversal
 * over the text.
 */
public class Token /* implements Iterable<Feature> */ {

    /**
     * Word or punctuation mark that is being held by token
     */
    private String value;

    /**
     * Represents if the token is the beginning or end of name, not a name,
     * or not yet classified yet
     */
    private int classification;

    /**
     * Collection of features that is contains characteristics about the token
     */
    // private ...<Feature> features; Need features class and subclasses to be built
    // before using this

    /**
     * Create a new Token.
     * 
     * @param value word or punctuation mark represented by the token
     */
    public Token(String value) {
        this.value = value;
        this.classification = -1;
    }

    /**
     * @return value in Token
     */
    public String getValue() {
        return value;
    }

    /**
     * @return classification of Token
     */
    public int getClassification() {
        return classification;
    }

    /**
     * @return iterator for collection of features
     */
    // public iterator<Feature> iterator() {}

    /**
     * @return size of collection of featurs
     */
    // public int size() {}

}
/*
 * Notes for this class:
 * - What other functionality does a Token serve other than a data class?
 * 
 * - If this is just a data class, should it even be a seperate file? Wouldn't
 * this class be represented by the equivalent of a struct in C++? This
 * would just be a Token class within the document class with public data
 * members. If all we are is constructors and getters and setters, I think
 * the answer is yes.
 * 
 * - We need to determine what kind of data structure is being used to store the
 * collection of features. This will determine whether the Iterable interface is
 * used or not.
 */
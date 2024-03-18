package edu.odu.cs.cs350;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * A Document will represent the string that is given in the input. It is
 * parsed into tokens, with each word and certain punctuation marks having its
 * own token.
 * 
 * Either white space or a new line character ("\n") exists
 * between each token for words. A punctuation mark such as a period or
 * comma will be considered its own token seperate from any surrounding words.
 * A hyphenated word or a word with an apostrophe (e.g., there's) is an example
 * of punctation marks that will not be a seperate token.
 */
public class Document implements Iterable<Token> {

    /**
     * Collection of tokens that hold words and certain punctuation marks from
     * the given input string.
     */
    private LinkedList<Token> allTokens;

    /**
     * String given provided by the user. This is parsed into tokens.
     */
    private String inputText;

    /**
     * Create a new Document.
     * 
     * @param inputText
     */
    public Document(String inputText) {
        this.inputText = inputText;

        allTokens = new LinkedList<Token>();
    }

    public int size() {
        return allTokens.size();
    }

    @Override
    public Iterator<Token> iterator()
    {
        return allTokens.iterator();
    }

}
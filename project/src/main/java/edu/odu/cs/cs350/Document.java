package edu.odu.cs.cs350;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.Scanner;

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
     * Constant for Scanner delimiter is one or more white spaces
     */
    private static final String TOKEN_DELIMITERS = "[\\s]";

    /**
     * Constant list for punctation marks that can be seperate tokens
     */
    private static final char PUNCTUATION_MARKS[] = { '.', ',', '!', '?' };

    /**
     * Collection of tokens that hold words and certain punctuation marks from
     * the given input string.
     */
    private List<Token> allTokens;

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
        parseDocument();
    }

    /**
     * @return String of document
     */
    public String getInputText() {
        return inputText;
    }

    /**
     * Places each word (seperated by whitespace newlines) in
     * a token and adds it to the collection of tokens
     */
    public void parseDocument() {
        Scanner scanner = new Scanner(inputText);

        scanner.useDelimiter(TOKEN_DELIMITERS);

        while (scanner.hasNext()) {

            String nextWord = scanner.next();
            Token nextToken;

            // If it contains punctuation add just the word first
            char lastChar = nextWord.charAt(nextWord.length() - 1);
            if (containsPunctuation(lastChar)) {
                nextToken = new Token(nextWord.substring(0, nextWord.length() - 1));
                allTokens.add(nextToken);
                nextToken = new Token(Character.toString(lastChar));
            } else
                nextToken = new Token(nextWord);

            allTokens.add(nextToken);
        }
        scanner.close();
    }

    /**
     * 
     * @param character is checked to see if it is a punctuation mark that
     *                  can be a seperate token
     * @return true if it is a punction mark
     */
    public boolean containsPunctuation(char character) {
        for (char knownPunctuation : PUNCTUATION_MARKS) {
            if (character == knownPunctuation)
                return true;
        }
        return false;
    }

    /**
     * @return size of token collection
     */
    public int size() {
        return allTokens.size();
    }

    /**
     * @return iterator of token collection
     */
    @Override
    public Iterator<Token> iterator() {
        return allTokens.iterator();
    }

}
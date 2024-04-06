package edu.odu.cs.cs350;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
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
     * Constant for Scanner delimiter is one or more white spaces.
     */
    private static final String TOKEN_DELIMITERS = "[\\s]";

    /**
     * Regular expression for punctation marks that can be seperate tokens.
     */
    private static final String PUNCTUATION_MARKS = "(?<=\")|(?=[.,!?;:()\"&-])";// add more marks next to "&"

    /**
     * Collection of tokens that hold words and certain punctuation marks from
     * the given input string.
     */
    private List<Token> allTokens;

    /**
     * String provided by the user to be parsed into tokens.
     */
    private String inputText;

    /**
     * Create a new Document.
     * 
     * @param inputText
     */
    public Document(String inputText) throws IOException {
        this.inputText = inputText;

        allTokens = new LinkedList<Token>();
        parseDocument();
    }

    /**
     * @return String of document.
     */
    public String getInputText() {
        return inputText;
    }

    /**
     * Add a token at the given postion of the collection.
     * 
     * @param index location to add Toekn
     * @param toAdd Token to add
     */
    public void addToken(ListIterator<Token> index, Token toAdd) {
        index.add(toAdd);
    }

    /**
     * Places each word and certain punctuation marks in a token and adds it to
     * the collection of tokens.
     */
    public void parseDocument() throws IOException {
        Scanner scanner = new Scanner(inputText);

        scanner.useDelimiter(TOKEN_DELIMITERS);

        while (scanner.hasNext()) {

            String nextWord = scanner.next();
            Token nextToken;

            // split if string contains punctuation
            String separatedNextWord[] = nextWord.split(PUNCTUATION_MARKS);
            for (String phrase : separatedNextWord) {
                nextToken = new Token(phrase);
                allTokens.add(nextToken);
            }
        }
        scanner.close();
    }

    /**
     * @return size of token collection.
     */
    public int size() {
        return allTokens.size();
    }

    /**
     * @return iterator of token collection.
     */
    @Override
    public ListIterator<Token> iterator() {
        return allTokens.listIterator();
    }

}
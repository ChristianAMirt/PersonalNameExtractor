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
     * To be part of a Feature object.
     */
    CommonNames commonNames = new CommonNames();
    /**
     * To be part of a Feature object.
     */
    DictionaryFeature dictionaryFeature = new DictionaryFeature();
    /**
     * To be part of a Feature object.
     */
    Honorifics honorifics = new Honorifics();
    /**
     * To be part of a Feature object.
     */
    KillWordFeature killWordFeature = new KillWordFeature();
    /**
     * To be part of a Feature object.
     */
    KnownAuthors knownAuthors = new KnownAuthors();
    /**
     * To be part of a Feature object.
     */
    LexicalFeature lexicalFeature = new LexicalFeature();
    /**
     * To be part of a Feature object.
     */
    LocationLookup locationLookup = new LocationLookup();
    /**
     * To be part of a Feature object.
     */
    PartsOfSpeech partsOfSpeech = new PartsOfSpeech();
    /**
     * To be part of a Feature object.
     */
    PrefixAndSuffixFeature prefixAndSuffixFeature = new PrefixAndSuffixFeature();

    /**
     * Create a new Document.
     * 
     * @param inputText
     * @throws IOException
     */
    public Document(String inputText) throws IOException {
        this.inputText = inputText;

        allTokens = new LinkedList<Token>();
    }

    /**
     * Gets the input text.
     * 
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
     * 
     * @throws IOException
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
                // Temporary
                nextToken.setCommonFirstName(commonNames.commonFirstName(phrase));
                nextToken.setCommonLastName(commonNames.commonLastName(phrase));
                nextToken.setDictionaryFeature(dictionaryFeature.determineDictionaryFeature(phrase));
                nextToken.setKillWordFeature(killWordFeature.determineKillWordFeature(phrase));
                nextToken.setHonorificsValue(honorifics.containsHonorifics(phrase));
                nextToken.setIsLocation(locationLookup.checkLocation(phrase));
                nextToken.setPrefixFeature(prefixAndSuffixFeature.determinePrefixFeature(phrase));
                nextToken.setSuffixFeature(prefixAndSuffixFeature.determineSuffixFeature(phrase));
                nextToken.setAuthorFirstName(knownAuthors.firstName(phrase));
                nextToken.setAuthorFirstName(knownAuthors.lastName(phrase));
                nextToken.setPartOfSpeech(partsOfSpeech.checkForPartsOfSpeech(phrase));
                nextToken.setLexicalFeature(lexicalFeature.determineLexicalFeature(phrase));

                allTokens.add(nextToken);
            }
        }
        scanner.close();
    }

    /**
     * Returns the size of the token collection.
     * 
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
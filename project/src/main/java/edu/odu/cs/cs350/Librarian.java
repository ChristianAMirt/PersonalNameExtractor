package edu.odu.cs.cs350;

import java.io.IOException;
import java.util.Vector;
import java.util.ListIterator;

/**
 * The Librarian is responsible for determining what parts of the given input
 * need to evaluated and the actions that will be taken on these sections of
 * data in order to extract meta data. The Librarian will then reconstruct the
 * input back together and include the PER tags around personal names
 * that are found.
 * 
 * The input may be at most a page of text (4000 characters), or 50 lines of
 * 80 characters per line. Any input exceeding this amout will not be accepted,
 * and will throw an exception.
 * 
 * The inputPage variable is checked for beginning and ending NER substring tags
 * If found, the inputDocuments vector is iterated and the text data is
 * added for each tag occurance.
 */
public class Librarian {

    /**
     * Raw input string provided by the user.
     */
    private String inputPage;

    /**
     * Collection of each part of input string that is surrounded by
     * NER tags.
     */
    private Vector<Document> inputDocuments;

    /**
     * Takes in a string from the user to read
     * @param inputPage is the string given by the user
     * 
     * @throws IOException if input is larger than 4000 characters
     */
    public Librarian(String inputPage) throws IOException {
        this.inputPage = inputPage;

        inputDocuments = new Vector<Document>();

        if (inputPage.length() > 4000)
            throw new IOException();
        else {
            if (inputPage != "") {
                String[] stringArray = splitInputPage(this.inputPage);
                int arraySize = stringArray.length;
                for (int i = 0; i < arraySize; i++) {
                    Document document = new Document(stringArray[i]);
                    inputDocuments.add(document);

                    if (stringArray[i].contains("<NER>"))
                        document.parseDocument();
                }
            }
        }
    }

    /**
     * Checks to see if documents have been added
     * @return true if one or more Documents have been added to collection
     */
    public boolean hasDocuments() {
        if (inputDocuments.size() > 0)
            return true;
        return false;
    }

    /**
     * Returns the number of elements that have been added to inputDocuments.
     */
    public int getDocumentsSize() {
        return inputDocuments.size();
    }

    /**
     * Returns the document at the specified index
     * @param index of the vector of Documents that is being retrived
     * @return the document object at that index
     */
    public Document getDocumentAt(int index) {
        return inputDocuments.elementAt(index);
    }

    /**
     * Splits the raw inputText right after each /NER.
     */
    public String[] splitInputPage(String textString) {
        String arrayNERStrings[] = inputPage.split("(?=<NER>)");
        return arrayNERStrings;
    }

    /**
     * Returns the substring text between the NER tags
     */
    public String getTextBetweenNERTags(String inputText) {

        String NER_TAG_START = "<NER>";
        String NER_TAG_END = "</NER>";

        int indexNERStart = 0;
        int indexNEREnd = 0;

        if (inputText.contains(NER_TAG_START) == true) {
            indexNERStart = getTagIndex(inputText, NER_TAG_START);
            indexNERStart = indexNERStart + NER_TAG_START.length();
        }

        if (inputText.contains(NER_TAG_END) == true) {
            indexNEREnd = getTagIndex(inputText, NER_TAG_END);
        }

        if ((indexNERStart != 0) && (indexNEREnd != 0) && (indexNERStart < indexNEREnd))
            return inputText.substring(indexNERStart, indexNEREnd);
        else
            return "";

    }

    /**
     * Returns index of "tag" within "inputString".
     */
    public int getTagIndex(String inputString, String tag) {
        return inputString.indexOf(tag);
    }

    /**
     * @return The input document with any changes made
     */
    @Override
    public String toString() {
        StringBuilder markedUp = new StringBuilder();

        // Super temporary, lots of other checks to do
        for (Document document : inputDocuments) {
            if (document.size() > 0) {
                for (Token token : document)
                    markedUp.append(token.getValue() + " ");
            } else {
                markedUp.append(document.getInputText());
            }
        }

        return markedUp.toString();
    }

    /**
     * Print the output of a text string to the console.
     */
    public void printDocumentText(String outputText) {
        System.out.println(outputText);
    }

    /**
     * Temporary stub for what the learning machine will be doing. If the
     * first name field is set to true, a PER tag will get added before.
     * If the last name field is set to true for the token, a PER tag will be
     * inserted after the Token.
     */
    public void markNames() throws IOException {
        for (Document document : inputDocuments) {
            ListIterator<Token> current = document.iterator();
            while (current.hasNext()) {
                Token tokenValue = current.next();
                if (needsTagBefore(tokenValue)) {
                    current.previous();
                    current.add(new Token("<PER>"));
                    current.next();
                } else if (needsTagAfter(tokenValue))
                    current.add(new Token("</PER>"));
            }
        }

    }

    private boolean needsTagBefore(Token nextToken) {
        return nextToken.getCommonFirstName();
    }

    private boolean needsTagAfter(Token nextToken) {
        return nextToken.getCommonLastName();
    }

}

package edu.odu.cs.cs350;

import java.io.IOException;
import java.util.Vector;

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
 * The Librarian takes looks for the NER tags ... (FILL THIS IN WHOEVER
 * HAS THIS STORY)
 */
public class Librarian {

    /**
     * Collection of each part of input string that is surrounded by
     * NER tags.
     */
    private Vector<Document> inputDocuments;

    /**
     * 
     * @param inputPage is the string given by the user
     * 
     * @throws IOException if input is larger than 4000 characters
     */
    public Librarian(String inputPage) throws IOException {
        inputDocuments = new Vector<Document>();

        if (inputPage.length() > 4000)
            throw new IOException();
        else {
            if (inputPage != "") {
                Document document = new Document(inputPage);
                inputDocuments.add(document);
            }
        }
        // THIS MAY BE MODIFIED TO ONLY CREATE A DOCUMENT FROM TEXT WITH NER TAG
    }

    /**
     * 
     * @return true if one or more Documents have been added to collection
     */
    public boolean hasDocuments() {
        if (inputDocuments.size() > 0)
            return true;
        return false;
    }
}

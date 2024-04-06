package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.ListIterator;

public class TestDocument {

    @Test
    public void testConstructors() throws IOException {
        assertDoesNotThrow(() -> new Document(""));
    }

    @Test
    public void testParsing() throws IOException {
        Document emptyDoc = new Document("");
        emptyDoc.parseDocument();

        assertThat(emptyDoc.getInputText(), is(""));
        assertThat(emptyDoc.size(), is(0));

        Document firstLine = new Document("Written by John Doe and Randy Butternubs.");
        firstLine.parseDocument();

        assertThat(firstLine.getInputText(), containsString("Written by John Doe and Randy Butternubs."));
        assertThat(firstLine.size(), is(8));

        Document titlePage = new Document("Written by John Doe and Randy Butternubs.");
        titlePage.parseDocument();

        ListIterator<Token> it = titlePage.iterator();
        String tokenizedSentence[] = { "Written", "by", "John", "Doe", "and", "Randy", "Butternubs", "." };

        int index = 0;
        while (it.hasNext()) {
            Token currentToken = it.next();
            assertTrue(currentToken.getValue().equals(tokenizedSentence[index]));
            index++;
        }

        Document multiLine = new Document("Testing with:\nLine\nBreaks.\n");
        titlePage.parseDocument();
        String tokenized2[] = { "Testing", "with", ":", "Line", "Breaks", "." };
        it = multiLine.iterator();

        index = 0;
        while (it.hasNext()) {
            Token currentToken = it.next();
            assertTrue(currentToken.getValue().equals(tokenized2[index]));
            index++;
        }

        Document weirdPunctuation = new Document("My drink? My \"Diet Dr. Kelp!\" Don't tell me you forgot my drink&!");
        weirdPunctuation.parseDocument();
        String tokenized3[] = { "My", "drink", "?", "My", "\"", "Diet", "Dr", ".", "Kelp", "!", "\"",
                "Don't", "tell", "me", "you", "forgot", "my", "drink", "&", "!" };
        it = weirdPunctuation.iterator();

        index = 0;
        while (it.hasNext()) {
            Token currentToken = it.next();
            assertTrue(currentToken.getValue().equals(tokenized3[index]));
            index++;
        }

    }

    @Test
    public void testToAdd() throws IOException {
        Document veryImportant = new Document("It's really not that important.");
        veryImportant.parseDocument();
        String tokenized[] = { "It's", "really", "not", "NOT", "that", "important", "." };

        ListIterator<Token> it = veryImportant.iterator();

        for (int i = 0; i < 3; i++)
            it.next();

        veryImportant.addToken(it, new Token("NOT"));

        for (int i = 0; i < 4; i++)
            it.previous();

        int index = 0;
        while (it.hasNext()) {
            Token currentToken = it.next();
            assertTrue(currentToken.getValue().equals(tokenized[index]));
            index++;
        }
    }
}

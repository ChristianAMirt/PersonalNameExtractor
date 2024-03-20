package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Iterator;

public class TestDocument {

    @Test
    public void testConstructors() {
        Document emptyDoc = new Document("");

        assertThat(emptyDoc.getInputText(), is(""));
        assertThat(emptyDoc.size(), is(0));

        Document titlePage = new Document("Written by John Doe and Randy Butternubs.");

        assertThat(titlePage.getInputText(), containsString("Written by John Doe and Randy Butternubs."));
        assertThat(titlePage.size(), is(8));
    }

    @Test
    public void testParsing() {
        Document titlePage = new Document("Written by John Doe and Randy Butternubs.");

        Iterator<Token> it = titlePage.iterator();
        String tokenizedSentence[] = { "Written", "by", "John", "Doe", "and", "Randy", "Butternubs", "." };

        int index = 0;
        while (it.hasNext()) {
            Token currentToken = it.next();
            assertThat(currentToken.getValue(), containsString(tokenizedSentence[index]));
            index++;
        }

        Document multiLine = new Document("Testing with:\nLine\nBreaks.\n");
        String tokenized2[] = { "Testing", "with", ":", "Line", "Breaks", "." };
        it = multiLine.iterator();

        index = 0;
        while (it.hasNext()) {
            Token currentToken = it.next();
            assertThat(currentToken.getValue(), containsString(tokenized2[index]));
            index++;
        }

        Document weirdPunctuation = new Document("My drink? My \"Diet Dr. Kelp!\" Don't tell me you forgot my drink&!");
        String tokenized3[] = { "My", "drink", "?", "My", "\"", "Diet", "Dr", ".", "Kelp", "!", "\"",
                "Don't", "tell", "me", "you", "forgot", "my", "drink", "&", "!" };
        it = weirdPunctuation.iterator();

        index = 0;
        while (it.hasNext()) {
            Token currentToken = it.next();
            assertThat(currentToken.getValue(), containsString(tokenized3[index]));
            index++;
        }

    }
}

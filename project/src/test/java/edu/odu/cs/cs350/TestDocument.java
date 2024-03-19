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
        assertThat(titlePage.size(), is(7)); // This really should be 8 once punctuation gets its own token
        
        Iterator<Token> it = titlePage.iterator();
        Token currentToken = it.next();
        assertThat(currentToken.getValue(), containsString("Written"));
    }
}

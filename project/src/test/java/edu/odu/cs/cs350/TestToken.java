package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestToken {

    @Test
    public void testConstructors() {
        Token emptyToken = new Token("");

        assertThat(emptyToken.getValue(), is(""));
        assertThat(emptyToken.getClassification(), is(-1));

        Token firstWordOfSentence = new Token("Although");

        assertThat(firstWordOfSentence.getValue(), is("Although"));
        assertThat(firstWordOfSentence.getClassification(), is(-1));
    }
}

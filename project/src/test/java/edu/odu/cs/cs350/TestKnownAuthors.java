package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeAll;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

/**
 * @author Rahul Malik and Brendan Hearrell
 *         Tests for KnownAuthors.java
 */

public class TestKnownAuthors {

    /**
     * KnownAuthors object to be used within each test.
     */
    private KnownAuthors knownAuthors;

    /**
     * Initialize KnownAuthors constructor to be utilized by all tests. 
     * @throws IOException
     */
    @BeforeAll
    public void initializeKnownAuthors() throws IOException{
        knownAuthors = new KnownAuthors();
    }

    /**
     * Test method for the KnownAuthors constructor
     */
    @Test
    public void testKnownAuthorsConstructor() throws IOException, FileNotFoundException {
        assertNotNull(knownAuthors, "KnownAuthors object should not be null");
    }

    /**
     * Test to see that token value is accepted and verified
     * as true or false dependent on the string value being
     * located within Dictionary.firstNames.txt
     */
    @Test
    public void testFirstName()
            throws IOException {
        Token token = new Token("brendan");
        assertEquals(true, knownAuthors.firstName(token.getValue()));

        Token tokenTwo = new Token("SyntaxSages");
        assertNotEquals(true, knownAuthors.firstName(tokenTwo.getValue()));
    }

    /**
     * Test to see that token value is accepted and verified
     * as true or false dependent on the string value being
     * located within Dictionary.lastNames.txt
     */
    @Test
    public void testLastName()
            throws IOException {
        Token token = new Token("heath-pastore");
        assertEquals(true, knownAuthors.lastName(token.getValue()));

        Token tokenTwo = new Token("SyntaxSages");
        assertNotEquals(true, knownAuthors.lastName(tokenTwo.getValue()));
    }
}

package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

/**
 * @author Rahul Malik 
 *  Tests for KnownAuthors.java
 */


public class TestKnownAuthors {

    /**
     * Test method for the KnownAuthors constructor
     */
    @Test
    public void testKnownAuthorsConstructor(){
        KnownAuthors testKnownAuthors = new KnownAuthors();
        assertNotNull(testKnownAuthors, "KnownAuthors object should not be null");
    }
    /**
     * Test method to ensure first name data file can be read
     */
    @Test
    public void testReadFirstNameToArray() {
        KnownAuthors knownAuthors = new KnownAuthors();
        try {
            String[] firstNameArray = knownAuthors.readFirstNameToArray();
            assertNotNull(firstNameArray, "First name array should not be null");
            assertTrue(firstNameArray.length > 0, "At least one first name should be read");
        } catch (IOException e) {
            fail("IOException occurred while reading first names file: " + e.getMessage());
        }
    }

    /**
     * Test method to ensure last name data file can be read
     */
    @Test
    public void testReadLastNameToArray() {
        KnownAuthors knownAuthors = new KnownAuthors(); 
        try {
            String[] lastNameArray = knownAuthors.readLastNameToArray();
            assertNotNull(lastNameArray, "Last name array should not be null");
            assertTrue(lastNameArray.length > 0, "At least one last name should be read");
        } catch (IOException e) {
            fail("IOException occurred while reading last names file: " + e.getMessage());
        }
    }

    /**
     * Test to see that token value is accepted and verified 
     * as true or false dependent on the string value being
     * located within Dictionary.firstNames.txt
     */
    @Test
    public void testFirstName() 
        throws IOException
    {
        KnownAuthors knownAuthors = new KnownAuthors();
        Token token = new Token("brendan");
        assertEquals(knownAuthors.firstName(token.getValue()) , true);
        
        KnownAuthors knownAuthorsTwo = new KnownAuthors();
        Token tokenTwo = new Token("SyntaxSages");
        assertNotEquals(knownAuthorsTwo.firstName(tokenTwo.getValue()), true);
    }

    /**
     * Test to see that token value is accepted and verified 
     * as true or false dependent on the string value being
     * located within Dictionary.lastNames.txt
     */
    @Test
    public void testLastName() 
        throws IOException
    {
        KnownAuthors knownAuthors = new KnownAuthors();
        Token token = new Token("hearrell");
        assertEquals(knownAuthors.lastName(token.getValue()) , true);
        
        KnownAuthors knownAuthorsTwo = new KnownAuthors();
        Token tokenTwo = new Token("SyntaxSages");
        assertNotEquals(knownAuthorsTwo.lastName(tokenTwo.getValue()), true);
    }
}

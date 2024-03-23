package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
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

        assertNotNull(testKnownAuthors);
        
    }
    
}

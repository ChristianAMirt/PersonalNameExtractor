package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeAll;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)


public class TestHonorifics {

    /**
     * Honorifics object to be used within each test. 
     */
    private Honorifics honorificsFeature;

    /**
     * Initialize Honorifics constructor to be utilized by all tests.
     * @throws IOException
     */
    @BeforeAll
    public void initializeHonorifics() throws IOException{
        honorificsFeature = new Honorifics();
    }


    /**
     * Test to make sure Honorifics constructor
     * is properly creating new instance of Honorifics
     * @throws IOException 
     */
    @Test
    public void testHonorificsConstructor() throws IOException{
        assertNotNull(honorificsFeature,"Honorifics object should not be null");
    }

    /**
     * Tests that both reference files are both valid
     * and that function returns open file
     * @throws IOException 
     */
    @Test
    public void testOpenFile()
        throws IOException
    {
        String inputFileOne = "src/test/data/Dictionary.honorifics.txt";
        assertNotNull(honorificsFeature.openFile(inputFileOne));
    }

    /**
     * Test to see that token value is accepted and verified 
     * as true or false dependent on the string value being
     * located within Dictionary.honorifics.txt
     */
    @Test
    public void testContainsHonorifics() 
        throws IOException
    {
        Token token = new Token("Mr");
        assertEquals(true, honorificsFeature.containsHonorifics(token.getValue()));
        
        Token tokenTwo = new Token("SyntaxSages");
        assertNotEquals(true, honorificsFeature.containsHonorifics(tokenTwo.getValue()));
    }
}

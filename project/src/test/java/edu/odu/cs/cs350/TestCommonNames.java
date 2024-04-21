package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeAll;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)


public class TestCommonNames {

    /**
     * CommonNames object to be used within each test. 
     */
    private CommonNames commonNames;

    /**
     * Initialize CommonNames constructor to be utilized by all tests.
     * @throws IOException 
     */
    @BeforeAll
    public void initializeCommonNames() throws IOException{
        commonNames = new CommonNames();
    }
    
    /**
     * Test to make sure CommonNames constructor
     * is properly creating new instance of CommonNames.
     * @throws IOException 
     */
    @Test
    public void testCommonNamesConstructor() throws IOException{
        assertNotNull(commonNames, "CommonNames object should not be null");
    }

    /**
     * Tests that both reference files are both valid
     * and that function returns open file.
     * @throws IOException 
     */
    @Test
    public void testOpenFile()
        throws IOException
    {
        String inputFileOne = "src/test/data/CommonFirstNames.txt";
        assertNotNull(commonNames.openFile(inputFileOne));

        String inputFileTwo = "src/test/data/CommonLastNames.txt";
        assertNotNull(commonNames.openFile(inputFileTwo));
    }

    /**
     * Test to see that token value is accepted and verified 
     * as true or false dependent on the string value being
     * located within CommonFirstNames.txt.
     */
    @Test
    public void testCommonFirstName() 
        throws IOException
    {
        Token token = new Token("brendan");
        assertEquals(true, commonNames.commonFirstName(token.getValue()));
        
        Token tokenTwo = new Token("SyntaxSages");
        assertNotEquals(true, commonNames.commonFirstName(tokenTwo.getValue()));
    }

    /**
     * Test to see that token value is accepted and verified
     * as true or false dependent on the string value being 
     * located within CommonLastNames.txt.
     */
    @Test
    public void testCommonLastName() 
        throws IOException
    {
        Token token = new Token("smith");
        assertEquals(true, commonNames.commonLastName(token.getValue()));
        
        Token tokenTwo = new Token("hearrell");
        assertNotEquals(true, commonNames.commonLastName(tokenTwo.getValue()));
    }
    
}

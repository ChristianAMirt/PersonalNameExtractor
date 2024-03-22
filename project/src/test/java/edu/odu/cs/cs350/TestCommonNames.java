package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.IOException;


public class TestCommonNames {
    
    /**
     * Test to make sure CommonNames constructor
     * is properly creating new instance of CommonNames
     */
    @Test
    public void testCommonNamesConstructor(){
        CommonNames commonName = new CommonNames();
        assertNotNull(commonName);
    }

    /**
     * Tests that both reference files are both valid
     * and that function returns open file
     */
    @Test
    public void testOpenFile()
        throws FileNotFoundException
    {
        CommonNames testInput = new CommonNames();

        String inputFileOne = "src/test/data/CommonFirstNames.txt";
        assertNotNull(testInput.openFile(inputFileOne));


        String inputFileTwo = "src/test/data/CommonLastNames.txt";
        assertNotNull(testInput.openFile(inputFileTwo));
    }

    /**
     * Test to see that token value is accepted and verified 
     * as true or false dependent on the string value being
     * located within CommonFirstNames.txt
     */
    @Test
    public void testCommonFirstName() 
        throws IOException
    {
        CommonNames commonFirstName = new CommonNames();
        Token token = new Token("brendan");
        assertEquals(commonFirstName.commonFirstName(token.getValue()) , true);
        
        CommonNames commonFirstNameTwo = new CommonNames();
        Token tokenTwo = new Token("SyntaxSages");
        assertNotEquals(commonFirstNameTwo.commonFirstName(tokenTwo.getValue()), true);
    }

    /**
     * Test to see that token value is accepted and verified
     * as true or false dependent on the string value being 
     * located within CommonLastNames.txt
     */
    @Test
    public void testCommonLastName() 
        throws IOException
    {
        CommonNames commonLastName = new CommonNames();
        Token token = new Token("smith");
        assertEquals(commonLastName.commonLastName(token.getValue()) , true);
        
        CommonNames commonLastNameTwo = new CommonNames(); 
        Token tokenTwo = new Token("hearrell");
        assertNotEquals(commonLastNameTwo.commonLastName(tokenTwo.getValue()), true);
    }
    
}

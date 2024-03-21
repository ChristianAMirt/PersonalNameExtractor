package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;


public class TestCommonNames {
    
    public void testCommonNamesConstructor(){
        CommonNames commonName = new CommonNames();
        assertNotNull(commonName);
    }

    public void testCommonFirstName() 
        throws IOException
    {
        CommonNames commonFirstName = new CommonNames();
        Token token = new Token("brendan");
        assertEquals(commonFirstName.commonFirstName(token) , true);
        
        CommonNames commonFirstNameTwo = new CommonNames();
        Token tokenTwo = new Token("SyntaxSages");
        assertNotEquals(commonFirstNameTwo.commonFirstName(tokenTwo), true);
    }

    public void testCommonLastName() 
        throws IOException
    {
        CommonNames commonLastName = new CommonNames();
        Token token = new Token("smith");
        assertEquals(commonLastName.commonLastName(token) , true);
        
        CommonNames commonLastNameTwo = new CommonNames();
        Token tokenTwo = new Token("hearrell");
        assertNotEquals(commonLastNameTwo.commonLastName(tokenTwo), true);
    }
    
}

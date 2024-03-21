package edu.odu.cs.cs350;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestDictionaryFeature {
    
    @Test
    public void testDetermineDictionaryFeature() throws FileNotFoundException, IOException
    {
        DictionaryFeature testForDictionaryFeature = new DictionaryFeature();
        Token firstToken = new Token("syntax");
        
        assertEquals(testForDictionaryFeature.determineDictionaryFeature(firstToken) , true);
    }
}

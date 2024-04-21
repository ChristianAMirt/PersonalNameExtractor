package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeAll;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestLexicalFeature {
    
    /**
     * LexicalFeature object that will be used for testing.
     */
    private LexicalFeature myLexicalFeature;

    /**
     * Calls the constructor of LexicalFeature only
     * once. This allows for the same LexicalFeature
     * object to be used for every test here.
     */
    @BeforeAll
    public void callConstructor(){
        myLexicalFeature = new LexicalFeature();
    }

    /**
     * Tests the constructor for LexicalFeature, making sure that
     * the instantiation of the class of as an object actually happens.
     */
    @Test
    public void testLexicalFeatureConstructor(){
        assertNotNull(myLexicalFeature);
    }

    /**
     * Tests the determineLexicalFeature method for the accuracy
     * of the returned String values 
     */
    @Test
    public void testDetermineLexicalFeature(){
        assertEquals("number", myLexicalFeature.determineLexicalFeature("27"));
        assertEquals("punct", myLexicalFeature.determineLexicalFeature("~"));
        assertEquals("other", myLexicalFeature.determineLexicalFeature("katana"));
    }

    /**
     * Tests the checkNumber method for the accuracy
     * of the returned boolean values 
     */
    @Test
    public void testCheckNumber(){
        assertEquals(true, myLexicalFeature.checkNumber("420"));
        assertEquals(false, myLexicalFeature.checkNumber("four"));
    }

    /**
     * Tests the checkPunctuation method for the accuracy
     * of the returned boolean values
     */
    @Test
    public void testCheckPunctuation(){
        assertEquals(true, myLexicalFeature.checkPunctuation("!"));
        assertEquals(false, myLexicalFeature.checkPunctuation("exclamation"));
    }
    /**
     * Tests the checkSingleCapital method for the accuracy
     * of the returned boolean values
     */
    @Test
    public void testCheckSingleCapital(){
        assertEquals(true, myLexicalFeature.checkSingleCapital("L"));
        assertEquals(false, myLexicalFeature.checkSingleCapital("Lawlite"));
    }

    /**
     * Tests the checkCapitalWord method for the accuracy
     * of the returned boolean values
     */
    @Test
    public void testCheckCapitalWord(){
        assertEquals(true, myLexicalFeature.checkCapitalWord("Velvet"));
        assertEquals(false, myLexicalFeature.checkCapitalWord("velvet"));
    }

    /**
     * Tests the checkAllCapital method for the accuracy
     * of the returned boolean values
     */
    @Test
    public void testCheckAllCapital(){
        assertEquals(true, myLexicalFeature.checkAllCapital("YELLOW"));
        assertEquals(false, myLexicalFeature.checkAllCapital("YeLLoW"));
    }
}

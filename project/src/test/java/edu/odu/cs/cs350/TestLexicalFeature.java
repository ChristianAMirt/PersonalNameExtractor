package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestLexicalFeature {
    
    /**
     * Tests the constructor for LexicalFeature, making sure that
     * the instantiation of the class of as an object actually happens.
     */
    @Test
    public void testLexicalFeatureConstructor(){
        LexicalFeature firsLexicalFeature = new LexicalFeature();
        assertNotNull(firsLexicalFeature);
    }

    /**
     * Tests the determineLexicalFeature method for the accuracy
     * of the returned String values 
     */
    @Test
    public void testDetermineLexicalFeature(){
        LexicalFeature secondLexicalFeature = new LexicalFeature();
        assertEquals("number", secondLexicalFeature.determineLexicalFeature("27"));
        assertEquals("punct", secondLexicalFeature.determineLexicalFeature("~"));
        assertEquals("other", secondLexicalFeature.determineLexicalFeature("katana"));
    }

    /**
     * Tests the checkNumber method for the accuracy
     * of the returned boolean values 
     */
    @Test
    public void testCheckNumber(){
        LexicalFeature thrirdLexicalFeature = new LexicalFeature();
        assertEquals(true, thrirdLexicalFeature.checkNumber("420"));
        assertEquals(false, thrirdLexicalFeature.checkNumber("four"));
    }

    /**
     * Tests the checkPunctuation method for the accuracy
     * of the returned boolean values
     */
    @Test
    public void testCheckPunctuation(){
        LexicalFeature fourthLexicalFeature = new LexicalFeature();
        assertEquals(true, fourthLexicalFeature.checkPunctuation("!"));
        assertEquals(false, fourthLexicalFeature.checkPunctuation("exclamation"));
    }
    /**
     * Tests the checkSingleCapital method for the accuracy
     * of the returned boolean values
     */
    @Test
    public void testCheckSingleCapital(){
        LexicalFeature fifthLexicalFeature = new LexicalFeature();
        assertEquals(true, fifthLexicalFeature.checkSingleCapital("L"));
        assertEquals(false, fifthLexicalFeature.checkSingleCapital("Lawlite"));
    }

    /**
     * Tests the checkCapitalWord method for the accuracy
     * of the returned boolean values
     */
    @Test
    public void testCheckCapitalWord(){
        LexicalFeature sixthLexicalFeature = new LexicalFeature();
        assertEquals(true, sixthLexicalFeature.checkCapitalWord("Velvet"));
        assertEquals(false, sixthLexicalFeature.checkCapitalWord("velvet"));
    }

    /**
     * Tests the checkAllCapital method for the accuracy
     * of the returned boolean values
     */
    @Test
    public void testCheckAllCapital(){
        LexicalFeature seventhLexicalFeature = new LexicalFeature();
        assertEquals(true, seventhLexicalFeature.checkAllCapital("YELLOW"));
        assertEquals(false, seventhLexicalFeature.checkAllCapital("YeLLoW"));
    }
}

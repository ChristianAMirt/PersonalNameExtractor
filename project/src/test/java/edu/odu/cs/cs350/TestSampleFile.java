package edu.odu.cs.cs350;


import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;




public class TestSampleFile {
    
    @Test
    public void testStringComparison()
    {
        SampleFile mySample = new SampleFile();
        assertEquals(mySample.stringComparison("Syntax", "Sages"), false);
        assertEquals(mySample.stringComparison("Syntax", "Syntax"), true);
    }
}

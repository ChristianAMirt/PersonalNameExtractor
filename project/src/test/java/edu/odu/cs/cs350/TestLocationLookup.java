package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLocationLookup {

    private LocationLookup location;

    @BeforeEach
    void setUp() throws FileNotFoundException, IOException{
        location = new LocationLookup();
    }
    

    @Test
    void TestReadInFile() {
        assertTrue(location.size() > 0);
    }

    @Test
    void TestCheckLocation() {
        String toFind = "New York";
        assertTrue(location.checkLocation(toFind));
        toFind = "new";
        assertFalse(location.checkLocation(toFind));
    }
}
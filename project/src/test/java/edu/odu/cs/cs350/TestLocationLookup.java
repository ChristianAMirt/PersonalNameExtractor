package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLocationLookup {

    @Test
    void TestReadInFile() throws FileNotFoundException, IOException {
        String filename = "Locations.txt";
        LocationLookup location = new LocationLookup(filename);
        assertTrue(location.size() > 0);
    }
}
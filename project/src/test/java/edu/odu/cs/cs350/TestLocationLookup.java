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
        String filename = "/home/cs_aakin007/cs350/cs350-project-s24-ChristianAMirt/project/src/test/data/Locations.txt";
        LocationLookup location = new LocationLookup(filename);
        assertTrue(location.size() > 0);
    }
}
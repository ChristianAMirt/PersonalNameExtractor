package edu.odu.cs.cs350;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGeographicalFeatures {

    @Test
    void TestReadInFile() {
        GeographicalFeatures features;
        features.readInData();
        assertTrue(features.dataMap.size() > 0);
        assertEquals()
    }
}
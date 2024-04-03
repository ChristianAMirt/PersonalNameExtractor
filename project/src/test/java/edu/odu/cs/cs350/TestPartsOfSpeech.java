package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestPartsOfSpeech {

    @Test
    public void testConstructor() {
        assertDoesNotThrow(() -> new PartsOfSpeech());
    }

    @Test
    public void testIsPartOfSpeach() {
        PartsOfSpeech checker = new PartsOfSpeech();

        // assertThat(checker.testIsPartOfSpeach("A"), is(true));
    }
}

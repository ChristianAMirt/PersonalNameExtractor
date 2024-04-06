package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestPartsOfSpeech {

    private PartsOfSpeech checker;

    @BeforeEach
    public void setUp() throws FileNotFoundException, IOException {
        checker = new PartsOfSpeech();
    }

    @Test
    public void testConstructor() throws FileNotFoundException, IOException {
        assertDoesNotThrow(() -> new PartsOfSpeech());
    }

    @Test
    public void testCheckForPartsOfSpeech() {
        assertEquals("other", checker.checkForPartsOfSpeech(""));
        assertEquals("other", checker.checkForPartsOfSpeech("any"));
        assertEquals("other", checker.checkForPartsOfSpeech("plumbus"));
        assertEquals("period", checker.checkForPartsOfSpeech("."));
        assertEquals("comma", checker.checkForPartsOfSpeech(","));
        assertEquals("hyphen", checker.checkForPartsOfSpeech("-"));
        assertEquals("conjunction", checker.checkForPartsOfSpeech("or"));
        assertEquals("article", checker.checkForPartsOfSpeech("an"));
    }

    @Test
    void testIsConjunction() {
        assertThat(checker.isConjunction(""), is(false));
        assertThat(checker.isConjunction("and"), is(true));
        assertThat(checker.isConjunction("If"), is(true));
        assertThat(checker.isConjunction("1"), is(false));
        assertThat(checker.isConjunction("Andy"), is(false));
        assertThat(checker.isConjunction("although"), is(true));
    }

    @Test
    public void testIsArticle() {
        assertThat(checker.isArticle(""), is(false));
        assertThat(checker.isArticle("A"), is(true));
        assertThat(checker.isArticle("aN"), is(true));
        assertThat(checker.isArticle("the"), is(true));
        assertThat(checker.isArticle("Any"), is(false));
        assertThat(checker.isArticle("TH"), is(false));
    }

    @Test
    public void testPunctuation() {
        assertThat(checker.isPeriod(""), is(false));
        assertThat(checker.isPeriod("."), is(true));
        assertThat(checker.isComma(""), is(false));
        assertThat(checker.isComma(","), is(true));
        assertThat(checker.isHyphen(""), is(false));
        assertThat(checker.isHyphen("-"), is(true));
        assertThat(checker.isPeriod(","), is(false));
        assertThat(checker.isComma("-"), is(false));
        assertThat(checker.isHyphen("."), is(false));
    }

}

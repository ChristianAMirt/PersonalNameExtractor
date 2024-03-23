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

public class TestLibrarian {
    @Test
    public void testConstructor() throws IOException {
        Librarian librarian = new Librarian("");

        assertThat(librarian.hasDocuments(), is(false));

        Librarian librarian2 = new Librarian("Is this the Krusty Krab? No this is Patrick.");

        assertThat(librarian2.hasDocuments(), is(true));
    }

    @Test
    public void testCharacterLimit() throws IOException {

        String filename = "src/test/data/bigString.txt";
        String bigString = Files.readString(Paths.get(filename));

        assertDoesNotThrow(() -> new Librarian("small String"));
        assertThrows(IOException.class, () -> new Librarian(bigString));
    }

    @Test
    public void testGetTextBetweenNERTags() throws IOException {

        // Variable for empty text test.
        String emptyInputText = "";

        // Variable for sample input text test.
        String sampleInputText = "ABCD EFGH <NER> IJKL MNOP </NER> QRST UVWX YZ";

        // Variable for sample output text test.
        String expectedOutputText = " IJKL MNOP ";

        Librarian librarian = new Librarian(sampleInputText);
        assertEquals(expectedOutputText, librarian.getTextBetweenNERTags(sampleInputText));

    }

    @Test
    public void testGetTagIndex() throws IOException {

        // Variable for sample input text test.
        String sampleInputText = "ABCD EFGH <NER> IJKL MNOP </NER> QRST UVWX YZ";

        Librarian librarian = new Librarian(sampleInputText);
        assertEquals(11, librarian.getTagIndex(sampleInputText, "NER"));

    }

    @Test
    public void testmarkNames() throws IOException {
        Librarian withNames = new Librarian("<NER> A very easy name is Steve Smith. </NER>");
        Librarian noNames = new Librarian("<NER> This is just text with no names. </NER>");

        withNames.markNames();
        noNames.markNames();

        assertEquals(withNames.toString(), "<NER> A very easy name is <PER> Steve Smith </PER> . </NER> ");
        assertEquals(noNames, "<NER> This is just text with no names. </NER>");
    }
}

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

        Librarian librarian3 = new Librarian("<NER>ABCD EFGH </NER><NER> IJKL MNOP </NER><NER> QRST UVWX YZ</NER>");
        String[] outputExpected = { "<NER>ABCD EFGH </NER>", "<NER> IJKL MNOP </NER>", "<NER> QRST UVWX YZ</NER>" };
        int vectorSizeExpected = 3;

        int vectorSizeActual = librarian3.getDocumentsSize();
        assertThat(librarian3.hasDocuments(), is(true));
        assertEquals(vectorSizeExpected, vectorSizeActual);

        String documentText;
        for (int i = 0; i < vectorSizeActual; i++) {
            documentText = librarian3.getDocumentAt(i).getInputText();
            assertEquals(outputExpected[i], documentText);
        }
    }

    @Test
    public void testCharacterLimit() throws IOException {

        String filename = "src/test/data/bigString.txt";
        String bigString = Files.readString(Paths.get(filename));

        assertDoesNotThrow(() -> new Librarian("small String"));
        assertThrows(IOException.class, () -> new Librarian(bigString));
    }

    @Test
    public void testSplitInputPage() throws IOException {

        Librarian librarian3 = new Librarian("<NER>ABCD EFGH </NER><NER> IJKL MNOP </NER><NER> QRST UVWX YZ</NER>");

        String[] outputExpected = { "<NER>ABCD EFGH </NER>", "<NER> IJKL MNOP </NER>", "<NER> QRST UVWX YZ</NER>" };
        int arraySizeExpected = 3;

        String[] outputActual = librarian3
                .splitInputPage("<NER>ABCD EFGH </NER><NER> IJKL MNOP </NER><NER> QRST UVWX YZ</NER>");
        int arraySizeActual = outputActual.length;

        assertEquals(arraySizeExpected, arraySizeActual);
        assertEquals(outputExpected[0], outputActual[0]);
        assertEquals(outputExpected[1], outputActual[1]);
        assertEquals(outputExpected[2], outputActual[2]);
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
        Librarian noNames = new Librarian("<NER> This is text without names. </NER>");

        String output1 = withNames.markNames();
        String output2 = noNames.markNames();

        assertThat(output1.toString(),
                containsString("<NER> A very easy name is <PER> Steve Smith </PER> . </NER> "));
        assertThat(output2.toString(), containsString("<NER> This is text without names . </NER> "));
    }

    @Test
    public void testPrintDocumentText() throws IOException {

        String outputText = "<NER>ABCD EFGH </NER>";

        Librarian librarian = new Librarian(outputText);
        librarian.printDocumentText(outputText);
    }

}

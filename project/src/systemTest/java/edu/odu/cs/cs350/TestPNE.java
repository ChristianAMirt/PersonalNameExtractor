package edu.odu.cs.cs350;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;public class TestPNE {

    ByteArrayOutputStream outputStream; // will contain captured output from System.out
    PrintStream saved; // used to restore System.out

    @BeforeEach
    void setUp() {
        // Redirect System.out to outputString;
        outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        saved = System.out;
        System.setOut(ps);
    }

    @AfterEach
    void cleanUp() {
        System.setOut(saved);
    }

    public String fileToString(String filepath) {
        Scanner scanner = new Scanner(filepath);
        return scanner.toString();
    }

    @Test
    public void testTokenProcessing() {
        String input = fileToString("project/src/systest/data/test000/test.dat");
        String[] args = { input };
        String expected = fileToString("project/src/systest/data/test000/test.expected");

        PNE.main(args);
        String output = outputStream.toString();

        assertEquals(expected, output);
    }

    @Test
    public void testPageProcessing() {
        String input = fileToString("project/src/systest/data/test001/test.dat");
        String[] args = { input };
        String expected = fileToString("project/src/systest/data/test001/test.expected");

        PNE.main(args);
        String output = outputStream.toString();

        assertTrue(output.contains(output));
    }

    @Test
    public void testLexicalFeatures() throws IOException {
        Path inputPath = Path.of("src/systemTest/data/test002/test.dat");
        String input = Files.readString(inputPath);

        Path expectedPath = Path.of("src/systemTest/data/test002/test.expected");
        String expected = Files.readString(expectedPath);

        String[] textBlock = new String[] { input };

        PNE.main(textBlock);

        BufferedWriter output = new BufferedWriter(new FileWriter("src/systemTest/data/test002/test.out"));
        output.append(outputStream.toString());
        output.close();

        assertEquals(expected, outputStream.toString());
    }
    
    @Test
    public void testDictionaryFeature() throws IOException{
        Path inputPath = Path.of("src/systemTest/data/test003/test.dat");
        String input = Files.readString(inputPath);

        Path expectedPath = Path.of("src/systemTest/data/test003/test.expected");
        String expected = Files.readString(expectedPath);

        String[] textBlock = new String[] {input};

        PNE.main(textBlock);

        BufferedWriter output = new BufferedWriter(new FileWriter("src/systemTest/data/test003/test.out"));
        output.append(outputStream.toString());
        output.close();

        assertEquals(expected, outputStream.toString());
    }
    
    @Test
    public void testLocationLookup() throws IOException {
        final String pathDat = "src/systemTest/data/test005/test.dat";
        final String pathOut = "src/systemTest/data/test005/test.out";
        final String pathExpected = "src/systemTest/data/test005/test.expected";

        Path inputPath = Path.of(pathDat);
        String input = Files.readString(inputPath);

        Path expectedPath = Path.of(pathExpected);
        String expected = Files.readString(expectedPath);
        
        String[] textBlock = new String[] { input };

        PNE.main(textBlock);

        BufferedWriter output = new BufferedWriter(new FileWriter(pathOut));
        output.append(outputStream.toString());
        output.close();

        assertEquals(expected, outputStream.toString());
    }
}
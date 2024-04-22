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

public class TestPNE {

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
}
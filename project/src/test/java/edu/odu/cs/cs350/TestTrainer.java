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

public class TestTrainer {
    @Test
    public void testConstructor() throws FileNotFoundException, IOException {
        String firstDataString = "F, F, F, F, F, F, F, F, F, F, other, other, F, F, F, F, F, F, F, F, F, F, other, other, F, F, F, F, F, F, F, F, F, F, other, punct, F, F, F, F, F, F, F, F, F, F, hyphen, punct, F, F, F, F, F, F, F, F, F, F, hyphen, punct, 0";
        Trainer trainer = new Trainer();

        assertTrue(firstDataString.equals(trainer.getDataStringAt(0)));
        FileReader arffFile = new FileReader("src/main/data/trainingARFF.arff");
        assertTrue(arffFile.read() != -1);
    }
}

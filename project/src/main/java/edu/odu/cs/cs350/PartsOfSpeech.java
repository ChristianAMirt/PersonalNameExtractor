package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class PartsOfSpeech {

    private final String STOPLIST_FILE_PATH = "src/main/data/Stoplist.english.txt";

    private HashSet<String> commonSpeach;

    /*
     * Create a new PartsOfSpeech object
     */
    public PartsOfSpeech() throws FileNotFoundException, IOException {
        this.commonSpeach = new HashSet<String>();
        loadData(STOPLIST_FILE_PATH);
    }

    private void loadData(String filePath) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        while (reader.ready()) {
            commonSpeach.add(reader.readLine());
        }
        reader.close();
    }
}

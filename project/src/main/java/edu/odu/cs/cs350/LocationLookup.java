package edu.odu.cs.cs350;

import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The LocationLookup class loads and stores location, Environent, City, State, and geographical feature names from a file,
 * providing functionality to check if a specific location is included.
 * 
 * The locations are stored in a case-insensitive manner, allowing for easy lookup.
 * Any duplicate locations or names will not be stored.
 * 
 * The class is help together using a "Set" Container Datatype. This will store all of the data
 * for quick and easy look up access.
 */
public class LocationLookup {

    /**
     * Holds the unique set of location names loaded from the file.
     */
    private Set<String> locations;

    /**
     * File for locations
     */
    private final String file1 = "src/main/data/Dictionary.location1.txt";

    /**
     * File for locations
     */
    private final String file2 = "src/main/data/Dictionary.location2.txt";

    /**
     * File for locations
     */
    private final String file3 = "src/main/data/Dictionary.location3.txt";

    /**
     * Stores the files for locations
     */
    private final String[] fileList = { file1, file2, file3 };

    /**
     * Constructs a new LocationLookup instance by loading location names from the specified file.
     *
     * @throws FileNotFoundException if the specified file does not exist.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    public LocationLookup() throws FileNotFoundException, IOException {
        this.locations = new HashSet<>();
        for (var file : fileList) {
            loadLocations(file);
        }
    }

    /**
     * Loads location names from a specified file and stores them in a Set. Each location name is trimmed and converted to lowercase before storage to ensure case-insensitive matching.
     *
     * @param fileName The path to the file from which to load location names.
     * @throws FileNotFoundException if the file cannot be found.
     * @throws IOException if an I/O error occurs during reading from the file.
     */
    private void loadLocations(String fileName) throws FileNotFoundException, IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                locations.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            // Re-throw the exception to allow the caller to handle it.
            throw e; 
        }
    }

    /**
     * Checks if the specified location is present in the set of locations loaded from the file.
     *
     * @param thisLocation The location name to check, ignoring case.
     * @return true if the location is present, false otherwise.
     */
    public boolean checkLocation(String thisLocation) {
        return this.locations.contains(thisLocation.trim().toLowerCase());
    }

    /**
     * Returns the number of unique locations loaded from the file.
     *
     * @return The size of the locations set.
     */
    public int size() {
        return this.locations.size();
    }
}
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
    
    // Holds the unique set of location names loaded from the file.
    private Set<String> locations;

    /**
     * Constructs a new LocationLookup instance by loading location names from the specified file.
     *
     * @param filename The path to the file containing location names, with one name per line.
     * @throws FileNotFoundException if the specified file does not exist.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    public LocationLookup(String filename) throws FileNotFoundException, IOException {
        this.locations = new HashSet<>();
        loadLocations(filename);
    }

    /**
     * Loads location names from a specified file and stores them in a Set. Each location name is trimmed and converted to lowercase before storage to ensure case-insensitive matching.
     *
     * @param filename The path to the file from which to load location names.
     * @throws FileNotFoundException if the file cannot be found.
     * @throws IOException if an I/O error occurs during reading from the file.
     */
    private void loadLocations(String filename) throws FileNotFoundException, IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            System.out.println("File opened");
            String line;
            while ((line = reader.readLine()) != null) {
                locations.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.out.println("Error: Could Not Open File");
            // Re-throw the exception to allow the caller to handle it.
            throw e; 
        }
    }

    /**
     * Checks if the specified location is present in the set of locations loaded from the file.
     *
     * @param loc The location name to check, ignoring case.
     * @return true if the location is present, false otherwise.
     */
    public boolean checkLocation(String loc) {
        return this.locations.contains(loc.trim().toLowerCase());
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
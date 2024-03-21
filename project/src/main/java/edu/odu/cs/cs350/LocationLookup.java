package edu.odu.cs.cs350;

import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * 
 * 
 * 
 * 
 * 
 */

public class LocationLookup {
    
    private Set<String> locations;

    public LocationLookup(String filename) throws FileNotFoundException, IOException {
        this.locations = new HashSet<>();
        loadLocations(filename);
    }

    private void loadLocations(String filename) throws FileNotFoundException, IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            System.out.println("File opened");
            String line;
            while ((line = reader.readLine()) != null) {
                locations.add(line.trim().toLowerCase());
            }
        }catch (IOException e){
            System.out.println("Error:Could Not Open File");
        }
    }

    public boolean checkLocation(String location) {
        return location.contains(location.trim().toLowerCase());
    }

    public int size() {
        return this.locations.size();
    }
}
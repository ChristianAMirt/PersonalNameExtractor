package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Object;
import java.util.regex.Pattern;

/**
 * Determines the lexical features of a token
 * @author Jaylen Wheeler
 */
public class lexicalFeature {
    /**
     * instantiation of this class as an object.
     */
    public lexicalFeature() {}

    /**
     * Checks to determine the lexical features of a token
     * through methods that return boolean values
     * 
     * @param myWord
     * @return String
     */
    public String determineLexicalFeature(String myWord) {



        return "false";
    }

    /**
     * Method to check if a token's value is a number
     * 
     * @param myWord
     * @return boolean
     */
    public boolean checkNumber(String myWord){

        // Checks every character in the string for a number
        for(int i = 0; i < myWord.length(); i++)
        {
            char characterIndex = myWord.charAt(i);
            if(Character.isDigit(characterIndex) == true){
                return true;
            }
        }

        return false;
    }

    /**
     * Method to check if a token's value is punctuation
     * 
     * Source: https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
     * Source: https://stackoverflow.com/a/49289766
     * 
     * @param myWord
     * @return
     */
    public boolean checkPunctuation(String myWord){

        // Immediately returns false if more than one character
        if(myWord.length() != 1){
            return false;
        }

        // compares the token's value to 641 punctuation characters
        if(Pattern.matches("[\\p{Punct}\\p{IsPunctuation}]", myWord) == true)
        {
            return true;
        }

        return false;
    }


}

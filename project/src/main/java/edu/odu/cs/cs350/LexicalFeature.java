package edu.odu.cs.cs350;

import java.util.regex.Pattern;

/**
 * Determines the lexical features of a token.
 * @author Jaylen Wheeler
 */
public class LexicalFeature {
    /**
     * instantiation of this class as an object.
     */
    public LexicalFeature() {}

    /**
     * Checks to determine the lexical features of a token
     * through methods that return boolean values.
     * 
     * @param myWord
     * @return String value.
     */
    public String determineLexicalFeature(String myWord) {

        if(checkNumber(myWord) == true){
            return "number";
        }

        if(checkPunctuation(myWord) == true){
            return "punct";
        }

        if(checkSingleCapital(myWord) == true){
            return "CapLetter";
        }

        if(checkCapitalWord(myWord) == true){
            return "capitalized";
        }

        if(checkAllCapital(myWord) == true){
            return "AllCaps";
        }
        return "other";
    }

    /**
     * Method to check if a token's value is a number.
     * 
     * @param myWord
     * @return boolean value.
     */
    public boolean checkNumber(String myWord){

        // Checks every character in the string for a number
        for(int i = 0; i < myWord.length(); i++)
        {
            char characterIndex = myWord.charAt(i);
            if(Character.isDigit(characterIndex) == false){
                return false;
            }
        }

        return true;
    }

    /**
     * Method to check if a token's value is punctuation.
     * 
     * Source: https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html.
     * Source: https://stackoverflow.com/a/49289766.
     * 
     * @param myWord
     * @return boolean value.
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

    /**
     * Method to check if a token's value is a single capitalized letter.
     * 
     * @param myWord
     * @return boolean value.
     */
    public boolean checkSingleCapital(String myWord){
        // Immediately returns false if more than one character
        if(myWord.length() != 1){
            return false;
        }

        // Checks if the character is uppercase
        char characterIndex = myWord.charAt(0);
        if(Character.isUpperCase(characterIndex)){
            return true;
        }

        return false;
    }

    /**
     * Method to check if a token's value is a word
     * beginning with a capital letter.
     * 
     * @param myWord
     * @return boolean value.
     */
    public boolean checkCapitalWord(String myWord){
        
        // Immediately return false if the first letter
        // is not capitalized
        char characterIndex = myWord.charAt(0);
        if(Character.isUpperCase(characterIndex) == false){
            return false;
        }


        // Checks the remaining portion of the word to see if 
        // everything is lowercase
        for(int i = 1; i < myWord.length(); i++)
        {
            characterIndex = myWord.charAt(i);

            if(Character.isUpperCase(characterIndex) == true){
                return false;
            }
        }

        return true;
    }

    /**
     * Method to check if an entire word is capitalized.
     * 
     * @param myWord
     * @return boolean value.
     */
    public boolean checkAllCapital(String myWord){

        // Checks the entire word to see if 
        // everything is uppercase
        for(int i = 0; i < myWord.length(); i++)
        {
            char characterIndex = myWord.charAt(i);

            if(Character.isUpperCase(characterIndex) == false){
                return false;
            }
        }

        return true;
    }
}

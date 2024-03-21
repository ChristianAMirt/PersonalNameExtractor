package edu.odu.cs.cs350;

public class PNE {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("PNE.jar [input text]");
        } else {
            String inputText = String.join(" ", args);
            Document doc = new Document(inputText);
            doc.parseDocument();
        }
    }
}
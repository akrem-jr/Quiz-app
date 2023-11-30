package MainFiles;

import java.io.*;
import java.util.*;

// A class that represents a question and its choices and answer
class Quizes {
    private String question;
    private String chooseA;
    private String chooseB;
    private String chooseC;
    private String chooseD;
    private char answer;

    // A constructor that takes the question and choices and answer as parameters
    public Quizes(String question, String chooseA, String chooseB, String chooseC, String chooseD, char answer) {
        this.question = question;
        this.chooseA = chooseA;
        this.chooseB = chooseB;
        this.chooseC = chooseC;
        this.chooseD = chooseD;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getChooseA() {
        return chooseA;
    }

    public String getChooseB() {
        return chooseB;
    }

    public String getChooseC() {
        return chooseC;
    }

    public String getChooseD() {
        return chooseD;
    }

    public char getAnswer() {
        return answer;
    }
    // A function that reads from a file and returns an array of convert objects

    public static Quizes[] readFromFile(String fileName) {
        // A list to store the convert objects
        List<Quizes> list = new ArrayList<>();

        // A try-catch block to handle any exceptions
        try {
            // A buffered reader to read from the file
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            // A string to store each line of the file
            String line;

            // A loop to read each line until the end of the file
            while ((line = br.readLine()) != null) {
                // A string array to split the line by commas
                String[] parts = line.split(",");

                // A check to ensure that the line has six parts
                if (parts.length == 6) {
                    // A convert object to store the question and choices and answer
                    Quizes c = new Quizes(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5].charAt(0));

                    // Add the convert object to the list
                    list.add(c);
                }
            }

            // Close the buffered reader
            br.close();
        } catch (Exception e) {
            // Print the exception message
            System.out.println(e.getMessage());
        }

        // Convert the list to an array and return it
        return list.toArray(new Quizes[list.size()]);
    }
}



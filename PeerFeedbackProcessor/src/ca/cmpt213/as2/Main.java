package ca.cmpt213.as2;

import com.google.gson.JsonParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String filePath;
    private static String output;
    private static List<Feedback> feedbacks = new ArrayList<>();

    private static void handleUserArguments(String[] args) {
        if (args.length == 2) {
            // handle path and output
            filePath = args[0];
            output = args[1];
        } else {
            throw new Error("Invalid arguments: Expected <filePath> <outputPath>");
        }
    }

    public static void main(String[] args) {
	// write your code here
        handleUserArguments(args);
        File[] files = FileHandler.getFiles(filePath);

        for (File file : files) feedbacks.add(FileHandler.readJSONFile(file));

        for (Feedback feedback : feedbacks) {
            System.out.println(feedback.getComments());
        }
    }
}

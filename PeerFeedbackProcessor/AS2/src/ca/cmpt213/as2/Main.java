package ca.cmpt213.as2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String filePath;
    private static String output;

    private static List<StudentGroup> feedbackGroups = new ArrayList<>();

    private static void handleUserArguments(String[] args) {
        if (args.length == 2) {
            // handle path and output
            filePath = args[0];
            output = args[1];
        } else {
            ErrorHandler.error("Invalid arguments: Expected <filePath> <outputPath>");
        }
    }

    public static void main(String[] args) {
        handleUserArguments(args);
        // find JSON files
        List<String> files = FileHandler.getFiles(filePath);
        List<Feedback> feedbacks = new ArrayList<>();

        // read JSON files from input dir
        for (String file : files) feedbacks.add(FileHandler.readJSONFile(file));

        // find number of teams
        List<Integer> teams = new ArrayList<>();
        for (Feedback feedback : feedbacks) {
            int team = Integer.parseInt(feedback.getCreator().getGroupID());

            if (!teams.contains(team)) teams.add(team);
        }

        // create buckets for the teams
        for (int team : teams) feedbackGroups.add(new StudentGroup());

        for (Feedback feedback : feedbacks) {
            Group student = feedback.getCreator();
            int team = Integer.parseInt(student.getGroupID());
            StudentGroup bucket = feedbackGroups.get(team - 1);
            bucket.addFeedback(feedback);
        }

        WriteHandler.writeToCSV(new File(output), feedbackGroups);
    }
}

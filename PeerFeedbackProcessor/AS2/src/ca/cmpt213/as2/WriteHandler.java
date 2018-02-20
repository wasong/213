package ca.cmpt213.as2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class WriteHandler {
    private static String rowHeading() {
        return "Group#,Source Student,Target Student,Score,Comment,,Private";
    }

    public static void writeToCSV(File output, List<StudentGroup> studentList) {
        try {
            PrintWriter writer = new PrintWriter(output);

            writer.println(rowHeading());

            for (StudentGroup group : studentList) {
                writer.println("Group " + group.getGroupID());

                for (Feedback f : group.getFeedbacks()) {
                    writer.println(f.prettifyData());
                }
            }

            writer.close();
        } catch (FileNotFoundException e) {
            ErrorHandler.error("Invalid output file: " + output.getName() + " at " + output.getAbsolutePath());
        }
    }
}

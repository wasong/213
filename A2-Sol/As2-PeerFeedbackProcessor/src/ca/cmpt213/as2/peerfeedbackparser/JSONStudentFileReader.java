package ca.cmpt213.as2.peerfeedbackparser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;
import com.google.gson.stream.MalformedJsonException;

/**
 * Utility class to read a JSON Student Comment File
 */
public class JSONStudentFileReader {
    public static List<Student> readStudentsFromJSONFiles(File sourceDirector) {
        // Error checking
        if (!sourceDirector.exists()) {
            ErrorHandler.displayErrorAndExit(
                    "ERROR: Specified source directory does not exist: " + sourceDirector
            );
        }
        if (!sourceDirector.isDirectory()) {
            ErrorHandler.displayErrorAndExit(
                    "ERROR: Specified source directory is not actually a directory: " + sourceDirector
            );
        }

        // Find files to process:
        File[] foundFiles = sourceDirector.listFiles(new FileFilter() {
            private static final String JSON_SUFFIX = ".json";

            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory()
                    || (pathname.getName().toLowerCase().endsWith(JSON_SUFFIX));
            }
        });

        // Process files:
        List<Student> students = new ArrayList<>();
        for (File file : foundFiles) {
            if (file.isDirectory()) {
                students.addAll(readStudentsFromJSONFiles(file));
            }

            if (file.isFile()) {
                Student newStudent = readStudentFromJSONFile(file);
                students.add(newStudent);
            }
        }

        return students;
    }

    private static Student readStudentFromJSONFile(File file) {
        assert (file.isFile() && file.exists());

        // Note: In reality, the only thing we can trust is the folder name coming
        // from CourSys, so we create the student based on this.
        // For this assignment, students are not told to rely on this folder name,
        // so student solutions won't be dealing with the name of the parent folder.
        String parentFolderName = file.getParentFile().getName();
        Student student = new Student(parentFolderName.trim(), file);

        try {
            JsonParser parser = new JsonParser();
            JsonElement fileElement = parser.parse(new FileReader(file));
            JsonObject fileObject = fileElement.getAsJsonObject();

            // Extract private feedback
            String privateFeedback = fileObject.get("confidential_comments").getAsString();
            student.setConfidential_comments(privateFeedback);

           // Extract group
           JsonArray group = fileObject.get("group").getAsJsonArray();
           boolean firstComment = true;
           for (JsonElement element : group) {
               JsonObject studentComment = element.getAsJsonObject();

               String name = studentComment.get("name").getAsString();
               String email = studentComment.get("sfu_email").getAsString().trim();

               JsonObject contribution = studentComment.get("contribution").getAsJsonObject();
               double score = contribution.get("score").getAsDouble();
               String comment = contribution.get("comment").getAsString();

               // Add the comment to the student
               student.addCommentOnTeammate(name, email.trim(), score, comment);

               // If this is the first comment left, ensure it matches the student ID of the folder
               // (Valid in reality, doing the check this way is not an assignment requirement).
               if (firstComment) {
                   if (!student.matchesEmail(email)) {
                       ErrorHandler.displayErrorAndExit(
                               "ERROR: Incorrect student ID (" + email + ") for self.\n" +
                               "      (file: " + file.getPath() + ")"
                       );
                   }
               }
               firstComment = false;
           }
        } catch (FileNotFoundException e) {
            ErrorHandler.displayErrorAndExit(
                    "ERROR: Unable to open file ('file not found')." +
                    "      (file: " + file.getPath()  + ")"
            );
        } catch (Exception e) {
            ErrorHandler.displayErrorAndExit(
                    "ERROR: Unable to process file (malformed JSON file?)\n" +
                    "      (file: " + file.getPath()  + ")"
            );
        }
        return student;
    }
}

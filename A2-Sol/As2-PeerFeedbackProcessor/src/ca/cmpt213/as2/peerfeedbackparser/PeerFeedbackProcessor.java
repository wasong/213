package ca.cmpt213.as2.peerfeedbackparser;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// NOTE: Run with:
// java -jar out/artifacts/As2_PeerFeedbackProcessor_jar/As2-PeerFeedbackProcessor.jar data/generated/2-OneGroup output/results.csv


/**
 * Main application to read in JSON data from student feedback JSON files and generate files to:
 * 1. Upload comments to CourSys (JSON)
 * 2. Review by instructor (CSV)
 */
public class PeerFeedbackProcessor {
    private static double POINTS_PER_STUDENT = 20.0;

    // Some defaults to run on if wanting to hard code some tests.
//    private static final String TEST_NAME = "1-Just1";
//    private static final String TEST_NAME = "2-OneGroup";
//    private static final String TEST_NAME = "3-CoupleSmallGroups";
    private static final String TEST_NAME = "4-BiggerTest";

    // Hard-coded outputs (for testing only!)
    private static final String SOURCE_FOLDER = "data/generated/" + TEST_NAME;
    private static final String TARGET_CSV_FOR_INSTRUCTOR_FILE = TEST_NAME + "/group_feedback.csv";
    private static final String TARGET_JSON_FOR_COURSYS_FILE = TEST_NAME + "/group_feedback.json";



    private static final int ARGS_SOURCE_FOLDER_IDX = 0;
    private static final int ARGS_TARGET_FILE_IDX = 1;
    private static final int ARG_COUNT_EXPECTED = 2;


    public static void main(String[] args) {
        File sourceFolder;
        File targetCsvFile;
        File targetJsonFile;
        if (args.length == ARG_COUNT_EXPECTED) {
            sourceFolder = new File(args[ARGS_SOURCE_FOLDER_IDX]);
            targetCsvFile = new File(args[ARGS_TARGET_FILE_IDX]);
            targetJsonFile = new File(targetCsvFile.getAbsolutePath().replaceFirst(".csv", ".json"));
        }

        // Defaults for testing:
//        else if (args.length == 0) {
//            sourceFolder = new File (SOURCE_FOLDER);
//            targetCsvFile = new File("output/" + TARGET_CSV_FOR_INSTRUCTOR_FILE);
//            targetJsonFile = new File("output/" + TARGET_JSON_FOR_COURSYS_FILE);
//        }

        else {
            ErrorHandler.displayErrorAndExit(
                    "ERROR: Must give two command line parameters:\n" +
                    "       1. Source directory to search recursively for JSON files.\n" +
                    "       2. Target .csv file path."
            );
            return;
        }

        // Read data from source files
        List<Student> students = JSONStudentFileReader.readStudentsFromJSONFiles(sourceFolder);

        // Infer groups
        List<Group> groups = inferGroupsFromStudents(students);

        // Check groups for any errors
        if (checkIfGroupsAreOk(groups)) {
            // Generate output:
            // JSON For Coursys not part of assignment.
//            generateJSONForCourSys(groups, targetJsonFile);
            generateCSVForInstructor(groups, targetCsvFile);
        } else {
            ErrorHandler.displayErrorAndExit(
                    "ERROR: Unable to generate .json and .csv files due to issues with student comments.\n" +
                    "       Fix the above listed issues with the processed data and re-run the tool."

            );
        }
        System.out.println("DONE");
    }

    private static List<Group> inferGroupsFromStudents(List<Student> students) {
        List<Group> groups = new ArrayList<>();
        for (Student student : students) {

            Group group = findExistingGroupForStudent(student, groups);

            // Is a new group required?
            if (group == null) {
                group = new Group();
                groups.add(group);
            }

            group.addStudent(student);
        }
        return groups;
    }

    private static Group findExistingGroupForStudent(Student student, List<Group> groups) {
        Group existingGroup = null;
        for (Group group : groups) {

            // Already in a group
            if (group.containsStudent(student.getEmail())) {
                Student existingStudent = group.getStudent(student.getEmail());
                ErrorHandler.displayErrorAndExit(
                    "ERROR: Duplicate student found in files:\n" +
                    "       (file: " + student.getSourceFile()  + ")\n" +
                    "       (file: " + existingStudent.getSourceFile() + ")"
                );
            }

            // Other students in group mention this student?
            if (group.containsCommentOnStudent(student.getEmail())) {
                // Somehow already in a group already?
                if (existingGroup != null) {
                    ErrorHandler.displayErrorAndExit(
                            "ERROR: Student found in multiple groups: " + student.getEmail() + "\n" +
                            "       (file: " + student.getSourceFile() + ")"
                    );
                }
                existingGroup = group;
            }
        }
        return existingGroup;
    }

    private static boolean checkIfGroupsAreOk(List<Group> groups) {
        // Do each one individually to avoid short-circuit evaluation.
        boolean allOk = true;
        allOk &= checkIfHaveAtLeastOneGroup(groups);
        allOk &= checkIfGroupsHaveFullFeedback(groups);
        allOk &= checkIfScoresOk(groups);
        return allOk;

    }

    private static boolean checkIfHaveAtLeastOneGroup(List<Group> groups) {
        if (groups.size() == 0) {
            System.out.println(
                    "ERROR: No input .JSON files found."
            );
            return false;
        }
        return true;
    }


    private static boolean checkIfGroupsHaveFullFeedback(List<Group> groups) {
        boolean allOk = true;
        for (Group group : groups) {
            for (Student student : group) {
                // Check we have at least the right number for the group:
                if (group.getSize() != student.getNumberFeedbacks()) {
                    System.out.println(
                            "Feedback Issue: \n" +
                            "   Student gives feedback for different number of students than found in group\n" +
                            "   (student gives " + student.getNumberFeedbacks() + ", group size " + group.getSize() + ")\n"+
                            "   (file: " + student.getSourceFile().getPath() + " )"
                    );
                    allOk = false;
                }

                // Ensure each student in group has feedback from all other students.
                for (Student otherStudent : group) {
                    String myEmail = student.getEmail();
                    if (!otherStudent.matchesEmail(myEmail) && !otherStudent.hasCommentOnEmail(myEmail)) {
                        System.out.println("Feedback Issue: ");
                        System.out.println("   Student " + otherStudent.getEmail()
                                + " does not comment on " + student.getEmail());
                        System.out.println("   (file: " + otherStudent.getSourceFile().getPath() + " )");
                        allOk = false;
                    }

                }
            }
        }
        return allOk;
    }

    private static boolean checkIfScoresOk(List<Group> groups) {
        boolean allOk = true;
        for (Group group : groups) {
            for (Student student : group) {
                // Check negative
                if (student.hasNegativeScore()) {
                    System.out.println(
                            "SCORE ERROR: Negative score found in student feedback.\n" +
                            "    (file: " + student.getSourceFile().getPath() + ")"
                    );
                    allOk = false;
                }

                // Check sum score
                double expectedScore = group.getSize() * POINTS_PER_STUDENT;
                boolean sumIncorrect =
                        Math.abs(student.sumFeedbackScoresTheyGave() - expectedScore) >= 0.1;
                if (sumIncorrect) {
                    System.out.println(
                            "SCORE ERROR: Sum of scores in file is incorrect.\n" +
                            "    Sum = " + student.sumFeedbackScoresTheyGave() + ", Expected " + expectedScore + "\n" +
                            "    (file: " + student.getSourceFile().getPath() + ")"
                    );
                    allOk = false;
                }
            }
        }
        return allOk;
    }


    // Not part of assignment requirements, but needed in practice.
    private static void generateJSONForCourSys(List<Group> groups, File outputFile) {
        try {
            // Create output folder:
            File outputFolder = outputFile.getParentFile();
            if (!outputFolder.exists()) {
                if (!outputFolder.mkdirs()) {
                    ErrorHandler.displayErrorAndExit(
                            "ERROR: Unable to create output folder: "
                            + outputFolder.getAbsolutePath());
                }
            }

            // Open
            PrintWriter writer = new PrintWriter(outputFile);
            writer.print("{");
            writer.print("  \"marks\": [\n");

            // Print
            for (Group group : groups) {
                double scoreSum = 0;
                int numStudentsCommenting = 0;
                for (Student targetStudent : group) {
                    String targetEmail = targetStudent.getEmail();

                    List<String> feedbacks = new ArrayList<>();

                    for (Student sourceStudent : group) {
                        // Skip self
                        if (sourceStudent.matchesEmail(targetEmail)) {
                            continue;
                        }

                        // Add to feedback
                        numStudentsCommenting++;
                        scoreSum += sourceStudent.getScoreForStudent(targetEmail);
                        feedbacks.add(sourceStudent.getCommentForStudent(targetEmail));
                    }

                    double averageScore = scoreSum / numStudentsCommenting;

                    // Randomize order of output
                    Collections.shuffle(feedbacks);
                    String feedback = "";
                    for (int i = 0; i < feedbacks.size(); i++) {
                        String s = feedbacks.get(i);
                        feedback += "[STUDENT " + (i + 1) + "]:\n";
                        feedback += s;
                        feedback += "\n\n";
                    }

                    // Generate JSON output
                    writer.printf("    \"userid\":\"%s\"\n", targetEmail);
                    writer.printf("    \"feedback-contribution\": {\"mark\": %.2f, \"comment\":\"%s\"}\n",
                            averageScore,
                            feedback);
                }

                writer.println("");
            }
            // Close
            writer.print("  ]");
            writer.print("}");

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ErrorHandler.displayErrorAndExit("ERROR: Unable to write output JSON file.");
        }
    }

    private static void generateCSVForInstructor(List<Group> groups, File outputFile) {
        try {
            // Create output folder:
            File outputFolder = outputFile.getParentFile();
            if (!outputFolder.exists()) {
                if (!outputFolder.mkdirs()) {
                    ErrorHandler.displayErrorAndExit("ERROR: Unable to create output folder: "
                            + outputFolder.getAbsolutePath());
                }
            }

            PrintWriter writer = new PrintWriter(outputFile);
//            writer.println("Group Feedback Analysis");
//            writer.println("Date: " + new Date());
//            writer.println();
            writer.println("Group#,Source Student,Target Student,Score,Comment,,Private");

            // Write out groups
            int groupNum = 0;
            for (Group group : groups) {
                groupNum++;
                writer.println("Group " + groupNum);
                for (Student targetStudent : group) {
                    // Find all comments about this student and sum score.
                    int numStudentsCommenting = 0;
                    double scoreSum = 0;
                    String targetEmail = targetStudent.getEmail();
                    for (Student sourceStudent : group) {
                        // Skip self
                        if (sourceStudent.matchesEmail(targetEmail)) {
                            continue;
                        }

                        // Print data:
                        double score = sourceStudent.getScoreForStudent(targetEmail);
                        String comment = sourceStudent.getCommentForStudent(targetEmail);
                        writer.printf(",%s,%s,%.1f,\"%s\",,%n",
                                sourceStudent.getEmail(),
                                targetEmail,
                                score,
                                comment
                                );

                        scoreSum += score;
                        numStudentsCommenting++;
                    }

                    // Write out score summary and self comments:
                    double averageScore = scoreSum / numStudentsCommenting;
                    writer.printf(",-->,%s,%.1f,\"%s\"%n",
                            targetEmail,
                            targetStudent.getScoreForStudent(targetEmail),
                            targetStudent.getCommentForStudent(targetEmail)
                    );
                    writer.printf(",-->,%s,avg %.1f /%d,,,\"%s\"%n",
                            targetEmail,
                            averageScore,
                            numStudentsCommenting,
                            targetStudent.getConfidentialComments()
                    );
                }
                writer.println("");
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ErrorHandler.displayErrorAndExit("ERROR: Unable to write output CSV file.");
        }
    }

}

package ca.cmpt213.as2.peerfeedbackparser;

/**
 * Utility class for handling an error.
 */
public class ErrorHandler {
    public static void displayErrorAndExit(String message) {
        final int FAILURE = -1;

        // May be .out. or .err.
        System.err.println(message);
        System.exit(FAILURE);
    }
}

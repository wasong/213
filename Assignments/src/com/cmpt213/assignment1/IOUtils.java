package com.cmpt213.assignment1;

import java.util.Scanner;

public class IOUtils {
    public static void multiPrint(int numTimes, String text, Boolean inline) {
        for (int i = 0; i < numTimes; i++) {
            if (inline) {
                System.out.print(text);
            } else {
                System.out.println(text);
            }
        }
    }

    public static void printAsterisks(int numTimes) {
        multiPrint(numTimes, "*", true);
        System.out.print("\n");
    }

    public static String readInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

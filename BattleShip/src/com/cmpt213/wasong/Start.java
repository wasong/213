package com.cmpt213.wasong;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Start {
    private static int tanks = 5;
    private static boolean isCheating = false;
    private static List<Tank> tankList = new ArrayList<>();
    private static Fortress fortress = new Fortress(1500);
    private static boolean isFinished = false;
    private static boolean hasWon = false;

    private static boolean checkGameState() {
        // handle checking game end reqs
        int HP = fortress.getHp();
        int tanksLeft = 0;

        for(Tank t : tankList) tanksLeft += t.remainingCells();

        if (HP <= 0) {
            isFinished = true;
        } else if (tanksLeft == 0) {
            isFinished = true;
            hasWon = true;
        }

        return isFinished;
    }

    public static void main(String[] args) {
	    // set tanks
        if (args.length == 1) {
            // TODO: handle --cheat only arg
            tanks = Integer.parseInt(args[0]);
        } else if (args.length == 2) {
            tanks = Integer.parseInt(args[0]);
            isCheating = args[1].equals("--cheat");
        }

        Field.generateGrid(5);
        tankList = Field.generateTanks(tanks);
        Field.showGrid();

        while (!isFinished) {
            boolean validMove = false;
            String move;
            List<Integer> point;
            while (!validMove) {
                System.out.println("Enter your move: ");
                Scanner s = new Scanner(System.in);
                move = s.next();
                validMove = move.length() == 2;

                if (!validMove) {
                    System.out.println("Invalid move! Try again.");
                } else {
                    point = Utils.mapCoordinateToPoint(move);
                    System.out.println(point.get(0) + " " + point.get(1));
                    if (point.get(0) == -1 || point.get(1) == -1) {
                        validMove = false;
                        System.out.println("Invalid move! Try again.");
                    }
                }
            }

        }
    }
}

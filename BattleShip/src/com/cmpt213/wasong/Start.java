package com.cmpt213.wasong;

public class Start {
    private static int tanks = 5;
    private static boolean isCheating = false;
    private static boolean hasWon = false;
    private static boolean hasLost = false;

    private static boolean checkGameState() {
        // handle checking game end reqs
        return false;
    }

    public static void main(String[] args) {
	    // set tanks
        if (args.length == 1) {
            tanks = Integer.parseInt(args[0]);
        } else if (args.length == 2) {
            tanks = Integer.parseInt(args[0]);
            isCheating = args[1].equals("--cheat");
        }

        Field.generateGrid(5);
        Field.showGrid();
    }
}

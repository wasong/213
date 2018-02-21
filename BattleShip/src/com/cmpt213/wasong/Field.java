package com.cmpt213.wasong;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Field {
    private static List<List<Cell>> grid = new ArrayList<>();
    private static final int gridX = 10;
    private static final int gridY = 10;

    public static void generateGrid() {
        for (int i = 0; i < gridY; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < gridX; j++) {
                Cell c = new Cell();
                c.setCoordinates(i, j);
                row.add(c);
            }
            grid.add(row);
        }
    }

    public static List<Tank> generateTanks(int tanks) {
        List<Tank> tankList = new ArrayList<>();

//        System.out.println("---Tetromino Debug---");
        for (int i = 0; i < tanks; i++) {
            int tankID = 'A' + i;
            List<Cell> tetrominos = generateTetromino((char) tankID);

            tankList.add(new Tank(tetrominos, (char) tankID));

//            System.out.println("Tetromino: " + i);
//            for (Cell c : tetrominos) {
//                System.out.println(c.isTank() + " " + c.getCoordinates());
//            }
//            System.out.println();
        }
//        System.out.println("---------------------");
        return tankList;
    }

    private static List<Cell> generateTetromino(char tankID) {
        List<Integer> point = Utils.selectRandomCell(0, 9);
        List<Cell> tetromino = new ArrayList<>();
        int x = point.get(0);
        int y = point.get(1);
//        System.out.println("First X: " + x + ", Y: " + y);

        Cell selectedCell = getCell(x, y);

        // find an empty starting point
        while (selectedCell.isTank()) {
//            System.out.println("Is tank:");
            point = Utils.selectRandomCell(0, 9);
            x = point.get(0);
            y = point.get(1);

//            System.out.println(x + " " + y);

            selectedCell = getCell(x, y);
        }

        selectedCell.setAsTank();
        selectedCell.setTankID(tankID);

        // add to tetromino
        tetromino.add(selectedCell);

        for (int i = 0; i < 3; i++) {
            boolean inBound = false;
            int retry = 0;

            while (!inBound) {
                int testX = x;
                int testY = y;

                char direction = Utils.selectRandomDirection();
//                System.out.println("Direction: " + direction);
                switch (direction) {
                    case 'T':
                        testY -= 1;
                        break;
                    case 'R':
                        testX += 1;
                        break;
                    case 'B':
                        testY += 1;
                        break;
                    case 'L':
                        testX -= 1;
                        break;
                    default:
                        break;
                }
                inBound = testX > -1 && testX < 10 && testY > -1 && testY < 10;
//                System.out.println("InBound: " + inBound);
                if (inBound) {
                    // check if the Cell is a tank
                    if (getCell(testX, testY).isTank()) {
//                        System.out.println("Is Tank!");
                        inBound = false;
                        retry += 1;
                    } else {
//                        System.out.println("Is Not Tank!");
                        x = testX;
                        y = testY;
                        selectedCell = getCell(x, y);
                        selectedCell.setAsTank();
                        selectedCell.setTankID(tankID);
                    }
                }
//                System.out.println("Finished! " + " X: " + testX + ", Y: " + testY);
                if (retry > 10) {
                    i -= 1;
                    System.out.println("Retrying!");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    break;
                }
            }
            if (retry <= 10) {
                tetromino.add(selectedCell);
//                System.out.println((i + 1) + "/3\n");
            }
        }
        return tetromino;
    }

    public static Cell getCell(int x, int y) {
        return grid.get(x).get(y);
    }

    public static boolean targetCell(int x, int y) {
        Cell target = getCell(x, y);
        return target.handleOnHit();
    }

    public static void showGrid(boolean endMap, boolean isCheating) {
        // offset row label
        System.out.print("  ");

        // print column labels
        for (int i = 0; i < gridX; i++) System.out.print(" " + i + " ");
        System.out.println();

        for (int i = 0; i < gridY; i++) {
            // print row labels
            List<Cell> l = grid.get(i);
            System.out.print((char) ('A' + i) + " ");

            for(Cell c : l) {
                if (endMap || isCheating) {
                    if (c.isHit() && c.isTank()) {
                        System.out.print("[X]");
                    } else if (c.isHit() && !c.isTank() && endMap) {
                        System.out.print("[m]");
                    } else if (c.isTank()) {
                        System.out.print("[" + c.getTankID() + "]");
                    } else  {
                        System.out.print(endMap ? "[ ]" : "[.]");
                    }
                } else {
                    if (c.isHit() && c.isTank()) {
                        System.out.print("[X]");
                    } else if (c.isHit() && !c.isTank()) {
                        System.out.print("[ ]");
                    } else {
                        System.out.print("[~]");
                    }
                }

            }
            System.out.println();
        }
    }

}

package com.cmpt213.wasong;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Field {
    private static List<List<Cell>> grid = new ArrayList<>();

    public static void generateGrid(int tanks) {
        int gridY = 10;
        int gridX = 10;

        for (int i = 0; i < gridY; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < gridX; j++) {
                row.add(new Cell());
            }
            grid.add(row);
        }

//        generateTetromino();

        System.out.println("---Tetromino Debug---");
        for (int i = 0; i < tanks; i++) {
            List<Cell> tetrominos = generateTetromino();
            System.out.println("Tetromino: " + i);
            for (Cell c : tetrominos) {
                System.out.println(c.getTankID() + " " + c.getCoordinates());
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }

    private static List<Cell> generateTetromino() {
        List<Integer> point = Utils.selectRandomCell(0, 9);
        List<Cell> tetromino = new ArrayList<>();
        int x = point.get(0);
        int y = point.get(1);
        System.out.println("First X: " + x + ", Y: " + y);

        Cell selectedCell = getCell(x, y);

        // find an empty starting point
        while (selectedCell.isTank()) {
            System.out.println("Is tank:");
            point = Utils.selectRandomCell(0, 9);
            x = point.get(0);
            y = point.get(1);

            System.out.println(x + " " + y);

            selectedCell = getCell(x, y);
        }

        selectedCell.setCoordinates(x, y);

        // third party solution from https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
        // set tankID
        int tankID = ThreadLocalRandom.current().nextInt(1000, 9999);
        selectedCell.setTankID(tankID);

        // add to tetromino
        tetromino.add(selectedCell);

        for (int i = 0; i < 3; i++) {
            boolean inBound = false;
            // check if in bounds

            int retry = 0;

            while (!inBound) {
                int testX = x;
                int testY = y;

                char direction = Utils.selectRandomDirection();
                System.out.println("Direction: " + direction);
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
                System.out.println("InBound: " + inBound);
                if (inBound) {
                    // check if the Cell is a tank
                    if (getCell(testX, testY).isTank()) {
                        System.out.println("Is Tank!");
                        inBound = false;
                        retry += 1;
                    } else {
                        System.out.println("Is Not Tank!");
                        x = testX;
                        y = testY;
                        selectedCell = getCell(x, y);
                        selectedCell.setTankID(tankID);
                        selectedCell.setCoordinates(x, y);
                    }
                }
                System.out.println("Finished! " + " X: " + testX + ", Y: " + testY);
                if (retry > 10) {
                    i -= 1;
                    System.out.println("Retrying!");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    break;
                }
            }
            if (retry <= 10) {
                tetromino.add(selectedCell);
                System.out.println((i + 1) + "/3\n");
            }
        }
        return tetromino;
    }

    private static Cell getCell(int x, int y) {
        return grid.get(x).get(y);
    }

    public static void showGrid() {
        for(List<Cell> l : grid) {
            for(Cell c : l) {
                if (c.checkIfHit()) {
                    System.out.print("[X]");
                } else if (c.isTank()) {
//                    System.out.println("[" + c.getTankID() + "]");
                    System.out.print("[T]");
                } else  {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }

}

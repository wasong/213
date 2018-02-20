package com.cmpt213.wasong;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {
    public static List<Integer> selectRandomCell(int min, int max) {
        int randomX = ThreadLocalRandom.current().nextInt(min, max);
        int randomY = ThreadLocalRandom.current().nextInt(min, max);

        List<Integer> point = new ArrayList<>();
        point.add(randomX);
        point.add(randomY);

        return point;
    }

    public static char selectRandomDirection() {
        int dir = ThreadLocalRandom.current().nextInt(0, 3);

        switch (dir) {
            case 0:
                return 'T';
            case 1:
                return 'R';
            case 2:
                return 'B';
            case 3:
                return 'L';
            default:
                return 'T';
        }
    }

    public static List<Integer> mapCoordinateToPoint(String coordinate) {
        List<Integer> point = new ArrayList<>();

        int pointX = mapASCIIToDecimal(coordinate.charAt(0));
        int pointY = Character.getNumericValue(coordinate.charAt(1));

        point.add(pointX);
        point.add(pointY);

        return point;
    }

    private static int mapASCIIToDecimal(int value) {
        int lowercase = 97;
        int uppercase = 65;

        int maxLowercase = lowercase + 10;
        int maxUppercase = uppercase + 10;

        if (value >= uppercase && value < maxUppercase) {
            return value - uppercase;
        }

        if (value >= lowercase && value < maxLowercase) {
            return value - lowercase;
        }

        return -1;
    }
}

package com.cmpt213.wasong;

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
}

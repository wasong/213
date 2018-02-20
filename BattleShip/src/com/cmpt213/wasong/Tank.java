package com.cmpt213.wasong;

import java.util.List;

public class Tank {
    private char id;
    private List<Cell> cells;

    public Tank(List<Cell> cells, char id) {
        this.cells = cells;
        this.id = id;
    }

    public char getId() {
        return id;
    }

    public int remainingCells() {
        int remaining = 0;
        for(Cell c : cells) if (!c.isHit()) remaining += 1;
        return remaining;
    }

    public int damageDone() {
        int damage = 0;
        for (Cell c : cells) {
            if (!c.isHit()) {
                damage += 1;
            }
        }

        switch (damage) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 5;
            case 4:
                return 20;
            default:
                return 0;
        }
    }
}

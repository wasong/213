package com.cmpt213.wasong;

import java.util.List;

public class Tank {
    private List<Cell> cells;

    public Tank(List<Cell> cells) {
        this.cells = cells;
    }

    public int remainingCells() {
        int remaining = 0;
        for(Cell c : cells) if (!c.isHit()) remaining += 1;
        return remaining;
    }

    public int attack() {
        int damage = 0;
        for (Cell c : cells) {
            if (c.isHit()) {
                damage += 1;
            }
        }
        return damage;
    }
}

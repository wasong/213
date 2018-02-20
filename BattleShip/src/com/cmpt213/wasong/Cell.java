package com.cmpt213.wasong;

public class Cell {
    private int tankID = 0;
    private boolean isHit = false;
    private int x;
    private int y;

    public Cell() {}

    public boolean checkIfHit() {
        return isHit;
    }

    public int getTankID() {
        return tankID;
    }

    public boolean isTank() {
        return tankID != 0;
    }

    public void handleOnHit() {
        this.isHit = true;
    }

    public void setTankID(int tankID) {
        this.tankID = tankID;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getCoordinates() {
        return "X: " + x + ", Y: " + y;
    }
}

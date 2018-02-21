package com.cmpt213.wasong;

public class Cell {
    private char tankID;
    private boolean isTank = false;
    private boolean isHit = false;
    private int x;
    private int y;

    public Cell() {}

    public boolean isHit() {
        return isHit;
    }

    public boolean isTank() {
        return isTank;
    }

    public boolean handleOnHit() {
        this.isHit = true;
        return isHit && isTank;
    }

    public void setAsTank() {
        this.isTank = true;
    }

    public void setTankID(char tankID) {
        this.tankID = tankID;
    }

    public char getTankID() {
        return tankID;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getCoordinates() {
        return "X: " + x + ", Y: " + y;
    }
}

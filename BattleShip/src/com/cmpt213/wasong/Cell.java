package com.cmpt213.wasong;

public class Cell {
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

    public void handleOnHit() {
        this.isHit = true;
    }

    public void setAsTank() {
        this.isTank = true;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getCoordinates() {
        return "X: " + x + ", Y: " + y;
    }
}

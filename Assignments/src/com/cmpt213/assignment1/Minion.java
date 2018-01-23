package com.cmpt213.assignment1;

public class Minion {
    private String name;
    private double height;
    private int numEvilDeeds;

    public Minion(String name, double height, int numEvilDeeds) {
        this.name = name;
        this.height = height;
        this.numEvilDeeds = numEvilDeeds;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[Name:" + name + ", Evil Deeds:" + numEvilDeeds + ", Height:" + height + "]";
    }

//    public String getName() {
//        return name;
//    }
//
//    public double getHeight() {
//        return height;
//    }
//
//    public int getNumEvilDeeds() {
//        return numEvilDeeds;
//    }

    public String printFormattedInfo() {
        return name + ", " + height + ", " + numEvilDeeds + (numEvilDeeds > 1 || numEvilDeeds == 0 ? " evil deeds" : " evil deed");
    }

    public void incrementEvilDeed() {
        this.numEvilDeeds += 1;
    }
}

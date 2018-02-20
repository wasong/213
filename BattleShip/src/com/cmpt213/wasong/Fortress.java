package com.cmpt213.wasong;

public class Fortress {
    private int hp;
    public Fortress(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void getHit(int hp) {
        if (hp > this.hp) {
            this.hp = 0;
            return;
        }
        this.hp -= hp;
    }
}

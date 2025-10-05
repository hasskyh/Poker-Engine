package org.example;

public class Player {
    private Card[] hand = new Card[2];
    private int chipValue;

    public Player() {
        this.chipValue = 0;
    }

    public Player(int value) {
        this.chipValue = value;
    }

    public Card[] getHand() {
        return hand;
    }

    public int getChipValue() {
        return chipValue;
    }

    public void setHand(Card[] newHand) {
        this.hand = newHand;
    }
}


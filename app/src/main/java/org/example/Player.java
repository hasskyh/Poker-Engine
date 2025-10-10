package org.example;

public class Player {
    private Card[] hand = new Card[2]; //The hand the player has
    private int chipValue; //The amount of chips the player has

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

    public void setHand(Card card1, Card card2) {
        this.hand = new Card[]{card1, card2};
    }

}


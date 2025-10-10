package org.example;

public class Player {
    private Card[] hand = new Card[2]; //The hand the player has
    private int chipValue; //The amount of chips the player has
    private Boolean folded = false;
    private int anteValue; //The amount of money the player has put into the round they're playing

    public Player() {
        this.chipValue = 0;
        this.anteValue = 0;
    }

    public Player(int value) {
        this.chipValue = value;
        this.anteValue = 0;
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

    public Boolean isFolded() {
        return folded;
    }

    public void setFolded(Boolean folded) {
        this.folded = folded;
    }

    public int getAnte() {
        return anteValue;
    }

    public void setAnte(int value) {
        this.anteValue = value;
    }

}


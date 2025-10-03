package org.example;
import java.util.LinkedList;
import org.example.Card;
import org.example.Card.Suit;
import org.example.Card.Rank;

public class Deck {
    public LinkedList<Card> deck;
    public int cardInDeck;

    public Deck() {
        this.deck = new LinkedList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
                cardInDeck++;
            }
        }
    }
}

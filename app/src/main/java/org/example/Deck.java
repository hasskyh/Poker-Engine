package org.example;
import java.util.LinkedList;
import java.util.Collections;
import org.example.Card.Suit;
import org.example.Card.Rank;

public class Deck {
    private static Deck instance;
    private final LinkedList<Card> deck;
    public int cardInDeck;

    private Deck() {
        this.deck = new LinkedList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
                cardInDeck++;
            }
        }

        shuffle();
    }

    //Use this to ensure only one version of the deck is going around at any one time.
    public static Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card drawCard() {
        return deck.pop();
    }
}

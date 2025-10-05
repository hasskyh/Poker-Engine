package org.example;
import java.util.LinkedList;
import java.util.Collections;
import java.util.NoSuchElementException;
import org.example.Card.Suit;
import org.example.Card.Rank;

public class Deck {
    private static Deck instance;
    private final LinkedList<Card> deck;
    public int cardsInDeck;

    private Deck() {
        this.deck = new LinkedList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
                cardsInDeck++;
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

    static void burnDeck() {
        instance = null;
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card drawCard() {
        try {
            Card card = deck.pop();
            cardsInDeck--;
            return card;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void replaceCard(Card card) {
        if (card != null) {
            cardsInDeck++;
            deck.push(card);
        }
    }
}

package org.example;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
    static int dealer = 0;                                  //The dealer starts out as the player at position 0
    static int lastRaiser = 0;                              //The last person to raise is technically the person after the small blind, even though the big blind has set the raise value, but we set this so it doesn't end the round when it reaches them
    Deck deck = Deck.getInstance();                         //The deck used to get playing cards from
    static LinkedList<Player> players = new LinkedList<>(); //The list of players in the round
    static Scanner scanner = new Scanner(System.in);        //Scanner to get player decisions
    static int currentPlayer = 0;                           //Index which is used to track who's turn it is
    static int inPlayers = players.size();                  //Used to track how many players are not folded so we can end the round early if they are all gone
    static int ante;                                        //The amount someone needs to put in at minimum when they check
    static final int maxPlayerSize = 23;                    //The maximum amount of players we're able to play with

    //Used for player decisions during play()
    enum playerAction {
        CHECK,
        RAISE,
        FOLD,
        LOOK
    }

    /**
     * This function sets or resets all the information required for the running of a round of Poker
     * @param playersArray The list of players playing, to be converted into a linked List
     */
    public static void initialiseRound(Player[] playersArray, int bigBlind) {
        dealer = 0;
        lastRaiser = 0;
        currentPlayer = 3;
        inPlayers = playersArray.length;
        players.clear(); //To prevent any carryover from previous games
        ante = bigBlind;
        Deck.burnDeck();
        Deck deck = Deck.getInstance(); //Reset the deck
        deck.shuffle(); //Shuffle again for fun

        //We are unable to use more than 52 cards, and we need about 6 of them for the public hands
        //so we are limited to 23 players, which is an obscene quantity of poker players anyway
        //This prevents drawing a card which doesn't exist
        if (playersArray.length > maxPlayerSize) {
            for (int i = 0; i < maxPlayerSize; i++) {
                players.add(playersArray[i]);
            }
        } else {
            for (int i = 0; i < playersArray.length; i++) {
                players.add(playersArray[i]);
            }
        }

        //Deal the cards
        for (int i = 0; i < players.size(); i++) {
             //Technically we're dealing two cards at a time to each player, which isn't how it's done in casinos
             //But this doesn't actually affect the randomness of the deal
            players.get(i).setHand(deck.drawCard(), deck.drawCard());
        }
    }
    

    public static void playRound(int bigBlind) {
        //Check if the round has already been initialised and initialise if not

        //Set up the blinds

        //play round the table until consensus is reached on the raise
        play();
        //Collect all the money raised during the round into a pot and reset all the players antes

        //Deal the flop

        //play round the table until consensus is reached on the raise
        play();
        //Collect all the money raised during the round into a pot and reset all the players antes


        //Deal the turn

        //play round the table until consensus is reached on the raise
        play();
        //Collect all the money raised during the round into a pot and reset all the players antes

        //Deal the river

        //play round the table until consensus is reached on the raise
        play();
        //Collect all the money raised during the round into a pot and reset all the players antes

        //Showdown

    }

    /**
     * Plays a round of betting for the current player, handling their decision to check, raise, fold, or look at their cards.
     * Recursively advances to the next player until the betting round is complete or only one player remains.
     */
    public static void play() {
        //Get the current player
        Player player = players.get(currentPlayer);
        //End the game if everyone else has folded
        if (inPlayers < 2) {
            endRound();
        }
        //Provided the play is not folded, let them do their actions
        if (!player.isFolded()) {

        //Ask the current player what they want to do, fold, check or raise
        //And perform their required action
        playerAction action;
        do {
            action = getAction();

            switch (action) {
                    case CHECK:
                        if (ante > player.getAnte()) {
                            player.setAnte(ante);
                        }
                        break;

                    case RAISE:
                        int amount;
                        do {
                            System.out.println("The current ante is " + ante);
                            System.out.println("By what amount would you like to raise by?");
                            amount = scanner.nextInt();
                        } while (amount <= 0);

                        ante += amount;
                        player.setAnte(ante);
                        lastRaiser = currentPlayer;
                        break;

                    case LOOK:
                        System.out.println("These are your cards: " + player.getHand().toString());
                        break;

                    default:
                        //As we want to default to folding just in case something broke, we will use the default for folding
                        player.setFolded(true);
                        inPlayers--;
            }

        } while (action == playerAction.LOOK);

        } //End of the !player.isFolded() if statement


        //Check if the next player needs to be switched to and recurse if so
        if (lastRaiser == currentPlayer + 1) {
            return; //This means we've gone round without anyone raising, so we should end the round
        } else {
            currentPlayer = (currentPlayer + 1) % players.size();
            play();
        }

    }

    public static playerAction getAction() {
        System.out.println("What would you like to do?");
        System.out.println("1: Check");
        System.out.println("2: Raise");
        System.out.println("3: Fold");
        System.out.println("4: Look at your cards again");

        int playerDecision = scanner.nextInt();
        playerAction action = null;

        do {
            switch (playerDecision) {
                case 1:
                    action = playerAction.CHECK;
                    break;
                case 2:
                    action = playerAction.RAISE;
                    break;
                case 3:
                    action = playerAction.FOLD;
                    break;
                case 4:
                    action = playerAction.LOOK;
                    break;
                default:
                    System.out.println("You don't seem to have provided a valid integer...");
                    System.out.println("What would you like to do?");
                    System.out.println("1: Check");
                    System.out.println("2: Raise");
                    System.out.println("3: Fold");
                    System.out.println("4: Look at your cards again");
            }
        } while (action == null);

        return action;
    }

    /**
     * Once the game has reached a conclusion, this function adds up and attributes the winnings to the correct person,
     * and resets values such as who is folded, anyone holding any cards, and who the dealer is.
     */
    public static void endRound() {
        //TODO
    }

}

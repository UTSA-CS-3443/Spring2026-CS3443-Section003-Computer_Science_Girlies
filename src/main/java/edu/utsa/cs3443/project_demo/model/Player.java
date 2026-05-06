package edu.utsa.cs3443.project_demo.model;

import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> hand;
    private boolean isBot;

    public Player(String name, boolean isBot) {
        this.name = name;
        this.isBot = isBot;
        this.hand = new ArrayList<>();
    }

    /**
     * Bot automatically plays the first valid card found.
     */
    public Card playCard(Card topCard) {

        for (int i = 0; i < hand.size(); i++) {

            Card current = hand.get(i);

            if (current.matches(topCard)) {
                hand.remove(i);
                return current;
            }
        }

        return null;
    }

    /**
     * Draws one card from the deck.
     */
    public void drawCard(Deck deck) {

        Card drawnCard = deck.drawCard();

        if (drawnCard != null) {
            hand.add(drawnCard);
        } else {
            System.out.println("The deck is empty. Cannot draw a card.");
        }
    }

    /**
     * Checks whether the player has at least one valid move.
     */
    public boolean hasValidMove(Card topCard) {

        for (Card card : hand) {

            if (card.matches(topCard)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds a card directly to the player's hand.
     */
    public void addCard(Card card) {

        if (card != null) {
            hand.add(card);
        }
    }

    /**
     * Removes a card from the player's hand.
     */
    public void removeCard(Card card) {
        hand.remove(card);
    }

    /**
     * Returns true if the player has no cards left.
     */
    public boolean hasWon() {
        return hand.isEmpty();
    }

    /**
     * Returns the total number of cards in hand.
     */
    public int getHandSize() {
        return hand.size();
    }

    // Getters
    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public boolean isBot() {
        return isBot;
    }

    // Setters
    public void setBot(boolean bot) {
        isBot = bot;
    }

    public void setName(String name) {
        this.name = name;
    }
}
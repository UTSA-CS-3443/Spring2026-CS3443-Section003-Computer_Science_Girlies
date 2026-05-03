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

    public void drawCard(Deck deck) {
        Card drawnCard = deck.drawCard();

        if (drawnCard != null) {
            hand.add(drawnCard);
        } else {
            System.out.println("The deck is empty. We cannot draw a card.");
        }
    }

    public boolean hasValidMove(Card topCard) {
        for (Card card : hand) {
            if (card.matches(topCard)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public boolean isBot() {
        return isBot;
    }
}
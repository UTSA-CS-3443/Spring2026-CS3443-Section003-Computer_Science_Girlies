package edu.utsa.cs3443.project_demo.model;

import java.util.ArrayList;
import java.util.Stack;

public class Game {

    private ArrayList<Player> players;
    private Deck deck;
    private Stack<Card> discardPile;
    private int currentPlayerIndex;
    private int direction; // 1 = forward, -1 = reverse

    public Game() {
        players = new ArrayList<>();
        deck = new Deck();
        discardPile = new Stack<>();
        currentPlayerIndex = 0;
        direction = 1;
    }

    // Add player
    public void addPlayer(Player player) {
        players.add(player);
    }

    // Start game
    public void startGame() {
        deck.loadFromFile("/data/cards.csv");
        deck.shuffle();

        // Deal 7 cards to each player
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.drawCard(deck);
            }
        }

        // Start discard pile
        Card firstCard = deck.drawCard();

        while (firstCard != null && isActionCard(firstCard)) {
            deck.drawCard();
            firstCard = deck.drawCard();
        }

        if (firstCard != null) {
            discardPile.push(firstCard);
        }
    }

    // Get current player
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    // Move to next turn
    public void nextTurn() {
        currentPlayerIndex =
                (currentPlayerIndex + direction + players.size()) % players.size();
    }

    // Play one bot turn
    public void playTurn() {
        Player player = getCurrentPlayer();
        Card topCard = getTopCard();

        System.out.println(player.getName() + "'s turn. Top card: " + topCard);

        if (player.hasValidMove(topCard)) {
            Card played = player.playCard(topCard);

            if (played != null) {
                discardPile.push(played);
                System.out.println(player.getName() + " played: " + played);

                applyActionCard(played);
            }
        } else {
            System.out.println(player.getName() + " has no valid move. Drawing card...");
            player.drawCard(deck);
            nextTurn();
        }
    }

    // Apply action card effects
    public void applyActionCard(Card card) {
        String value = card.getValue().toLowerCase();

        if (value.equals("skip")) {
            // Skip the next player
            nextTurn();
            nextTurn();

        } else if (value.equals("reverse")) {
            // Change turn direction
            reverseDirection();
            nextTurn();

        } else if (value.equals("draw2")) {
            // Next player draws 2 cards and loses their turn
            nextTurn();

            Player affectedPlayer = getCurrentPlayer();
            affectedPlayer.drawCard(deck);
            affectedPlayer.drawCard(deck);

            nextTurn();

        } else {
            // Normal card
            nextTurn();
        }
    }

    // Reverse turn direction
    public void reverseDirection() {
        direction *= -1;
    }

    // Check if a card is an action card
    private boolean isActionCard(Card card) {
        String value = card.getValue().toLowerCase();

        return value.equals("skip")
                || value.equals("reverse")
                || value.equals("draw2");
    }

    // Check winner
    public Player checkWinner() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return player;
            }
        }

        return null;
    }

    // Get deck
    public Deck getDeck() {
        return deck;
    }

    // Get top card
    public Card getTopCard() {
        return discardPile.peek();
    }

    // Get discard pile
    public Stack<Card> getDiscardPile() {
        return discardPile;
    }

    // Get all players
    public ArrayList<Player> getPlayers() {
        return players;
    }

    // Get direction
    public int getDirection() {
        return direction;
    }
}
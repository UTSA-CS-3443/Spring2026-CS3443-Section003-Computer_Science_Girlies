package com.example.uno.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameController {

    private List<String> deck;
    private List<String> playerHand;
    private String topCard;
    private int currentPlayerIndex;
    private boolean gameStarted;

    public GameController() {
        deck = new ArrayList<>();
        playerHand = new ArrayList<>();
        currentPlayerIndex = 0;
        gameStarted = false;
        initializeDeck();
    }

    private void initializeDeck() {
        String[] colors = {"Red", "Blue", "Green", "Yellow"};
        String[] values = {"0","1","2","3","4","5","6","7","8","9","Skip","Reverse","Draw2"};

        for (String color : colors) {
            for (String value : values) {
                deck.add(color + " " + value);
            }
        }

        Collections.shuffle(deck);
    }

    public void startGame() {
        gameStarted = true;
        dealCards();
        topCard = drawCard();
    }

    private void dealCards() {
        for (int i = 0; i < 7; i++) {
            playerHand.add(drawCard());
        }
    }

    public String drawCard() {
        if (deck.isEmpty()) return null;
        return deck.remove(0);
    }

    public boolean playCard(String card) {
        if (!playerHand.contains(card)) return false;

        if (isValidMove(card)) {
            playerHand.remove(card);
            topCard = card;
            nextTurn();
            return true;
        }

        return false;
    }

    private boolean isValidMove(String card) {
        String[] played = card.split(" ");
        String[] top = topCard.split(" ");

        return played[0].equals(top[0]) || played[1].equals(top[1]);
    }

    private void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % 4;
    }

    public List<String> getPlayerHand() {
        return playerHand;
    }

    public String getTopCard() {
        return topCard;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }
}

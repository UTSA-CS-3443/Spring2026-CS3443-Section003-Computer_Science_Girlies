package com.example.uno.controllers;

import java.util.ArrayList;
import java.util.List;

public class LobbyController {

    private List<String> players;
    private int maxPlayers;
    private boolean gameReady;

    public LobbyController() {
        players = new ArrayList<>();
        maxPlayers = 4;
        gameReady = false;
    }

    public boolean addPlayer(String username) {
        if (players.size() >= maxPlayers) {
            return false;
        }

        if (!players.contains(username)) {
            players.add(username);
            checkIfReady();
            return true;
        }

        return false;
    }

    public void removePlayer(String username) {
        players.remove(username);
        gameReady = false;
    }

    private void checkIfReady() {
        if (players.size() >= 2) {
            gameReady = true;
        }
    }

    public boolean isGameReady() {
        return gameReady;
    }

    public List<String> getPlayers() {
        return players;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public boolean startGame() {
        return gameReady;
    }
}

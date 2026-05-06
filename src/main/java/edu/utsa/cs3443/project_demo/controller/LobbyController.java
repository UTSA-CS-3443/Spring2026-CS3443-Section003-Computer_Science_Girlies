package edu.utsa.cs3443.project_demo.controller;

import java.io.IOException;

import edu.utsa.cs3443.project_demo.model.Lobby;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LobbyController {

    @FXML private TextField playerNameField;
    @FXML private TextArea playerListArea;

    private Lobby lobby;

    @FXML
    public void initialize() {
        lobby = new Lobby(4);

        // Lobby starts with only Player 1
        lobby.addPlayer("Player 1");

        updatePlayerList();
    }

    @FXML
    private void handleInvite(ActionEvent event) {
        if (lobby.getPlayerCount() < 4) {
            String playerName = "Player " + (lobby.getPlayerCount() + 1);
            lobby.addPlayer(playerName);
            updatePlayerList();

            if (playerNameField != null) {
                playerNameField.clear();
            }
        } else {
            System.out.println("Lobby is full. Max 4 players.");
        }
    }

    @FXML
    private void handleStartGame(ActionEvent event) {
        int humanPlayerCount = lobby.getPlayerCount();

        // Tell GameController how many real players were added in the lobby
        GameController.setHumanPlayerCount(humanPlayerCount);

        // Fill empty player slots with bots only for display in the lobby
        while (lobby.getPlayerCount() < 4) {
            lobby.addPlayer("Bot " + (lobby.getPlayerCount() + 1));
        }

        updatePlayerList();

        if (lobby.startGame()) {
            switchScene(event, "/layouts/game.fxml");
        }
    }

    @FXML
    private void handleLeaveLobby(ActionEvent event) {
        switchScene(event, "/layouts/main_menu.fxml");
    }

    private void updatePlayerList() {
        if (playerListArea != null) {
            playerListArea.clear();

            for (String player : lobby.getPlayers()) {
                playerListArea.appendText(player + "\n");
            }
        }
    }

    private void switchScene(ActionEvent event, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root, 900, 600);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.setMaximized(false);
            stage.setWidth(900);
            stage.setHeight(600);
            stage.centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
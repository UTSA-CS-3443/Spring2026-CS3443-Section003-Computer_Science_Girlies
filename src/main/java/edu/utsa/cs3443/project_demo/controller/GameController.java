package edu.utsa.cs3443.project_demo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import edu.utsa.cs3443.project_demo.model.Card;
import edu.utsa.cs3443.project_demo.model.Game;
import edu.utsa.cs3443.project_demo.model.Player;

import java.io.IOException;
import java.io.InputStream;

public class GameController {

    @FXML private Text turnLabel;
    @FXML private Button topCardDisplay;
    @FXML private HBox handContainer;
    @FXML private HBox player2HandContainer;
    @FXML private HBox player3HandContainer;
    @FXML private HBox player4HandContainer;
    @FXML private Button drawButton;
    @FXML private Button continueButton;

    private Game game;

    @FXML
    public void initialize() {
        game = new Game();

        game.addPlayer(new Player("Player 1", false));
        game.addPlayer(new Player("Bot 2", true));
        game.addPlayer(new Player("Bot 3", true));
        game.addPlayer(new Player("Bot 4", true));

        game.startGame();

        if (continueButton != null) {
            continueButton.setVisible(false);
        }

        updateUI();
    }

    private void runBotTurns() {
        while (game.getCurrentPlayer().isBot() && game.checkWinner() == null) {
            game.playTurn();
        }

        updateUI();
    }

    @FXML
    private void handleDrawCard() {
        Player currentPlayer = game.getCurrentPlayer();

        if (currentPlayer.isBot()) {
            return;
        }

        currentPlayer.drawCard(game.getDeck());
        game.nextTurn();

        runBotTurns();
    }

    private void handlePlayCard(Card card) {
        Player currentPlayer = game.getCurrentPlayer();
        Card topCard = game.getTopCard();

        if (currentPlayer.isBot()) {
            return;
        }

        if (card.matches(topCard)) {
            currentPlayer.getHand().remove(card);
            game.getDiscardPile().push(card);

            game.applyActionCard(card);

            runBotTurns();
        } else {
            System.out.println("Invalid move: " + card + " does not match " + topCard);
        }
    }

    private void updateUI() {
        Player currentPlayer = game.getCurrentPlayer();

        turnLabel.setText(currentPlayer.getName() + "'s Turn");

        updateTopCard();
        updatePlayerHand(game.getPlayers().get(0));
        updateOtherPlayerHands();

        boolean isHumanTurn = !currentPlayer.isBot();
        drawButton.setDisable(!isHumanTurn);
        handContainer.setDisable(!isHumanTurn);

        Player winner = game.checkWinner();

        if (winner != null) {
            turnLabel.setText(winner.getName() + " wins!");
            drawButton.setDisable(true);
            handContainer.setDisable(true);

            if (continueButton != null) {
                continueButton.setVisible(true);
            }
        }
    }

    private void updateTopCard() {
        Card topCard = game.getTopCard();

        ImageView imageView = createCardImageView(topCard, 90, 130);

        topCardDisplay.setText("");
        topCardDisplay.setGraphic(imageView);
        topCardDisplay.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
    }

    private void updatePlayerHand(Player player) {
        handContainer.getChildren().clear();

        for (Card card : player.getHand()) {
            Button cardButton = createCardButton(card);
            handContainer.getChildren().add(cardButton);
        }
    }

    private void updateOtherPlayerHands() {
        if (game.getPlayers().size() > 1) {
            updateBackCardHand(player2HandContainer, game.getPlayers().get(1));
        }

        if (game.getPlayers().size() > 2) {
            updateBackCardHand(player3HandContainer, game.getPlayers().get(2));
        }

        if (game.getPlayers().size() > 3) {
            updateBackCardHand(player4HandContainer, game.getPlayers().get(3));
        }
    }

    private void updateBackCardHand(HBox container, Player player) {
        if (container == null || player == null) {
            return;
        }

        container.getChildren().clear();

        for (int i = 0; i < player.getHand().size(); i++) {
            Button backCard = new Button();

            backCard.setPrefSize(55, 80);
            backCard.setMinSize(55, 80);
            backCard.setMaxSize(55, 80);

            backCard.setStyle(
                    "-fx-background-color: black;" +
                            "-fx-background-radius: 12;" +
                            "-fx-border-color: white;" +
                            "-fx-border-width: 2;" +
                            "-fx-border-radius: 12;"
            );

            container.getChildren().add(backCard);
        }
    }

    private Button createCardButton(Card card) {
        Button cardButton = new Button();

        ImageView imageView = createCardImageView(card, 70, 100);

        cardButton.setGraphic(imageView);
        cardButton.setPrefSize(70, 100);
        cardButton.setMinSize(70, 100);
        cardButton.setMaxSize(70, 100);
        cardButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        cardButton.setOnMouseEntered(event -> {
            cardButton.setTranslateY(-8);
            cardButton.setScaleX(1.05);
            cardButton.setScaleY(1.05);
        });

        cardButton.setOnMouseExited(event -> {
            cardButton.setTranslateY(0);
            cardButton.setScaleX(1.0);
            cardButton.setScaleY(1.0);
        });

        cardButton.setOnAction(event -> handlePlayCard(card));

        return cardButton;
    }

    private ImageView createCardImageView(Card card, double width, double height) {
        String imagePath = "/images/cards/card_"
                + card.getColor().toLowerCase()
                + "_"
                + card.getValue().toLowerCase()
                + ".png";

        InputStream stream = getClass().getResourceAsStream(imagePath);

        if (stream == null) {
            System.out.println("Missing image: " + imagePath);

            ImageView fallback = new ImageView();
            fallback.setFitWidth(width);
            fallback.setFitHeight(height);
            return fallback;
        }

        Image image = new Image(stream);
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);

        return imageView;
    }

    @FXML
    private void handleContinue(ActionEvent event) {
        Player winner = game.checkWinner();

        if (winner != null) {
            int winnerIndex = game.getPlayers().indexOf(winner);
            GameOverController.setWinnerIndex(winnerIndex);
        }

        switchScene(event, "/layouts/game_over.fxml");
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

    @FXML
    private void handlePlayGame(ActionEvent event) {
        switchScene(event, "/layouts/lobby.fxml");
    }

    @FXML
    private void handleSettings(ActionEvent event) {
        switchScene(event, "/layouts/settings.fxml");
    }

    @FXML
    private void handleHowToPlay(ActionEvent event) {
        switchScene(event, "/layouts/how_to_play.fxml");
    }
}
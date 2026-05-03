package edu.utsa.cs3443.project_demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class GameOverController {

    @FXML private ImageView player1ResultImage;
    @FXML private ImageView player2ResultImage;
    @FXML private ImageView player3ResultImage;
    @FXML private ImageView player4ResultImage;

    private static int winnerIndex = 0;

    public static void setWinnerIndex(int index) {
        winnerIndex = index;
    }

    @FXML
    public void initialize() {
        setResultImage(player1ResultImage, 0);
        setResultImage(player2ResultImage, 1);
        setResultImage(player3ResultImage, 2);
        setResultImage(player4ResultImage, 3);
    }

    private void setResultImage(ImageView imageView, int playerIndex) {
        String imagePath;

        if (playerIndex == winnerIndex) {
            imagePath = "/images/you_win.png";
        } else {
            imagePath = "/images/you_lose.png";
        }

        InputStream stream = getClass().getResourceAsStream(imagePath);

        if (stream == null) {
            System.out.println("Missing result image: " + imagePath);
            return;
        }

        imageView.setImage(new Image(stream));
    }

    @FXML
    private void handleBackToMenu(ActionEvent event) {
        switchScene(event, "/layouts/main_menu.fxml");
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
package edu.utsa.cs3443.project_demo.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private void handlePlayGame(ActionEvent event) {
        switchScene(event, "/layouts/lobby.fxml");
    }

    @FXML
    private void handleHowToPlay(ActionEvent event) {
        switchScene(event, "/layouts/how_to_play.fxml");
    }

    @FXML
    private void handleSettings(ActionEvent event) {
        switchScene(event, "/layouts/settings.fxml");
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
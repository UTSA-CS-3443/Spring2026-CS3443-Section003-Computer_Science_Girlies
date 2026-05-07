package edu.utsa.cs3443.project_demo.controller;

import java.io.IOException;

import edu.utsa.cs3443.project_demo.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HowToPlayController {

    @FXML
    private void handleBack(ActionEvent event) {
        switchScene(event, "/layouts/main_menu.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            StackPane wrapper = new StackPane(root);
            wrapper.setStyle("-fx-background-color: #9e9e9e;");

            Scene scene = new Scene(wrapper, stage.getWidth(), stage.getHeight());

            stage.setScene(scene);

            if (MainApp.currentDisplayMode.equalsIgnoreCase("Fullscreen")) {
                stage.setFullScreen(true);
                stage.setMaximized(false);

            } else if (MainApp.currentDisplayMode.equalsIgnoreCase("Maximized")) {
                stage.setFullScreen(false);
                stage.setMaximized(true);

            } else {
                stage.setFullScreen(false);
                stage.setMaximized(false);

                if (MainApp.currentResolution.contains("x")) {
                    String[] parts = MainApp.currentResolution.toLowerCase().split("x");

                    double width = Double.parseDouble(parts[0].trim());
                    double height = Double.parseDouble(parts[1].trim());

                    stage.setWidth(width);
                    stage.setHeight(height);
                }
            }

            stage.centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
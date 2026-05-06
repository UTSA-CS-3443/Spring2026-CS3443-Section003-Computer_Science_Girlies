package edu.utsa.cs3443.project_demo.controller;

import java.io.*;
import java.util.*;

import edu.utsa.cs3443.project_demo.model.Setting;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class SettingsController {

    private Map<String, Setting> settingsMap = new HashMap<>();

    @FXML private ComboBox<String> languageBox;
    @FXML private ComboBox<String> controlsBox;
    @FXML private ComboBox<String> appearanceBox;
    @FXML private ComboBox<String> resolutionBox;
    @FXML private ComboBox<String> displayModeBox;

    @FXML private CheckBox musicCheck;
    @FXML private CheckBox sfxCheck;
    @FXML private CheckBox colorblindCheck;

    @FXML
    public void initialize() {
        loadSettings();

        setupComboBox(languageBox, "language");
        setupComboBox(controlsBox, "controls");
        setupComboBox(appearanceBox, "appearance");
        setupComboBox(resolutionBox, "resolution");
        setupComboBox(displayModeBox, "displayMode");

        setupCheckBox(musicCheck, "music");
        setupCheckBox(sfxCheck, "soundEffects");
        setupCheckBox(colorblindCheck, "colorblindMode");
    }

    public void loadSettings() {
        InputStream stream = getClass().getResourceAsStream("/data/settings.csv");

        if (stream == null) {
            System.out.println("settings.csv not found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);

                if (parts.length >= 4) {
                    String key = parts[0].trim();
                    String type = parts[1].trim();
                    String value = parts[2].trim();
                    List<String> options = Arrays.asList(parts[3].split(";"));

                    settingsMap.put(key, new Setting(key, type, value, options));
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading settings: " + e.getMessage());
        }
    }

    private void setupComboBox(ComboBox<String> box, String key) {
        Setting setting = settingsMap.get(key);

        if (setting == null) {
            return;
        }

        box.getItems().clear();
        box.getItems().addAll(setting.getOptions());
        box.setValue(setting.getValue());
    }

    private void setupCheckBox(CheckBox checkBox, String key) {
        Setting setting = settingsMap.get(key);

        if (setting == null) {
            return;
        }

        checkBox.setSelected(setting.getValue().equalsIgnoreCase("ON"));
    }

    @FXML
    public void handleSave(ActionEvent event) {
        updateSettings();
        saveToCSV();
        applySettings(event);
        System.out.println("Settings saved and applied.");
    }

    private void applySettings(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String appearance = settingsMap.get("appearance").getValue();
        String resolution = settingsMap.get("resolution").getValue();
        String displayMode = settingsMap.get("displayMode").getValue();

        applyAppearance(stage.getScene(), appearance);
        applyResolution(stage, resolution);
        applyDisplayMode(stage, displayMode);
    }

    private void applyAppearance(Scene scene, String appearance) {
        Parent root = scene.getRoot();

        if (appearance.equalsIgnoreCase("Dark")) {
            root.setStyle("-fx-background-color: #1e1e1e;");
        } else {
            root.setStyle("-fx-background-color: #f5f5f5;");
        }
    }

    private void applyResolution(Stage stage, String resolution) {
        if (resolution == null || !resolution.contains("x")) {
            return;
        }

        String[] parts = resolution.toLowerCase().split("x");

        try {
            double width = Double.parseDouble(parts[0].trim());
            double height = Double.parseDouble(parts[1].trim());

            stage.setWidth(width);
            stage.setHeight(height);
            stage.centerOnScreen();

        } catch (NumberFormatException e) {
            System.out.println("Invalid resolution: " + resolution);
        }
    }

    private void applyDisplayMode(Stage stage, String displayMode) {
        if (displayMode == null) {
            return;
        }

        if (displayMode.equalsIgnoreCase("Fullscreen")) {
            stage.setFullScreen(true);
            stage.setMaximized(false);
        } else if (displayMode.equalsIgnoreCase("Windowed")) {
            stage.setFullScreen(false);
            stage.setMaximized(false);
        } else if (displayMode.equalsIgnoreCase("Maximized")) {
            stage.setFullScreen(false);
            stage.setMaximized(true);
        }
    }

    public void updateSettings() {
        settingsMap.get("language").setValue(languageBox.getValue());
        settingsMap.get("controls").setValue(controlsBox.getValue());
        settingsMap.get("appearance").setValue(appearanceBox.getValue());
        settingsMap.get("resolution").setValue(resolutionBox.getValue());
        settingsMap.get("displayMode").setValue(displayModeBox.getValue());

        settingsMap.get("music").setValue(musicCheck.isSelected() ? "ON" : "OFF");
        settingsMap.get("soundEffects").setValue(sfxCheck.isSelected() ? "ON" : "OFF");
        settingsMap.get("colorblindMode").setValue(colorblindCheck.isSelected() ? "ON" : "OFF");
    }

    public void saveToCSV() {
        String filePath = "settings.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Setting,Type,Value,Options");
            writer.newLine();

            for (Setting setting : settingsMap.values()) {
                String options = String.join(";", setting.getOptions());

                writer.write(setting.getKey() + ","
                        + setting.getType() + ","
                        + setting.getValue() + ","
                        + options);

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving settings: " + e.getMessage());
        }
    }

    @FXML
    public void handleBack(ActionEvent event) {
        switchScene(event, "/layouts/main_menu.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());

            stage.setScene(scene);
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
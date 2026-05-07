package edu.utsa.cs3443.project_demo.controller;

import java.io.*;
import java.util.*;

import edu.utsa.cs3443.project_demo.MainApp;
import edu.utsa.cs3443.project_demo.model.Setting;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingsController {

    private Map<String, Setting> settingsMap = new HashMap<>();

    @FXML private StackPane settingsRoot;
    @FXML private ImageView backgroundImage;

    @FXML private Text settingsTitle;

    @FXML private Label languageLabel;
    @FXML private Label controlsLabel;
    @FXML private Label appearanceLabel;
    @FXML private Label resolutionLabel;
    @FXML private Label displayModeLabel;

    @FXML private Button backButton;
    @FXML private Button saveButton;

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

        applyLanguage(getSettingValue("language"));
        applyAppearance(getSettingValue("appearance"),
                getSettingValue("colorblindMode").equalsIgnoreCase("ON"));
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

        if (setting == null || box == null) {
            return;
        }

        box.getItems().clear();
        box.getItems().addAll(setting.getOptions());
        box.setValue(setting.getValue());
    }

    private void setupCheckBox(CheckBox checkBox, String key) {
        Setting setting = settingsMap.get(key);

        if (setting == null || checkBox == null) {
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

        String language = getSettingValue("language");
        String appearance = getSettingValue("appearance");
        String resolution = getSettingValue("resolution");
        String displayMode = getSettingValue("displayMode");

        boolean musicOn = getSettingValue("music").equalsIgnoreCase("ON");
        boolean colorblindOn = getSettingValue("colorblindMode").equalsIgnoreCase("ON");

        applyLanguage(language);
        applyAppearance(appearance, colorblindOn);
        applyResolution(stage, resolution);
        applyDisplayMode(stage, displayMode);
        MainApp.setMusicEnabled(musicOn);
    }

    private void applyLanguage(String language) {
        if (language == null) {
            return;
        }

        if (language.equalsIgnoreCase("Spanish")) {
            settingsTitle.setText("Configuración");
            languageLabel.setText("Idioma");
            controlsLabel.setText("Controles");
            appearanceLabel.setText("Apariencia");
            resolutionLabel.setText("Resolución");
            displayModeLabel.setText("Modo de Pantalla");

            musicCheck.setText("Música");
            sfxCheck.setText("Efectos de Sonido");
            colorblindCheck.setText("Modo Daltónico");

            backButton.setText("Volver al Menú");
            saveButton.setText("Guardar");

        } else if (language.equalsIgnoreCase("French")) {
            settingsTitle.setText("Paramètres");
            languageLabel.setText("Langue");
            controlsLabel.setText("Commandes");
            appearanceLabel.setText("Apparence");
            resolutionLabel.setText("Résolution");
            displayModeLabel.setText("Mode d'affichage");

            musicCheck.setText("Musique");
            sfxCheck.setText("Effets sonores");
            colorblindCheck.setText("Mode daltonien");

            backButton.setText("Retour au menu");
            saveButton.setText("Enregistrer");

        } else {
            settingsTitle.setText("Settings");
            languageLabel.setText("Language");
            controlsLabel.setText("Controls");
            appearanceLabel.setText("Appearance");
            resolutionLabel.setText("Resolution");
            displayModeLabel.setText("Display Mode");

            musicCheck.setText("Music");
            sfxCheck.setText("Sound Effects");
            colorblindCheck.setText("Colorblind Mode");

            backButton.setText("Back to Main Menu");
            saveButton.setText("Save Settings");
        }
    }

    private void applyAppearance(String appearance, boolean colorblindOn) {
        if (settingsRoot == null) {
            return;
        }

        String backgroundColor;
        String textColor;
        String buttonColor;

        if (appearance.equalsIgnoreCase("Dark")) {
            backgroundColor = "#1e1e1e";
            textColor = "white";
            buttonColor = "#2f2f2f";
        } else {
            backgroundColor = "#4fc3e8";
            textColor = "white";
            buttonColor = "white";
        }

        if (colorblindOn) {
            backgroundColor = "#0072B2";
            buttonColor = "#E69F00";
            textColor = "white";
        }

        settingsRoot.setStyle("-fx-background-color: " + backgroundColor + ";");

        settingsTitle.setStyle("-fx-font-size: 28px;"
                + "-fx-fill: " + textColor + ";"
                + "-fx-font-family: 'Arial Rounded MT Bold';");

        styleLabel(languageLabel, textColor);
        styleLabel(controlsLabel, textColor);
        styleLabel(appearanceLabel, textColor);
        styleLabel(resolutionLabel, textColor);
        styleLabel(displayModeLabel, textColor);

        styleCheckBox(musicCheck, textColor);
        styleCheckBox(sfxCheck, textColor);
        styleCheckBox(colorblindCheck, textColor);

        styleButton(backButton, buttonColor);
        styleButton(saveButton, buttonColor);
    }

    private void styleLabel(Label label, String textColor) {
        if (label != null) {
            label.setStyle("-fx-text-fill: " + textColor + ";"
                    + "-fx-font-size: 14px;"
                    + "-fx-font-weight: bold;");
        }
    }

    private void styleCheckBox(CheckBox checkBox, String textColor) {
        if (checkBox != null) {
            checkBox.setStyle("-fx-text-fill: " + textColor + ";"
                    + "-fx-font-size: 16px;");
        }
    }

    private void styleButton(Button button, String buttonColor) {
        if (button != null) {
            button.setStyle("-fx-font-size: 14px;"
                    + "-fx-background-radius: 12;"
                    + "-fx-background-color: " + buttonColor + ";"
                    + "-fx-text-fill: black;"
                    + "-fx-font-weight: bold;");
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

            stage.setFullScreen(false);
            stage.setMaximized(false);
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

    private String getSettingValue(String key) {
        Setting setting = settingsMap.get(key);

        if (setting == null || setting.getValue() == null) {
            return "";
        }

        return setting.getValue();
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
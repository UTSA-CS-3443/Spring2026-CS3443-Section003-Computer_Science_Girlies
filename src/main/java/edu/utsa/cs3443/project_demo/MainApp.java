package edu.utsa.cs3443.project_demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static MediaPlayer mediaPlayer;

    @Override
    public void start(Stage stage) throws Exception {

        // Load FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/main_menu.fxml"));
        Scene scene = new Scene(loader.load(), 900, 600);

        loadBackgroundMusic();

        // Window settings
        stage.setTitle("Color & Number Match Card Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    private void loadBackgroundMusic() {
        try {
            // Load background music
            String musicPath = getClass().getResource("/music/game_music.mp3").toExternalForm();

            Media media = new Media(musicPath);
            mediaPlayer = new MediaPlayer(media);

            // Music settings
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.2);
            mediaPlayer.play();

        } catch (Exception e) {
            System.out.println("Background music could not be loaded.");
        }
    }

    public static void setMusicEnabled(boolean enabled) {
        if (mediaPlayer == null) {
            return;
        }

        if (enabled) {
            mediaPlayer.play();
        } else {
            mediaPlayer.pause();
        }
    }

    public static void setMusicVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }

    @Override
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
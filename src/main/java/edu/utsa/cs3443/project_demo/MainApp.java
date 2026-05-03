package edu.utsa.cs3443.project_demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MainApp extends Application {

    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage stage) throws Exception {

        // Load FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/main_menu.fxml"));
        Scene scene = new Scene(loader.load());

        // Load background music
        String musicPath = getClass().getResource("/music/game_music.mp3").toExternalForm();
        Media media = new Media(musicPath);
        mediaPlayer = new MediaPlayer(media);

        // Music settings
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // loops forever
        mediaPlayer.setVolume(0.1); // volume (0.0 to 1.0)
        mediaPlayer.play();

        // Window settings
        stage.setTitle("Color & Number Match Card Game");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        // Stop music when app closes
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
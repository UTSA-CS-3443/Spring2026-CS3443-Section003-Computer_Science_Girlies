import java.io.IOException;
import javafx.scene.Node;
import org.w3c.dom.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MenuController {
    public void handlePlayGame(){//goToGame()
        /*Load your game screen FXML
        Switch the current scene to the game */
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/game.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void handleHowToSettings(){//goToHowToPlay()
        /*Load your how-to-play screen
        Switch scenes */
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/how_to_play.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void handleSettings(){//goToSettings()
        /*Load your settings.fxml
        Switch scenes → this connects to your SettingsController */
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/settings.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

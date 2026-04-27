import java.io.IOException;
import javafx.scene.Node;
import org.w3c.dom.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SettingsController{
    public String filePath = "settings.csv";
    @fxml private ComboBox<String> launguageBox;
    @fxml private ComboBox<String> controlsBox;
    @fxml private ComboBox<String> appearanceBox;
    @fxml private ComboBox<String> resolutionBox;
    @fxml private ComboBox<String> displayModeBox;

    @fxml private CheckBox musicCheck;
    @fxml private CheckBox sfxCheck;
    @fxml private CheckBox colorBlindCheck;
    public void initailize(){
        /* Calls the method that loads settings from the CSV
        Populates all UI elements (ComboBoxes + CheckBoxes) using the loaded data
        Ensures the UI reflects the current saved values */
        
        loadSettings();
        setupComboBox(languageBox, "language");
        setupComboBox(controlsBox, "controls");
        setupComboBox(appearanceBox, "appearance");
        setupComboBox(resolutionBox, "resolution");
        setupComboBox(displayModeBox, "displayMode");

        setupCheckBox(musicCheck, "music");
        setupCheckBox(sfxCheck, "SoundFX");
        setupCheckBox(colorBlindCheck, "colorBlind");

    }
    public void loadSettings(){
        /* Opens and reads settings.csv
        Skips the header row
        Parses each line into a structured setting object
        Stores all settings in a collection (like a map using keys such as "language", "music", etc.) */
        String filePath = "settings.csv";
        try (BufferedReader settings = new BufferedReader(filePath)){
            String file;
            settings.readLine();

            while(file = settings.readLine() != null){
                string[] column = line.split(",");
            }



        } catch (IOException e){

        }

    }
    private void setupComboBox(ComboBox<String> box, String settingKey){
        /* Takes a ComboBox and a setting key (like "language")
        Loads the available options into the dropdown
        Sets the default/current value */
    }
    private void setupCheckBox(checkBox cowboy, String settingKey){
        /* Takes a CheckBox and a setting key
        Converts "ON" / "OFF" into checked/unchecked
        Applies the correct state to the UI */
    }
    public void handleSave(){
        /* Triggered when the user clicks Save
        Reads current values from all UI elements
        Updates the in-memory settings data
        Calls a method to persist (write) everything back to the CSV */
    }
    public void saveToCSV(String filePath){
        
        /* Writes all settings back into settings.csv
        Preserves the correct format:
        Setting name
        Type
        Current value
        Options
        Overwrites or updates the file with new value */
        try {

        } catch(IOException e){

        }
    }
    public void handleBack(){
        /*Triggered when the user clicks Back
        Switches the scene to the previous screen (e.g., main menu)
        Does not modify settings unless Save was clicked */
    }
    public void updateSettings(){
        
    }
}

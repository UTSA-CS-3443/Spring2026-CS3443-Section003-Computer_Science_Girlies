import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Node;
import org.w3c.dom.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SettingsController{
    private Map<String, Setting> settingsMap = new HashMap<>();


    @FXML private ComboBox<String> launguageBox;
    @FXML private ComboBox<String> controlsBox;
    @FXML private ComboBox<String> appearanceBox;
    @FXML private ComboBox<String> resolutionBox;
    @FXML private ComboBox<String> displayModeBox;

    @FXML private CheckBox musicCheck;
    @FXML private CheckBox sfxCheck;
    @FXML private CheckBox colorblindCheck;

    @FXML
    public void initialize(){
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
        setupCheckBox(sfxCheck, "SoundEffects");
        setupCheckBox(colorblindCheck, "colorblindMode");

    }
    public void loadSettings(){
        /* Opens and reads settings.csv
        Skips the header row
        Parses each line into a structured setting object
        Stores all settings in a collection (like a map using keys such as "language", "music", etc.) */
        String filePath = "settings.csv";

        try (BufferedReader settings = new BufferedReader(new FileReader(filePath))){
            
            settings.readLine();//
            String file;

            while((file = settings.readLine()) != null){
                String[] parts = file.split(",");

                String key = parts[0];
                String type = parts[1];
                String value = parts[2];
                List<String> options = Arrays.asList(parts[3].split(";"));
                
                settingsMap.put(key, new Setting(key, type, value, options));
            }



        } catch (IOException e){

        }

    }
    private void setupComboBox(ComboBox<String> box, String key){
        /* Takes a ComboBox and a setting key (like "language")
        Loads the available options into the dropdown
        Sets the default/current value */
        Settings s = settingsMap.get(key);
        if (s == null) return;
        
        box.getItems().addAll(s.getOptions());
        box.setValue(s.getValue());


    }
    private void setupCheckBox( checkBox cowboy, String key){
        /* Takes a CheckBox and a setting key
        Converts "ON" / "OFF" into checked/unchecked
        Applies the correct state to the UI */

        Setting s = settingsMap.get(key);
        if (s == null) return;

        cowboy.setSelected(s.getValue().equalsIgnoreCase("ON"));
    }

    @FXML
    public void handleSave(){
        /* Triggered when the user clicks Save
        Reads current values from all UI elements
        Updates the in-memory settings data
        Calls a method to persist (write) everything back to the CSV */
        settingsMap.get("language").setValue(languageBox.getValue());
        settingsMap.get("controls").setValue(controlsBox.getValue());
        settingsMap.get("appearance").setValue(appearanceBox.getValue());
        settingsMap.get("resolution").setValue(resolutionBox.getValue());
        settingsMap.get("displayMode").setValue(displayModeBox.getValue());
    
        settingsMap.get("music").setValue(musicCheck.isSelected() ? "ON" : "OFF");
        settingsMap.get("soundEffects").setValue(sfxCheck.isSelected() ? "ON" : "OFF");
        settingsMap.get("colorblindMode").setValue(colorblindCheck.isSelected() ? "ON" : "OFF");
        
        saveToCSV();
    }
    public void saveToCSV(String filePath){
        
        /* Writes all settings back into settings.csv
        Preserves the correct format:
        Setting name
        Type
        Current value
        Options
        Overwrites or updates the file with new value */
        try (BufferedReader input = new BufferedReader(new FileReader("settings.csv"))){
            input.write("Settings,Type,Value,Options\n");

            for (Settings s : SettingsMap.values()) {
                String options = String.join(";", s.getOptions());

                input.write(s.getKey(), + "," + s.getType(), "," + s.getValue() + "," + options + "\n");
            }
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

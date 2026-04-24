package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HowToPlayController {

    @FXML
    private void handleBack(ActionEvent event) {
        System.out.println("Returning to main menu...");
        // later: switch scene back to main_menu.fxml
    }
}

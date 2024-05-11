package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class menuController {

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button musicPlayerMenuButton;

    @FXML
    void switchToMusicPlayerScreen(MouseEvent event) {
        try {
            App.setRoot("musicPlayer");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

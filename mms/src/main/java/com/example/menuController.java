package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class menuController {

    @FXML
    private Button musicPlayerMenuButton;

    @FXML
    private Button manageMediaSceneButton;

    @FXML
    void switchToMusicPlayerScreen(MouseEvent event) throws IOException {
        try {
            App.setRoot("musicPlayer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToManageMediaScene(MouseEvent event) throws IOException{
        try {
            App.setRoot("manageMedia");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToLogin(MouseEvent event) {
        try {
            App.setRoot("login");
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

}

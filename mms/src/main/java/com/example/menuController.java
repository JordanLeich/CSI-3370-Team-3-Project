package com.example;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
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
    private void openGitHubPage(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/JordanLeich/CSI-3370-Team-3-Project/graphs/contributors"));
        } catch (Exception e) {
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

    @FXML
    void quitApplication(MouseEvent event) {
        Platform.exit();
    }

}

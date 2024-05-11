package com.example;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class musicPlayerController {
    private MediaPlayer mediaPlayer;
    private menuController menuController; // Assuming you have an instance of MenuController

    @FXML
    private Button mainMenuButton;

    @FXML
    public void initialize() {
        String audioFilePath = "mms" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "audio" + File.separator + "spongeboy.mp3";
        Media media = new Media(new File(audioFilePath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    // Method to set MenuController
    public void setMenuController(menuController menuController) {
        this.menuController = menuController;
    }

    @FXML
    void pauseButton(ActionEvent event) {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        }
    }

    @FXML
    void playButton(ActionEvent event) {
        if (mediaPlayer != null && (mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED ||
                mediaPlayer.getStatus() == MediaPlayer.Status.STOPPED)) {
            mediaPlayer.play();
        }
    }

    @FXML
    void stopButton(ActionEvent event) {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }
    }

    @FXML
    void goToMainMenu(ActionEvent event) {
        if (menuController != null) {
            menuController.goToMainMenu();
        }
    }
}

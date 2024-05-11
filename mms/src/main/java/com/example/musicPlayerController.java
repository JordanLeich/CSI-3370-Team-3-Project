package com.example;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class musicPlayerController {
    private MediaPlayer mediaPlayer;
    

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button playButton;

    @FXML
    private Button stopButton;

    @FXML
    private Label debuglabel;

    @FXML
    public void initialize() {
        String audioFilePath = "mms" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "audio" + File.separator + "spongeboy.mp3";
        Media media = new Media(new File(audioFilePath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }
    

    @FXML
    void pauseButton(MouseEvent event) {
        debuglabel.setText("pause"+mediaPlayer.getStatus());
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @FXML
    void playButton(MouseEvent event) {
        debuglabel.setText("play"+mediaPlayer.getStatus());
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    @FXML
    void stopButton(MouseEvent event) {
        debuglabel.setText("stop" +mediaPlayer.getStatus());
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
    @FXML
    void goToMainMenu(MouseEvent event) {
        try {
            App.setRoot("menu");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}


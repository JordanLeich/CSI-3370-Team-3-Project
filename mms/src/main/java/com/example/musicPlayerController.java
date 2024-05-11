package com.example;

import java.io.File;

import javafx.collections.ModifiableObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class musicPlayerController {
    MediaPlayer mediaPlayer;
    // Path to the audio file
   // String audioFilePath = "audio\\spongeboy.mp3"; // this path is wrong
   // Media media = new Media(new File(audioFilePath).toURI().toString());

    // Create MediaPlayer
   // public musicPlayerController() {
   //     mediaPlayer = new MediaPlayer(media);
   // }
    
    

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button playButton;

    @FXML
    private Button stopButton;

    @FXML
    void pauseMedia(MouseEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    void playMedia(MouseEvent event) {
        mediaPlayer.play();
    }

    @FXML
    void stopMedia(MouseEvent event) {
        mediaPlayer.stop();
    }



}

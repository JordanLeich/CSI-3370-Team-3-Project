package com.example;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class App extends Application {

    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) {
        // Path to the audio file
        String audioFilePath = "audio\\spongeboy.mp3";
        Media media = new Media(new File(audioFilePath).toURI().toString());

        // Create MediaPlayer
        mediaPlayer = new MediaPlayer(media);

        // Create buttons for play, pause, and stop
        Button playButton = new Button("Play");
        Button pauseButton = new Button("Pause");
        Button stopButton = new Button("Stop");

        // Add event handlers to the buttons
        playButton.setOnAction(event -> mediaPlayer.play());
        pauseButton.setOnAction(event -> mediaPlayer.pause());
        stopButton.setOnAction(event -> mediaPlayer.stop());

        // Arrange buttons horizontally
        HBox buttonBox = new HBox(10); // 10 pixels spacing between buttons
        buttonBox.getChildren().addAll(playButton, pauseButton, stopButton);

        // Create the scene and set it in the stage
        Scene scene = new Scene(buttonBox, 300, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Music Management System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

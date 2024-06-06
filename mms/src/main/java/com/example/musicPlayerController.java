package com.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class musicPlayerController {

    private MediaPlayer mediaPlayer;
    private List<Song> playlist;
    private List<Song> originalPlaylist;
    private int currentIndex = 0;
    private boolean isShuffle = false;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button playButton;

    @FXML
    private Button stopButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button previousButton;

    @FXML
    private Slider progressBar;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Label elapsedTimeLabel;

    @FXML
    private Label totalTimeLabel;

    @FXML
    private ToggleButton repeatButton;

    @FXML
    private ToggleButton shuffleButton;

    @FXML
    private Label songNameLabel;

    @FXML
private Label artistNameLabel;

    @FXML
    void initialize() {
        // Initializes playlist
        playlist = new ArrayList<>();
        playlist.add(new Song("Deeper", "Unknown Artist", "mms\\src\\main\\resources\\audio\\Deeper.mp3"));
        playlist.add(new Song("Chill 1", "Unknown Artist", "mms\\src\\main\\resources\\audio\\Chill 1.mp3"));
        playlist.add(new Song("Chill 2", "Unknown Artist", "mms\\src\\main\\resources\\audio\\Chill 2.mp3"));
        playlist.add(new Song("Chill 3", "Unknown Artist", "mms\\src\\main\\resources\\audio\\Chill 3.mp3"));
        playlist.add(new Song("Spongeboy", "Spongeboy", "mms\\src\\main\\resources\\audio\\spongeboy.mp3"));
        // Adds more songs as needed

        // Stores the original order of the playlist you're in
        originalPlaylist = new ArrayList<>(playlist);

        // Initializes the media player with the first song
        loadMedia(playlist.get(currentIndex));

        // Binds the slider to the media player
        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!progressBar.isValueChanging()) {
                progressBar.setValue(newTime.toSeconds());
                elapsedTimeLabel.setText(formatTime(newTime));
            }
        });

        progressBar.setOnMouseClicked(event -> {
            mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
        });

        // Sets the maximum value of the slider to media duration
        mediaPlayer.setOnReady(() -> {
            progressBar.setMax(mediaPlayer.getMedia().getDuration().toSeconds());
            totalTimeLabel.setText(formatTime(mediaPlayer.getMedia().getDuration()));
        });

        // Initializes volume slider
        volumeSlider.setValue(mediaPlayer.getVolume());
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            mediaPlayer.setVolume(newVal.doubleValue());
        });

        // Handles the repeat button toggle
        repeatButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            mediaPlayer.setCycleCount(isSelected ? MediaPlayer.INDEFINITE : 1);
        });

        // Handles the shuffle button toggle
        shuffleButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                enableShuffle();
            } else {
                disableShuffle();
            }
        });

        // Disables the the previous button initially until a song is played 
        previousButton.setDisable(true);
    }

    private void loadMedia(Song song) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        Media media = new Media(new File(song.getFilePath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(this::playNext);
        mediaPlayer.play();
        // Updates the song name label
        songNameLabel.setText("Playing... " + song.getTitle());
        // Updates the artist name label
        artistNameLabel.setText("Artist: " + song.getArtist());
    }

    private void playNext() {
        currentIndex = (currentIndex + 1) % playlist.size();
        loadMedia(playlist.get(currentIndex));
        previousButton.setDisable(currentIndex == 0);
    }

    @FXML
    void nextButton(MouseEvent event) {
        playNext();
    }

    @FXML
    void previousButton(MouseEvent event) {
        if (currentIndex > 0) {
            currentIndex--;
            loadMedia(playlist.get(currentIndex));
            previousButton.setDisable(currentIndex == 0);
        }
    }

    private void enableShuffle() {
        isShuffle = true;
        Collections.shuffle(playlist);
        currentIndex = 0; // Reset to the first song in the shuffled list
    }

    private void disableShuffle() {
        isShuffle = false;
        playlist = new ArrayList<>(originalPlaylist);
        currentIndex = 0; // Reset to the first song in the original list
    }

    @FXML
    void pauseButton(MouseEvent event) {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        }
    }

    @FXML
    void playButton(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    @FXML
    void stopButton(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    @FXML
    void goToMainMenu(MouseEvent event) {
        try {
            App.setRoot("menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleRepeatToggle(MouseEvent event) {
        mediaPlayer.setCycleCount(repeatButton.isSelected() ? MediaPlayer.INDEFINITE : 1);
    }

    @FXML
    void handleShuffleToggle(MouseEvent event) {
        if (shuffleButton.isSelected()) {
            enableShuffle();
        } else {
            disableShuffle();
        }
    }

    private String formatTime(Duration duration) {
        int minutes = (int) duration.toMinutes();
        int seconds = (int) duration.toSeconds() % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}

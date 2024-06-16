package com.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class musicPlayerController {

    private MediaPlayer mediaPlayer;
    private List<Song> playlist;
    private int currentIndex = 0;

    @FXML
    private ListView<String> playlistView;
    @FXML
    private ListView<String> songView;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Label volumeTextLabel;

    @FXML
    private Button pauseButton;

    @FXML
    private Button playButton;

    @FXML
    private Slider progressBar;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Label elapsedTimeLabel;

    @FXML
    private Label totalTimeLabel;

    @FXML
    private Label songNameLabel;

    @FXML
    private Label artistNameLabel;

    @FXML
    void initialize() {
        playlist = new ArrayList<>();

        if (!playlist.isEmpty()) {
            loadMedia(playlist.get(currentIndex), false);
        } else {
            System.out.println("Playlist is empty, no media to load.");
        }

        playlistView();
        songView();

        if (mediaPlayer != null) {
            mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
                if (!progressBar.isValueChanging()) {
                    progressBar.setValue(newTime.toSeconds());
                    elapsedTimeLabel.setText(formatTime(newTime));
                }
            });

            progressBar.setOnMouseClicked(event -> {
                mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
            });

            mediaPlayer.setOnReady(() -> {
                progressBar.setMax(mediaPlayer.getMedia().getDuration().toSeconds());
                totalTimeLabel.setText(formatTime(mediaPlayer.getMedia().getDuration()));
            });
        }

        volumeSlider.setValue(50); // Set initial volume slider value
        volumeTextLabel.setText("50%"); // Set initial volume text label
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(newVal.doubleValue() / 100.0);
            }
            volumeTextLabel.setText(String.format("%.0f%%", newVal.doubleValue()));
            System.out.println("Volume adjusted to " + newVal);
        });
    }

    private void loadMedia(Song song, boolean autoplay) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        Media media = new Media(new File(song.getFilePath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
        mediaPlayer.setOnEndOfMedia(this::playNext);

        if (autoplay) {
            mediaPlayer.play();
        }

        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!progressBar.isValueChanging()) {
                progressBar.setValue(newTime.toSeconds());
                elapsedTimeLabel.setText(formatTime(newTime));
            }
        });

        mediaPlayer.setOnReady(() -> {
            progressBar.setMax(mediaPlayer.getMedia().getDuration().toSeconds());
            totalTimeLabel.setText(formatTime(mediaPlayer.getMedia().getDuration()));
        });

        songNameLabel.setText("Playing... " + song.getTitle());
        artistNameLabel.setText("Artist: " + song.getArtist());
    }

    private void playNext() {
        currentIndex = (currentIndex + 1) % playlist.size();
        loadMedia(playlist.get(currentIndex), true);
    }

    @FXML
    void switchToNextSong(ActionEvent event) {
        playNext();
    }

    @FXML
    void switchToPreviousSong(ActionEvent event) {
        if (currentIndex > 0) {
            currentIndex--;
            loadMedia(playlist.get(currentIndex), true);
        }
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
    void goToMainMenu(MouseEvent event) {
        try {
            App.setRoot("menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatTime(Duration duration) {
        int minutes = (int) duration.toMinutes();
        int seconds = (int) duration.toSeconds() % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void playlistView() {
        if (playlistView != null) {
            playlistView.getItems().clear();
            User currentUser = CurrentUser.getInstance().getUser();

            if (currentUser != null) {
                for (Playlist playlist : currentUser.getPlaylists()) {
                    playlistView.getItems().add(playlist.getName());
                }
            }

            playlistView.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1) {
                    String selectedPlaylistName = playlistView.getSelectionModel().getSelectedItem();
                    if (selectedPlaylistName != null) {
                        PlayPlaylistfromView(selectedPlaylistName);
                    }
                }
            });
        }
    }

    private void PlayPlaylistfromView(String playlistName) {
        User currentUser = CurrentUser.getInstance().getUser();

        if (currentUser != null) {
            Playlist selectedPlaylist = currentUser.getPlaylists().stream()
                .filter(playlist -> playlist.getName().equals(playlistName))
                .findFirst()
                .orElse(null);

            if (selectedPlaylist != null) {
                playlist = selectedPlaylist.getSongs();
                currentIndex = 0;
                updateSongView();
                if (!playlist.isEmpty()) {
                    loadMedia(playlist.get(currentIndex), false); // Load first song without autoplay
                } else {
                    System.out.println("Selected playlist is empty.");
                }
            } else {
                System.out.println("No playlist is selected to play or playlist is empty.");
            }
        }
    }

    private void updateSongView() {
        if (songView != null) {
            songView.getItems().clear();
            for (Song song : playlist) {
                songView.getItems().add(song.getTitle());
            }
        }
    }

    private void songView() {
        if (songView != null) {
            User currentUser = CurrentUser.getInstance().getUser();

            if (currentUser != null) {
                for (Song song : currentUser.getUploadedSongs()) {
                    songView.getItems().add(song.getTitle());
                }
            }

            songView.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1) {
                    String selectedSongTitle = songView.getSelectionModel().getSelectedItem();
                    if (selectedSongTitle != null) {
                        PlaySongfromView(selectedSongTitle);
                    }
                }
            });
        }
    }

    private void PlaySongfromView(String songName) {
        if (songView != null) {
            User currentUser = CurrentUser.getInstance().getUser();

            Song selectedSong = currentUser.getUploadedSongs().stream()
                .filter(song -> song.getTitle().equals(songName))
                .findFirst()
                .orElse(null);

            if (selectedSong != null) {
                loadMedia(selectedSong, true);
            } else {
                System.out.println("No song is selected to play.");
            }
        }
    }
}

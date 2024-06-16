package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class manageMediaController {

    @FXML
    private ButtonBar ButtonBarID;

    @FXML
    private Button DeleteID;

    @FXML
    private Button AddID;

    @FXML
    private TextField SeachID;

    @FXML
    private ListView<String> playlistView;

    @FXML
    private ListView<String> songView;

    @FXML
    private TextField playlistNameField;

    private PlaylistManager playlistManager;

    private File selectedFile;

    
    public ListView<String> getPlaylistView() {
        return playlistView;
    }

   
    public ListView<String> getSongView() {
        return songView;
    }

    @FXML
    public void initialize() {
        playlistManager = new PlaylistManager();
        updatePlaylistView();
        updateSongView();
    }

    private void updatePlaylistView() {
        playlistView.getItems().clear();
        User currentUser = CurrentUser.getInstance().getUser();
        if (currentUser != null) {
            for (Playlist playlist : currentUser.getPlaylists()) {
                playlistView.getItems().add(playlist.getName());
            }
        }
    }

    private void updateSongView() {
        songView.getItems().clear();
        User currentUser = CurrentUser.getInstance().getUser();
        if (currentUser != null) {
            for (Song song : currentUser.getUploadedSongs()) {
                songView.getItems().add(song.getTitle());
            }
        }
    }

    @FXML
    void handleAddButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Media File");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav", "*.aac", "*.ogg", "*.m4a"),
            new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try {
                Path destinationDirectory = Path.of("mms", "src", "main", "resources", "audio");
                Files.createDirectories(destinationDirectory);
                Path destinationFile = destinationDirectory.resolve(selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

                User currentUser = CurrentUser.getInstance().getUser();
                Song newSong = new Song(selectedFile.getName(), "Unknown Artist", destinationFile.toString());
                currentUser.addSongToUser(newSong);
                UserStorage.saveUser(currentUser);

                updateSongView();
                System.out.println("File copied to audio path in project!");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("File copy failed. Please try again.");
            }
        } else {
            System.out.println("File selection cancelled.");
        }
    }

    @FXML
    void deleteSelectedSong(ActionEvent event) {
        String selectedSongTitle = songView.getSelectionModel().getSelectedItem();
        if (selectedSongTitle != null) {
            User currentUser = CurrentUser.getInstance().getUser();
            Song selectedSong = currentUser.getUploadedSongs().stream()
                .filter(song -> song.getTitle().equals(selectedSongTitle))
                .findFirst()
                .orElse(null);
            if (selectedSong != null) {
                currentUser.removeSongFromUser(selectedSong);
                UserStorage.saveUser(currentUser);
                updateSongView();
                System.out.println("Song successfully deleted from user.");
            } else {
                System.out.println("Selected song not found.");
            }
        } else {
            System.out.println("No song selected for deletion.");
        }
    }

    @FXML
    void handleAddToPlaylistAction(ActionEvent event) {
        String selectedSongTitle = songView.getSelectionModel().getSelectedItem();
        String selectedPlaylistName = playlistView.getSelectionModel().getSelectedItem();
        if (selectedSongTitle != null && selectedPlaylistName != null) {
            User currentUser = CurrentUser.getInstance().getUser();
            if (currentUser != null) {
                Playlist selectedPlaylist = currentUser.getPlaylists().stream()
                    .filter(playlist -> playlist.getName().equals(selectedPlaylistName))
                    .findFirst()
                    .orElse(null);
                Song selectedSong = currentUser.getUploadedSongs().stream()
                    .filter(song -> song.getTitle().equals(selectedSongTitle))
                    .findFirst()
                    .orElse(null);
                if (selectedPlaylist != null && selectedSong != null) {
                    selectedPlaylist.addSong(selectedSong);
                    UserStorage.saveUser(currentUser);
                    System.out.println("Song added to playlist!");
                }
            }
        }
    }

    @FXML
    private void createPlaylist(ActionEvent event) {
        String name = playlistNameField.getText();
        if (!name.isEmpty()) {
            User currentUser = CurrentUser.getInstance().getUser();
            if (currentUser != null) {
                Playlist newPlaylist = new Playlist(name);
                currentUser.addPlaylistToUser(newPlaylist);
                UserStorage.saveUser(currentUser);
                updatePlaylistView();
                playlistNameField.clear();
                System.out.println("Playlist created!");
            }
        } else {
            System.out.println("Playlist name cannot be empty.");
        }
    }

    @FXML
    private void refreshPlaylist(ActionEvent event) {
        updatePlaylistView();
    }

    @FXML
    private void deletePlaylist(ActionEvent event) {
        String selectedPlaylistName = playlistView.getSelectionModel().getSelectedItem();
        if (selectedPlaylistName != null) {
            User currentUser = CurrentUser.getInstance().getUser();
            if (currentUser != null) {
                Playlist selectedPlaylist = currentUser.getPlaylists().stream()
                    .filter(playlist -> playlist.getName().equals(selectedPlaylistName))
                    .findFirst()
                    .orElse(null);
                if (selectedPlaylist != null) {
                    currentUser.removePlaylistFromUser(selectedPlaylist);
                    UserStorage.saveUser(currentUser);
                    updatePlaylistView();
                    System.out.println("Playlist deleted!");
                }
            }
        } else {
            System.out.println("No playlist selected for deletion.");
        }
    }

    @FXML
    void goToMainMenu(ActionEvent event) {
        try {
            App.setRoot("menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

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
import javafx.scene.image.ImageView;
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
    private ImageView searchImageid;

    @FXML
    private ListView<String> playlistView;

    @FXML
    private ListView<String> songView;

    @FXML
    private TextField playlistNameField;

    private PlaylistManager playlistManager;

    private File selectedFile;

    //getPlaylist View >> we need these 2 so we can use them in the manage media scene
    public ListView<String> getPlaylistView() {
        return playlistView;
    }

    //getSong view
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
        FileChooser fileSelect = new FileChooser();
        Path destinationDirectory = Path.of("mms", "src", "main", "resources", "audio");

        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        selectedFile = fileSelect.showOpenDialog(stage2);

        if (selectedFile != null && selectedFile.exists() && selectedFile.delete()) {
            
            User currentUser = CurrentUser.getInstance().getUser();
            currentUser.removeSongFromUser(new Song(selectedFile.getName(), "Unknown Artist", selectedFile.toString()));
            UserStorage.saveUser(currentUser);

            updateSongView();
            System.out.println("File successfully deleted");
        } else {
            System.out.println("ERROR: File cannot be deleted");
        }
        System.out.println("Delete button clicked!");
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
            Playlist newPlaylist = playlistManager.createPlaylist(name);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Add Songs to Playlist");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav", "*.aac", "*.ogg", "*.m4a"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
            );
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                try {
                    Path destinationDirectory = Path.of("mms", "src", "main", "resources", "playlists", name);
                    Files.createDirectories(destinationDirectory);
                    Path destinationFile = destinationDirectory.resolve(selectedFile.getName());
                    Files.copy(selectedFile.toPath(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
                    newPlaylist.addSong(new Song(selectedFile.getName(), "Unknown Artist", destinationFile.toString()));

                    
                    User currentUser = CurrentUser.getInstance().getUser();
                    currentUser.addPlaylistToUser(newPlaylist);
                    UserStorage.saveUser(currentUser);

                    updatePlaylistView();
                    playlistNameField.clear();
                    System.out.println("Playlist created and song added!");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Error creating playlist or adding song. Please try again.");
                }
            } else {
                System.out.println("File selection cancelled.");
            }
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
            playlistManager.deletePlaylist(selectedPlaylistName);
            Path playlistDirectory = Path.of("mms", "src", "main", "resources", "playlists", selectedPlaylistName);
            try {
                Files.walk(playlistDirectory)
                    .map(Path::toFile)
                    .forEach(File::delete);
                Files.deleteIfExists(playlistDirectory);

                
                User currentUser = CurrentUser.getInstance().getUser();
                currentUser.removePlaylistFromUser(new Playlist(selectedPlaylistName));
                UserStorage.saveUser(currentUser);

                updatePlaylistView();
                System.out.println("Playlist deleted!");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error deleting playlist. Please try again.");
            }
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

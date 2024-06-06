package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private TextField playlistNameField;

    private PlaylistManager playlistManager;

    private File selectedFile;

    @FXML
    public void initialize() {
        playlistManager = new PlaylistManager();
        updatePlaylistView();
    }

    @FXML
    void handleAddButtonAction(MouseEvent event) {
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
    void deleteSelectedSong(MouseEvent event) {
        FileChooser fileSelect = new FileChooser();
        Path destinationDirectory = Path.of("mms", "src", "main", "resources", "audio");

        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        selectedFile = fileSelect.showOpenDialog(stage2);

        if (selectedFile != null && selectedFile.exists() && selectedFile.delete()) {
            System.out.println("File successfully deleted");
        } else {
            System.out.println("ERROR: File cannot be deleted");
        }
        System.out.println("Delete button clicked!");
    }

    @FXML
    private void createPlaylist(MouseEvent event) {
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
                    newPlaylist.addSong(new Song("Title", "Artist", destinationFile.toString()));
                    
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
    private void refreshPlaylist(MouseEvent event) {
        updatePlaylistView();
    }

    @FXML
    private void deletePlaylist(MouseEvent event) {
        String selectedPlaylistName = playlistView.getSelectionModel().getSelectedItem();
        if (selectedPlaylistName != null) {
            playlistManager.deletePlaylist(selectedPlaylistName);
            Path playlistDirectory = Path.of("mms", "src", "main", "resources", "playlists", selectedPlaylistName);
            try {
                Files.walk(playlistDirectory)
                    .map(Path::toFile)
                    .forEach(File::delete);
                Files.deleteIfExists(playlistDirectory);
                updatePlaylistView();
                System.out.println("Playlist deleted!");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error deleting playlist. Please try again.");
            }
        }
    }

    private void updatePlaylistView() {
        playlistView.getItems().clear();
        for (Playlist playlist : playlistManager.getAllPlaylists()) {
            playlistView.getItems().add(playlist.getName());
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
}

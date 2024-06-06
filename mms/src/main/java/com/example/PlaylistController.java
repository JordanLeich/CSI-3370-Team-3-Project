package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PlaylistController {

    @FXML
    private ListView<String> playlistView;

    @FXML
    private TextField playlistNameField;

    private PlaylistManager playlistManager;

    @FXML
    public void initialize() {
        playlistManager = new PlaylistManager();
        updatePlaylistView();
    }

    @FXML
    private void createPlaylist() {
        String name = playlistNameField.getText();
        if (!name.isEmpty()) {
            playlistManager.createPlaylist(name);
            updatePlaylistView();
            playlistNameField.clear();
        }
    }

    @FXML
    private void refreshPlaylist() {
        String selectedPlaylistName = playlistView.getSelectionModel().getSelectedItem();
        if (selectedPlaylistName != null) {
            Playlist selectedPlaylist = playlistManager.getPlaylist(selectedPlaylistName);
            if (selectedPlaylist != null) {
                selectedPlaylist.refreshPlaylist();
                updatePlaylistView();
            }
        }
    }

    @FXML
    private void deletePlaylist() {
        String selectedPlaylistName = playlistView.getSelectionModel().getSelectedItem();
        if (selectedPlaylistName != null) {
            playlistManager.deletePlaylist(selectedPlaylistName);
            updatePlaylistView();
        }
    }

    private void updatePlaylistView() {
        playlistView.getItems().clear();
        for (Playlist playlist : playlistManager.getAllPlaylists()) {
            playlistView.getItems().add(playlist.getName());
        }
    }
}

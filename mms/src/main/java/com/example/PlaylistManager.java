package com.example;

import java.util.ArrayList;
import java.util.List;

public class PlaylistManager {
    private List<Playlist> playlists;

    public PlaylistManager() {
        playlists = new ArrayList<>();
    }

    public Playlist createPlaylist(String name) {
        Playlist newPlaylist = new Playlist(name);
        playlists.add(newPlaylist);
        return newPlaylist;
    }

    public void deletePlaylist(String name) {
        playlists.removeIf(playlist -> playlist.getName().equals(name));
    }

    public Playlist getPlaylist(String name) {
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(name)) {
                return playlist;
            }
        }
        return null;
    }

    public List<Playlist> getAllPlaylists() {
        return playlists;
    }
}

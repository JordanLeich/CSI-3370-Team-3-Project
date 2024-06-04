package com.example;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    } 
    public void removeSong(Song song) {
        songs.remove(song);
    }

    public void createPlaylist(String playlistName) {
        Playlist newPlaylist = new Playlist(playlistName);
    }
}


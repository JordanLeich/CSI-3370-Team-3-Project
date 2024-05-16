package com.example;

import java.util.ArrayList;
import java.util.List;

public class playlist {
    private String name;
    private List<Song> songs;

    public void Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }
}

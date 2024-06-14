package com.example;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String username;
    private String password;
    private List<Playlist> playlists;
    private List<Song> uploadedSongs;

    public User(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
        this.playlists = new ArrayList<>();
        this.uploadedSongs = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<Song> getUploadedSongs() {
        return uploadedSongs;
    }

    public void setUploadedSongs(List<Song> uploadedSongs) {
        this.uploadedSongs = uploadedSongs;
    }

    public void addPlaylistToUser(Playlist playlist) {
        playlists.add(playlist);
    }

    public void removePlaylistFromUser(Playlist playlist) {
        playlists.remove(playlist);
    }

    public void addSongToUser(Song song) {
        uploadedSongs.add(song);
    }

    public void removeSongFromUser(Song song) {
        uploadedSongs.remove(song);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", playlists=" + playlists +
                ", uploadedSongs=" + uploadedSongs +
                '}';
    }
}

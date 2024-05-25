package com.example;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String firstName;
    private String username;
    private String  password;
    private List<playlist> playlists;
    private List<Song> songs;
    private boolean currentUser;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.playlists = new ArrayList<>();
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
    @Override
    public String toString() {
        return   username+":"+password;
    }






}

package com.example;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

public class User {

    BufferedWriter writer;

    private String username;
    private String  password;
    private List<String> playlists;

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






}

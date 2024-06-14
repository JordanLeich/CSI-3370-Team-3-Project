package com.example;


import com.fasterxml.jackson.annotation.JsonProperty;


public class Song {
    private String title;
    private String artist;
    private String filePath;

    public Song(@JsonProperty("title") String title, @JsonProperty("artist") String artist, @JsonProperty("filePath") String filePath) {
        this.title = title;
        this.artist = artist;
        this.filePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}

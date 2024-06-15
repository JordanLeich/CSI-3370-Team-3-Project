package com.example;

import java.util.ArrayList;
import java.util.List;

public class InitialSongs {

    public static List<Song> getInitialSongs() {
        List<Song> initialSongs = new ArrayList<>();
        
        initialSongs.add(new Song("title", "artist", "song path")); 
        initialSongs.add(new Song("title", "artist", "song path"));
        initialSongs.add(new Song("title", "artist", "song path"));
        return initialSongs;
    }
}

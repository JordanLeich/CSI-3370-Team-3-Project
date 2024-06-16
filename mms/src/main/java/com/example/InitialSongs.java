package com.example;

import java.util.ArrayList;
import java.util.List;

public class InitialSongs {

    public static List<Song> getInitialSongs() {
        List<Song> initialSongs = new ArrayList<>();
        
        initialSongs.add(new Song("Deeper", "Unknown Artist", "mms\\src\\main\\resources\\audio\\Deeper.mp3"));
        initialSongs.add(new Song("Chill 1", "Unknown Artist", "mms\\src\\main\\resources\\audio\\Chill 1.mp3"));
        initialSongs.add(new Song("Chill 2", "Unknown Artist", "mms\\src\\main\\resources\\audio\\Chill 2.mp3"));
        initialSongs.add(new Song("Chill 3", "Unknown Artist", "mms\\src\\main\\resources\\audio\\Chill 3.mp3"));
        initialSongs.add(new Song("Spongeboy", "Spongeboy", "mms\\src\\main\\resources\\audio\\spongeboy.mp3"));
        return initialSongs;
    }
}

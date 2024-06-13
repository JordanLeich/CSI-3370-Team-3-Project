package com.example;

import java.util.ArrayList;
import java.util.List;

public class InitialSongs {

    public static List<Song> getInitialSongs() {
        List<Song> initialSongs = new ArrayList<>();
        //place the intial song into the resource folder when adding, when the user is gonna add their own song we just save the filepath instead, or we could possibly include it into the resource/audio folder
        initialSongs.add(new Song("title", "artist", "song path")); //path/to/song.mp3
        initialSongs.add(new Song("title", "artist", "song path"));
        initialSongs.add(new Song("title", "artist", "song path"));
        return initialSongs;
    }
}

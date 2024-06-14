package com.example;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PlaylistTest {

    private Playlist playlist;

    @Before
    public void setUp() {
        playlist = new Playlist("TestPlaylist");
    }

    @Test
    public void testGetName() {
        assertEquals("TestPlaylist", playlist.getName());
    }

    @Test
    public void testAddSong() {
        Song song = new Song("Song1", "Artist1", "file1.mp3");
        playlist.addSong(song);
        List<Song> songs = playlist.getSongs();
        assertEquals(1, songs.size());
        assertEquals(song, songs.get(0));
    }

    @Test
    public void testRemoveSong() {
        Song song1 = new Song("Song1", "Artist1", "file1.mp3");
        Song song2 = new Song("Song2", "Artist2", "file2.mp3");
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.removeSong(song1);
        List<Song> songs = playlist.getSongs();
        assertEquals(1, songs.size());
        assertFalse(songs.contains(song1));
        assertTrue(songs.contains(song2));
        playlist.removeSong(song2);
        songs = playlist.getSongs();
        assertTrue(songs.isEmpty());
        assertFalse(songs.contains(song2));
    }
}

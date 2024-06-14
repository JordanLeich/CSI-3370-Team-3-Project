package com.example;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private User user;

    @Before
    public void setUp() {
        user = new User("Username", "Password");
    }

    @Test
    public void testAddUploadedSong() {
        Song song = new Song("Song Title", "Famous Artist", "goodsong.mp3");
        user.addSongToUser(song);
        List<Song> uploadedSongs = user.getUploadedSongs();
        assertTrue(uploadedSongs.contains(song));
    }

    @Test
    public void testRemoveUploadedSong() {
        Song song = new Song("Song Title", "Famous Artist", "goodsong.mp3");
        user.addSongToUser(song);
        user.removeSongFromUser(song);
        List<Song> uploadedSongs = user.getUploadedSongs();
        assertFalse(uploadedSongs.contains(song));
    }

    @Test
    public void testAddPlaylistToUser() {
        Playlist playlist = new Playlist("playlisty");
        user.addPlaylistToUser(playlist);
        List<Playlist> playlists = user.getPlaylists();
        assertTrue(playlists.contains(playlist));
    }

    @Test
    public void testRemovePlaylistFromUser() {
        Playlist playlist = new Playlist("playlisty");
        user.addPlaylistToUser(playlist);
        user.removePlaylistFromUser(playlist);
        List<Playlist> playlists = user.getPlaylists();
        assertFalse(playlists.contains(playlist));
    }
}

package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "/" + fxml + ".fxml"));
        return fxmlLoader.load();
        
    }

    public static void main(String[] args) {
        // Load existing users from the JSON file
        UserStorage.loadUsers();

        // Create a new user
        User user = new User("john_doe", "password123");

        // Create a new playlist and add songs to it
        Playlist playlist = new Playlist("My Playlist");
        playlist.addSong(new Song("Song 1", "Artist 1", "/path/to/song1.mp3"));
        playlist.addSong(new Song("Song 2", "Artist 2", "/path/to/song2.mp3"));

        // Add the playlist to the user
        user.addPlaylistToUser(playlist);

        // Save the user
        UserStorage.saveUser(user);

        // Check if the user exists
        if (UserStorage.userExists("john_doe", "password123")) {
            System.out.println("User exists!");
        } else {
            System.out.println("User does not exist.");
        }

        // Print all users
        System.out.println("All Users: ");
        UserStorage.getUsers().forEach(System.out::println);
        
        launch();
    }
}

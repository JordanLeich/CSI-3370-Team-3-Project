package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;

public class manageMediaController {

    @FXML
    void handleAddButtonAction(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Media File");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav", "*.aac", "*.ogg", "*.m4a"),
            new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        // Obtain the current stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        File selectedFile = fileChooser.showOpenDialog(stage);
        
        if (selectedFile != null) {
            try { 
                Path destinationDirectory = Path.of("mms", "src", "main", "resources", "audio");
                Files.createDirectories(destinationDirectory);
                Path destinationFile = destinationDirectory.resolve(selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("File copied to: " + destinationFile);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("File copy failed: " + e.getMessage());
            }
        } else {
            System.out.println("File selection cancelled.");
        }
    }

    @FXML
    void handleDeleteButtonAction() {
        System.out.println("Delete button clicked!");
    }

    @FXML
    void goToMainMenu(MouseEvent event) {
        try {
            App.setRoot("menu");
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
}

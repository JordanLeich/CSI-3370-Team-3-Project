package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class manageMediaController {

    @FXML
    private ButtonBar ButtonBarID;

    @FXML
    private Button DeleteID;

    @FXML
    private Button AddID;

    @FXML
    private TextField SeachID;

    @FXML
    private ImageView searchImageid;


    private File selectedFile; //declared class level so we can use it in add/delete


    //debug this ....
    @FXML
    void handleAddButtonAction(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Media File");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav", "*.aac", "*.ogg", "*.m4a"),
            new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try { 
                Path destinationDirectory = Path.of("mms", "src", "main", "resources", "audio");
                Files.createDirectories(destinationDirectory);
                Path destinationFile = destinationDirectory.resolve(selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File copied audio path in project!");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("File copy failed. Please try again.");
            }
        } else {
            System.out.println("File selection cancelled.");
        }
    }
    

    @FXML
    void deleteSelectedSong(ActionEvent event) {

        //NOTE: need to edit FXML file

  
        //deleting selected file
        if (selectedFile.exists()) {
            if (selectedFile.delete()) 
            { 
                System.out.println("File sucessfully deleted");
            }
            else 
            {
                System.out.println("ERROR: File cannot be deleted");
            }
             
        }

        //confirmation
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






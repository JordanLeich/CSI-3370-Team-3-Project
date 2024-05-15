package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class registerController {

    @FXML
    private Button accountExistButton;

    @FXML
    private Text debugLabel;

    @FXML
    private Button attemptRegisterButton;

    @FXML
    private RadioButton intialPackageNoRadio;

    @FXML
    private RadioButton intialPackageYesRadio;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private PasswordField pfPasswordConfirmation;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfUsername;

    @FXML
    void attemptRegisterUser(MouseEvent event) {
        String firstName = tfFirstName.getText();
        String username = tfUsername.getText();
        String password = pfPassword.getText();
        
        // Check if the user already exists
        if (UserStorage.userExists(username)) {
            debugLabel.setText("User already exists.");
            // Handle user exists scenario (show error message, etc.)
        } else {
            // Save the user if they don't exist
            User newUser = new User(username, password); // Assuming User class has a constructor with username and password
            UserStorage.saveUser(newUser);
            debugLabel.setText("User registered successfully.");
            // Handle successful registration (navigate to another screen, show success message, etc.)
        }
    }
    
}


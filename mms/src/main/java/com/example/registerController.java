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
        
        
        if (UserStorage.userExists(username, password)) {
            debugLabel.setText("User already exists.");
            
        } else {
            
            User newUser = new User(username, password); 
            UserStorage.saveUser(newUser);
            debugLabel.setText("User registered successfully.");
            
        }
    }
    
}


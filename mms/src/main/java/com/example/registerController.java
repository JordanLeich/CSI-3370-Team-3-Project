package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class registerController {

    @FXML
    private Label debugLabel;

    @FXML
    private Button accountExistButton;

    @FXML
    private Button attemptRegisterButton;

    @FXML
    private RadioButton initialPackageNoRadio;

    @FXML
    private RadioButton initialPackageYesRadio;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private PasswordField pfPasswordConfirmation;

    @FXML
    private TextField tfUsername;

    @FXML
    void goToLogin(MouseEvent event) {
        try {
            App.setRoot("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void attemptRegisterUser(MouseEvent event) {    
        String username = tfUsername.getText();
        String password = pfPassword.getText();
        String passwordConfirmation = pfPasswordConfirmation.getText();
        
        if (UserStorage.userExists(username,password)){
        debugLabel.setText(password);
        }

        if (username.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty() ) {
            debugLabel.setText("All fields are required.");
            return;
        }

        if (!password.equals(passwordConfirmation)) {
            debugLabel.setText("Passwords do not match.");
            return;
        }

        if (UserStorage.userExists(username, password)) {
            debugLabel.setText("Username already exists.");
            return;
        }

        User newUser = new User(username, password);

        
        if (initialPackageYesRadio.isSelected()) {
            newUser.getUploadedSongs().addAll(InitialSongs.getInitialSongs());
        }

        UserStorage.saveUser(newUser);

       // debugLabel.setText("User registered successfully!");
        clearFields();
    }

    private void clearFields() {
        tfUsername.clear();
        pfPassword.clear();
        pfPasswordConfirmation.clear();
        initialPackageNoRadio.setSelected(false);
        initialPackageYesRadio.setSelected(false);
    }
}

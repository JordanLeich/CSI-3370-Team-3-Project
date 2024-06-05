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
    void goToLogin(MouseEvent event) {
        try {
            App.setRoot("login");
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
    
}


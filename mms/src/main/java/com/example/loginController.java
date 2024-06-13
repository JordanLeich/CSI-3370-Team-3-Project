package com.example;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class loginController {

    @FXML
    private PasswordField pfPassword;

    @FXML
    private Label failedActionReadout;

    @FXML
    private Button loginButton;

    @FXML
    private TextField tfUsername;

    @FXML
    private Button registerButton;

    @FXML
    private void switchToRegister(MouseEvent event) throws IOException {
        App.setRoot("register");
    }

    private void switchToMenuScreen() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    void loginSequence(MouseEvent event) {
        String username = tfUsername.getText();
        String password = pfPassword.getText();

        if (UserStorage.userExists(username, password)) {
            try {
                switchToMenuScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            failedActionReadout.setText("Account does not exist or credentials are incorrect!");
            FadeTransition ft = new FadeTransition(Duration.seconds(2.5), failedActionReadout);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.play();
        }
    }
}

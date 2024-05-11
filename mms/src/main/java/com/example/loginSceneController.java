package com.example;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.App; // Add this import statement





public class loginSceneController{

    String user = "123";
    String password = "123";

    @FXML
    private Text failedActionReadout;

    @FXML
    private Button loginButton;

    @FXML
    private TextField tfPassword;

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
        if(tfUsername.getText().equals(user) && tfPassword.getText().equals(password)){
            try{
                switchToMenuScreen();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            failedActionReadout.setText("Invalid username or password");
        }
    }




}

package com.example;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;





public class loginController{

    String user = "123";
    String password = "123";

     @FXML
    private PasswordField pfPassword;

    @FXML
    private Text failedActionReadout;

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
        if(tfUsername.getText().equals(user) && pfPassword.getText().equals(password)){
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

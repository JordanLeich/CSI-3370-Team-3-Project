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




public class loginSceneController{

    String user = "123";
    String password = "123";

    
    private void successfulUserLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("musicPlayer"));
            Parent root = loader.load();
            
            Stage stage = new Stage(); // Create a new instance of Stage
            stage.setScene(new Scene(root));
            stage.show(); 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginSequence(MouseEvent event) {
        String enteredUser = tfUsername.getText();
        String enteredPassword = tfPassword.getText();

        if(password.equals(enteredPassword) && user.equals(enteredUser)) {
            successfulUserLogin();
        } else {
            failedActionReadout.setText("Your credentials were incorrect");
        }
    }

    @FXML
    private Text failedActionReadout;

    @FXML
    private Button loginButton;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfUsername;

    
    



}

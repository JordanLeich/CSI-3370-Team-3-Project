package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UserStorage {
    static BufferedReader reader;
        //file path to where users are stored
    private static final String FILE_PATH = "users.txt";

    public static void saveUser(User user) {


        try {
            
        //save user to users.txt
    }

    public static void  userExists(String username) {
        //check if user exists in users.txt given username
        try {
            reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            boolean userExists = false;
            while((line = reader.readLine()) != null) {
                if(line.equals(username)) {
                    userExists = true;
                    break; // break out of loop
                }
            }
            if(userExists) {
                //set label that user is not available
            } else {
                //set label that user is available
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        }

}



    


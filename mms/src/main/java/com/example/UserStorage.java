package com.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserStorage {
    private static final String FILE_PATH = "users.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static List<User> users = new ArrayList<>();

    static {
        loadUsers();
    }

    public static void saveUser(User user) {
        users.add(user);
        saveUsers();
    }

    public static boolean userExists(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private static void saveUsers() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void loadUsers() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                users = new ArrayList<>(List.of(objectMapper.readValue(file, User[].class)));
                System.out.println("Users loaded from file: " + users); 
            } else {
                users = new ArrayList<>();
                System.out.println("File does not exist, initializing empty users list."); 
            }
        } catch (IOException e) {
            users = new ArrayList<>();
            e.printStackTrace();
        }
    }
}

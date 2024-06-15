package com.example;

public class CurrentUser {
    private static CurrentUser activeUser;
    private User user;

    private CurrentUser() { }

    public static synchronized CurrentUser getInstance() {
        if (activeUser == null) {
            activeUser = new CurrentUser();
        }
        return activeUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

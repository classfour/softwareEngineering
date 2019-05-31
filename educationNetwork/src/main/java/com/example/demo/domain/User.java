package com.example.demo.domain;

public class User {
    public User(String username, String password, String level) {
        this.username = username;
        this.password = password;
        this.level = level;
    }

    private String username;

    private String password;

    private String level;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLevel() {
        return level;
    }
}

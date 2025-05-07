package com.example.demo1;

import java.util.Objects;

public class User {
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }

    // Convert user to file string format
    public String toFileString() {
        return "name:" + name + ",email:" + email + ",password:" + password;
    }

    // Create user from file string
    public static User fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        String name = parts[0].split(":")[1];
        String email = parts[1].split(":")[1];
        String password = parts[2].split(":")[1];
        return new User(name, email, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
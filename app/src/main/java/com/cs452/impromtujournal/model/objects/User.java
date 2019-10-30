package com.cs452.impromtujournal.model.objects;

import com.google.gson.annotations.SerializedName;

public class User {
    String username;
    String firstName;
    String lastName;
    String password;
    Boolean displayPrompts;

    public User() {
    }

    public User(String username, String firstName, String lastName, String password, Boolean displayPrompts) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.displayPrompts = displayPrompts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDisplayPrompts() {
        return displayPrompts;
    }

    public void setDisplayPrompts(Boolean displayPrompts) {
        this.displayPrompts = displayPrompts;
    }
}

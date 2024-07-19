package edu.wisconsin.databaseclass.pet_connect.entities;


import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_ID;

    private String username;
    private String email;
    private String password;

    private String role;

    private Integer rescuerId;

    private LocalDateTime createdAt;

    // other fields

    // getters and setters

    public int getUserId() {
        return user_ID;
    }

    public void setUserId(int userId) {
        this.user_ID = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // other getters and setters
}
package edu.wisconsin.databaseclass.pet_connect.entities;


import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    // marked with ID and GeneratedValue as an identifier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_ID;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    // not all users are rescuers so it is nullable, but each rescuerId is unique to the rescuer
    @Column(nullable = true, unique = true) 
    private Integer rescuerId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

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
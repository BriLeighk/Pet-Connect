package edu.wisconsin.databaseclass.pet_connect.entities;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

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
    private String role = "USER"; // default role

    @Column(nullable = true, unique = true) 
    private Integer rescuerId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Lob
    @Column(name = "profile_image", columnDefinition = "LONGBLOB")
    private byte[] profileImage;

    @PrePersist // stores current date/time in database on creation of user record
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getRescuerId() {
        return rescuerId;
    }

    public void setRescuerId(Integer rescuerId) {
        this.rescuerId = rescuerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
}
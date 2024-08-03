package edu.wisconsin.databaseclass.pet_connect.entities;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id // denote as PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_ID; // type INT

    @Column(nullable = false) // Not null
    private String username; // type varchar(255)

    @Column(nullable = false, unique = true) // can uniquely identify user
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Lob // indicate as long blob type
    @Column(name = "profile_image", columnDefinition = "LONGBLOB")
    private byte[] profileImage;

    @OneToOne(mappedBy = "user")
    private Rescuer rescuer;

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

    public Rescuer getRescuer() {
        return rescuer;
    }

    public void setRescuer(Rescuer rescuer) {
        this.rescuer = rescuer;
    }
}
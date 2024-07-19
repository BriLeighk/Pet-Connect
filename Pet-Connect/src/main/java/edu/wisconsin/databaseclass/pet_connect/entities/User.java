package edu.wisconsin.databaseclass.pet_connect.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class User {

    private Long userId;
    private String username;
    private String email;
    private String password;
    private String role;
    private String rescuerId;
    private LocalDateTime createdAt;


    private Set<Pet> favoritePets = new HashSet<>();

    // Getters and setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getRescuerId() {
        return rescuerId;
    }

    public void setRescuerId(String rescuerId) {
        this.rescuerId = rescuerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Pet> getFavoritePets() {
        return favoritePets;
    }

    public void setFavoritePets(Set<Pet> favoritePets) {
        this.favoritePets = favoritePets;
    }
}

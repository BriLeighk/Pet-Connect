package edu.wisconsin.databaseclass.pet_connect.entities;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;


// FavoritesID model to represent the composite key between User and Pet entities
@Embeddable
public class FavoritesId implements Serializable {
    private int userId;
    private String petId;

    public FavoritesId() {}

    public FavoritesId(int userId, String petId) {
        this.userId = userId;
        this.petId = petId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritesId that = (FavoritesId) o;
        return userId == that.userId && Objects.equals(petId, that.petId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, petId);
    }
}
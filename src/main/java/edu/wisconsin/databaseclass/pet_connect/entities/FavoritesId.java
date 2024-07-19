package edu.wisconsin.databaseclass.pet_connect.entities;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class FavoritesId implements Serializable {
    private int userId;
    private int petId;

    public FavoritesId() {}

    public FavoritesId(int userId, int petId) {
        this.userId = userId;
        this.petId = petId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritesId that = (FavoritesId) o;
        return userId == that.userId && petId == that.petId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, petId);
    }
}

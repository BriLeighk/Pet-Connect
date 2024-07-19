package edu.wisconsin.databaseclass.pet_connect.entities;

import jakarta.persistence.*;
import java.util.Date;


// Entity for the Favorites relationship between User and Pet
@Entity
@Table(name = "favorites")
public class Favorites {
    @EmbeddedId
    private FavoritesId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_ID")
    private User user;

    @ManyToOne
    @MapsId("petId")
    @JoinColumn(name = "pet_ID")
    private Pet pet;

    private Date favorited_at;

    // Getters and setters

    public FavoritesId getId() {
        return id;
    }

    public void setId(FavoritesId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Date getFavoritedAt() {
        return favorited_at;
    }

    public void setFavoritedAt(Date favorited_at) {
        this.favorited_at = favorited_at;
    }
}



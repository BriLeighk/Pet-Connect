package edu.wisconsin.databaseclass.pet_connect.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "breed")
public class Breed {
    @Id
    private int breedId;

    @Column(nullable = false)
    private int type;

    @Column(nullable = false)
    private String name;

    // Getters and setters
    public int getBreedId() {
        return breedId;
    }

    public void setBreedId(int breedId) {
        this.breedId = breedId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
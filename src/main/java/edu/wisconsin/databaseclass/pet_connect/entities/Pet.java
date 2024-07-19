package edu.wisconsin.databaseclass.pet_connect.entities;

import jakarta.persistence.*;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pet_ID;

    private String name;
    private String breed;
    private String type;
    private int age;

    // other fields

    // getters and setters

    public int getPetId() {
        return pet_ID;
    }

    public void setPetId(int petId) {
        this.pet_ID = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // other getters and setters
}



package edu.wisconsin.databaseclass.pet_connect.entities;

import jakarta.persistence.*;

// Model for the Pet Entity
@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pet_ID;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String breed;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private double size;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String adoptionStatus;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String healthStatus;

    @Column(nullable = false)
    private String vaccinationStatus;

    @Column(nullable = false)
    private boolean sterilized;

    @Column(nullable = false)
    private boolean dewormed;

    @Column(nullable = false)
    private double fee;

    @Column(nullable = false)
    private double maturitySize;

    @Column(nullable = false)
    private double furLength;

    @Column(nullable = false)
    private int videoAmount;

    @Column(nullable = false)
    private int photoAmount;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "rescuer_ID", nullable = false)
    private User rescuer;

    @ManyToOne
    @JoinColumn(name = "location_ID", nullable = false)
    private Location location;

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



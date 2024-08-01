package edu.wisconsin.databaseclass.pet_connect.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "color")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int colorId;

    @Column(nullable = false)
    private String name;

    // Getters and setters
    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

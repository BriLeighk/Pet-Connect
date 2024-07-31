package edu.wisconsin.databaseclass.pet_connect.entities;

import jakarta.persistence.*;

// Entity for Location
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int location_ID;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double latitude;

    // getters and setters

    public int getLocationId() {
        return location_ID;
    }

    public void setLocationId(int locationId) {
        this.location_ID = locationId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
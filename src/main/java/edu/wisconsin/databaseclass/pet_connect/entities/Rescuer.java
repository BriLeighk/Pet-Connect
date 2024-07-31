package edu.wisconsin.databaseclass.pet_connect.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "rescuer")
public class Rescuer {

    @Id
    private int rescuerId;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @OneToMany(mappedBy = "rescuer")
    private List<Pet> pets;

    // getters and setters

    public int getRescuerId() {
        return rescuerId;
    }

    public void setRescuerId(int rescuerId) {
        this.rescuerId = rescuerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
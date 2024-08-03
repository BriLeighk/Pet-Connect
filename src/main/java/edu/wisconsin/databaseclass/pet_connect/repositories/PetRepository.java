package edu.wisconsin.databaseclass.pet_connect.repositories;

import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import edu.wisconsin.databaseclass.pet_connect.entities.Rescuer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, String> { // JpaRepository provides CRUD operations

    List<Pet> findByRescuer(Rescuer rescuer); // Custom query method to find pets by rescuer

    void deleteById(@NonNull String petId); // Method to delete a pet by its ID
}
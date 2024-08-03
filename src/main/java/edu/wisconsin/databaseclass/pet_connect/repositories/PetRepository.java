package edu.wisconsin.databaseclass.pet_connect.repositories;

import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import edu.wisconsin.databaseclass.pet_connect.entities.Rescuer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, String>, JpaSpecificationExecutor<Pet> { 
    // JpaRepository provides CRUD operations
    // JpaSpecificationExecutor provides support for dynamic queries

    List<Pet> findByRescuer(Rescuer rescuer); // Custom query method to find pets by rescuer

    void deleteById(@NonNull String petId); // Method to delete a pet by its ID

    List<Pet> findByNameContainingIgnoreCase(String name); // Method to search pets by name

    List<Pet> findByType(int type); // Method to search pets by type

    List<Pet> findByBreed1OrBreed2(int breed1, int breed2); // Method to search pets by breed

    List<Pet> findByAdoptionStatusContainingIgnoreCase(String adoptionStatus); // Method to search pets by adoption status
}
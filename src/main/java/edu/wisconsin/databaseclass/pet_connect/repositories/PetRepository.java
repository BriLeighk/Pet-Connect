package edu.wisconsin.databaseclass.pet_connect.repositories;

import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> getAllPets();
    Pet getPetById();
    Pet savePet();
    void deletePet();
}


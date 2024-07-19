package edu.wisconsin.databaseclass.pet_connect.repositories;

import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}


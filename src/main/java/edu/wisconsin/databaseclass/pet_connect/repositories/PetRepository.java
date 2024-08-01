package edu.wisconsin.databaseclass.pet_connect.repositories;

import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import edu.wisconsin.databaseclass.pet_connect.entities.Rescuer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findByRescuer(Rescuer rescuer);
}
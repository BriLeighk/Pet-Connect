package edu.wisconsin.databaseclass.pet_connect.entities;

import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {
	
}
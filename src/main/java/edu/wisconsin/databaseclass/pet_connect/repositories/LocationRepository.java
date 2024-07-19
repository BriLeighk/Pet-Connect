package edu.wisconsin.databaseclass.pet_connect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.wisconsin.databaseclass.pet_connect.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}


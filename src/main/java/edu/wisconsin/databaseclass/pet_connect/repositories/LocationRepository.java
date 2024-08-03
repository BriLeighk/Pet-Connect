package edu.wisconsin.databaseclass.pet_connect.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.wisconsin.databaseclass.pet_connect.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findAll();
}
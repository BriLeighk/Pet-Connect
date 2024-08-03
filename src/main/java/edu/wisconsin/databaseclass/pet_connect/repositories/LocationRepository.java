package edu.wisconsin.databaseclass.pet_connect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.wisconsin.databaseclass.pet_connect.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    boolean existsByLongitudeAndLatitude(double longitude, double latitude);
}
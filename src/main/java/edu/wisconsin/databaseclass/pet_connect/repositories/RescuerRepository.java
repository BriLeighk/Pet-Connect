package edu.wisconsin.databaseclass.pet_connect.repositories;
import edu.wisconsin.databaseclass.pet_connect.entities.Rescuer;
import java.util.Optional; // Added this import
import org.springframework.lang.NonNull; // Add this import

import org.springframework.data.jpa.repository.JpaRepository;

public interface RescuerRepository extends JpaRepository<Rescuer, String> {
     @NonNull
     Optional<Rescuer> findById(@NonNull String rescuerId);
}
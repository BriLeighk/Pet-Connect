package edu.wisconsin.databaseclass.pet_connect.repositories;

import edu.wisconsin.databaseclass.pet_connect.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}

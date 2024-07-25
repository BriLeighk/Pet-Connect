package edu.wisconsin.databaseclass.pet_connect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.wisconsin.databaseclass.pet_connect.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}

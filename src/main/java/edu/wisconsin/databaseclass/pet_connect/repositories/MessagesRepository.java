package edu.wisconsin.databaseclass.pet_connect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.wisconsin.databaseclass.pet_connect.entities.Messages;
import edu.wisconsin.databaseclass.pet_connect.entities.MessagesId;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, MessagesId> {
}



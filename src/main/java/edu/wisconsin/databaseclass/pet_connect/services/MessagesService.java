package edu.wisconsin.databaseclass.pet_connect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.wisconsin.databaseclass.pet_connect.entities.Messages;
import edu.wisconsin.databaseclass.pet_connect.entities.MessagesId;
import edu.wisconsin.databaseclass.pet_connect.repositories.MessagesRepository;

import java.util.List;

@Service
public class MessagesService {

    @Autowired
    private MessagesRepository messagesRepository;

    public List<Messages> getAllMessages() {
        return messagesRepository.findAll();
    }

    public Messages getMessagesById(MessagesId id) {
        return messagesRepository.findById(id).orElse(null);
    }

    public Messages saveMessages(Messages messages) {
        return messagesRepository.save(messages);
    }

    public void deleteMessages(MessagesId id) {
        messagesRepository.deleteById(id);
    }
}



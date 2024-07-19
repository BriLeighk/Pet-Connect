package edu.wisconsin.databaseclass.pet_connect.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.wisconsin.databaseclass.pet_connect.entities.Message;
import edu.wisconsin.databaseclass.pet_connect.entities.Messages;
import edu.wisconsin.databaseclass.pet_connect.entities.MessagesId;
import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import edu.wisconsin.databaseclass.pet_connect.entities.User;
import edu.wisconsin.databaseclass.pet_connect.services.MessageService;
import edu.wisconsin.databaseclass.pet_connect.services.MessagesService;
import edu.wisconsin.databaseclass.pet_connect.services.UserService;

/*
 * Controller for the Message entity and Messages relationship set
 */
@Controller
public class CombinedMessagesController {

    @Autowired
    private MessagesService messagesService; // initialize messagesService (messages relationship set service file)

    @Autowired
    private UserService userService; // init userService

    @Autowired
    private MessageService messageService; // init messageService (message Entity service file)


    // boilerplate code -- may need modification once message relationship is set up
    @GetMapping("/messages")
    public String getAllMessages(Model model) {
        model.addAttribute("messages", messageService.getAllMessages());
        return "message";
    }

    @PostMapping("/save-message")
    public String saveMessage(@RequestParam("sender_ID") int senderId, @RequestParam("receiver_ID") int receiverId,
                              @RequestParam("pet_ID") int petId, @RequestParam("content") String content) {
        Message message = new Message();
        message.setSender(userService.getUserById(senderId));
        message.setReceiver(userService.getUserById(receiverId));
        message.setPet(new Pet()); 
        message.setContent(content);
        message.setSentAt(new Date());
        messageService.saveMessage(message);
        return "redirect:/messages";
    }

    @GetMapping("/all-messages")
    public String getAllMessagesFromMessages(Model model) {
        model.addAttribute("messages", messagesService.getAllMessages());
        return "messages";
    }

    @PostMapping("/save-all-messages")
    public String saveMessages(@RequestParam("user_ID") int userId, @RequestParam("message_ID") int messageId) {
        Messages messages = new Messages();
        MessagesId id = new MessagesId(userId, messageId);
        User user = userService.getUserById(userId);
        Message message = messageService.getMessageById(messageId);
        messages.setId(id);
        messages.setUser(user);
        messages.setMessage(message);
        messagesService.saveMessages(messages);
        return "redirect:/all-messages";
    }
}

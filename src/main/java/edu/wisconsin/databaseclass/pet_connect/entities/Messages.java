package edu.wisconsin.databaseclass.pet_connect.entities;

import jakarta.persistence.*;

// Entity for the Messages Relationshp set between User and Message (User messages User)
@Entity
@Table(name = "messages")
public class Messages {
    @EmbeddedId
    private MessagesId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_ID")
    private User user;

    @ManyToOne
    @MapsId("messageId")
    @JoinColumn(name = "message_ID")
    private Message message;

    // Getters and setters

    public MessagesId getId() {
        return id;
    }

    public void setId(MessagesId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
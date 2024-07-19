package edu.wisconsin.databaseclass.pet_connect.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int message_ID;

    @ManyToOne
    @JoinColumn(name = "sender_ID")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_ID")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "pet_ID")
    private Pet pet;

    private String content;
    private Date sent_at;

    // getters and setters

    public int getMessageId() {
        return message_ID;
    }

    public void setMessageId(int messageId) {
        this.message_ID = messageId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSentAt() {
        return sent_at;
    }

    public void setSentAt(Date sentAt) {
        this.sent_at = sentAt;
    }
}




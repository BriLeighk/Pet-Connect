package edu.wisconsin.databaseclass.pet_connect.entities;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

// Model to represent composite key relation for the Messages relationship set between User and Message Entities
@Embeddable
public class MessagesId implements Serializable {
    private int userId;
    private int messageId;

    public MessagesId() {}

    public MessagesId(int userId, int messageId) {
        this.userId = userId;
        this.messageId = messageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessagesId that = (MessagesId) o;
        return userId == that.userId && messageId == that.messageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, messageId);
    }
}



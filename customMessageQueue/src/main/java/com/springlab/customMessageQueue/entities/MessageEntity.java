package com.springlab.customMessageQueue.entities;

import com.springlab.customMessageQueue.enums.MessageStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime pickedAt;

    private int retryCount = 0;
    private int maxRetryCount = 3;

    @Enumerated(EnumType.STRING)
    private MessageStatus status = MessageStatus.PENDING;

    public MessageEntity(String content, MessageStatus status, LocalDateTime pickedAt) {
        this.content = content;
        this.status = status;
        this.pickedAt = pickedAt;
    }
    public MessageEntity(){}

    public LocalDateTime getPickedAt() {
        return pickedAt;
    }

    public void setPickedAt(LocalDateTime pickedAt) {
        this.pickedAt = pickedAt;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public int getMaxRetryCount() {
        return maxRetryCount;
    }

    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }
}

package com.dailycoder.customMessageQueue.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class DeadLetterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long originalMessageId;
    private String content;
    private String failureReason;
    private LocalDateTime failedAt;

    public DeadLetterEntity(Long originalMessageId, String content, String failureReason, LocalDateTime failedAt) {
        this.originalMessageId = originalMessageId;
        this.content = content;
        this.failureReason = failureReason;
        this.failedAt = failedAt;
    }

    public DeadLetterEntity(){}

    public Long getId() {
        return id;
    }

    public Long getOriginalMessageId() {
        return originalMessageId;
    }

    public void setOriginalMessageId(Long originalMessageId) {
        this.originalMessageId = originalMessageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public LocalDateTime getFailedAt() {
        return failedAt;
    }

    public void setFailedAt(LocalDateTime failedAt) {
        this.failedAt = failedAt;
    }
}

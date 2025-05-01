package com.dailycoder.customMessageQueue.repository;

import com.dailycoder.customMessageQueue.entities.MessageEntity;
import com.dailycoder.customMessageQueue.enums.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findTop10ByStatusOrderByIdAsc(MessageStatus status);

    // Find messages that are either PENDING OR stuck in PROCESSING beyond timeout
    @Query("SELECT m FROM MessageEntity m WHERE" +
            "(m.status = 'PENDING') " +
            "OR (m.status = 'PROCESSING' AND m.pickedAt <= :timeoutLimit)" +
            "ORDER BY m.id ASC")
    List<MessageEntity> findPendingOrTimeoutMessages(@Param("timeoutLimit") LocalDateTime timeoutLimit);
}

package com.dailycoder.customMessageQueue.repository;

import com.dailycoder.customMessageQueue.entities.DeadLetterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeadLetterRepository extends JpaRepository<DeadLetterEntity, Long> {
}

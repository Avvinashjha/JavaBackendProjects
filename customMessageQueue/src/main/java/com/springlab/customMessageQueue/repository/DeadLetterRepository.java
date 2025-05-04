package com.springlab.customMessageQueue.repository;

import com.springlab.customMessageQueue.entities.DeadLetterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeadLetterRepository extends JpaRepository<DeadLetterEntity, Long> {
}

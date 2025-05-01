package com.dailycoder.kafkaDemo.repository;

import com.dailycoder.kafkaDemo.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}

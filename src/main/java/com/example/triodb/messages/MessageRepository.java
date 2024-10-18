package com.example.triodb.messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Messages, Long> {
    @Query("SELECT s FROM Messages s WHERE s.messageId = ?1")
    Optional<Messages> findMessagesByMessageId(String id);
}
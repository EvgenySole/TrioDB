package com.example.triodb.chats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chats, Long> {
    @Query("SELECT s FROM Chats s WHERE s.chatId = ?1")
    Optional<Chats> findChatsBysByChatId(String id);
}
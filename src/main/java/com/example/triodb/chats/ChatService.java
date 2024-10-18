package com.example.triodb.chats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ChatService {

    public final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }
    public List<Chats> getChats() {
        return chatRepository.findAll();
    }

    public List<Chats> getChat(Long messageId) {
        boolean exists = chatRepository.existsById(messageId);
        if (!exists){
            throw new IllegalStateException("Message with id " + messageId + " doesn't exist");
        }
        return chatRepository.findAllById(Collections.singleton(messageId));
    }
    public List<Chats> getChatsByPersonId(Long personId) {
        List<Chats> allMessages = chatRepository.findAll();
        List<Chats> targetMessages = new ArrayList<>();
        for (int i = 0; i < allMessages.size(); i++){
            if (allMessages.get(i).getPerson1Id().getPersonId() == personId ||
                    allMessages.get(i).getPerson2Id().getPersonId() == personId){
                targetMessages.add(allMessages.get(i));
            }
        }
        return targetMessages;
    }
    public Chats addNewChat(Chats message){
        System.out.println(message);
        return chatRepository.save(message);
    }

    public void deleteChat(Long messageId){
        boolean exists = chatRepository.existsById(messageId);
        if (!exists){
            throw new IllegalStateException("Chat with id " + messageId + " doesn't exsist");
        }
        chatRepository.deleteById(messageId);
        System.out.println(messageId);
    }

    @Transactional
    public void updateChatLastMsgAndDate(Long chatId, String lastMessage, Timestamp lastDate) {
        Chats chat = chatRepository.findById(chatId).orElseThrow(
                () -> new IllegalStateException("Chat with id " + chatId +
                        "doesn't exists")
        );
        System.out.println(lastMessage);
        if (lastMessage != null  && !Objects.equals(chat.getLastMessage(), lastMessage)) {
            chat.setLastMessage(lastMessage);
        }
        if (lastDate != null  && !Objects.equals(chat.getLastDate(), lastDate)) {
            chat.setLastDate(lastDate);
        }
    }
}

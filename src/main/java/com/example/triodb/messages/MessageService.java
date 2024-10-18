package com.example.triodb.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MessageService {

    public final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    public List<Messages> getMessages() {
        return messageRepository.findAll();
    }

    public List<Messages> getMessage(Long messageId) {
        boolean exists = messageRepository.existsById(messageId);
        if (!exists){
            throw new IllegalStateException("Message with id " + messageId + " doesn't exist");
        }
        return messageRepository.findAllById(Collections.singleton(messageId));
    }
    public List<Messages> getMessagesByPersonId(Long personId) {
        List<Messages> allMessages = messageRepository.findAll();
        List<Messages> targetMessages = new ArrayList<>();
        for (int i = 0; i < allMessages.size(); i++){
            if (allMessages.get(i).getSenderId().getPersonId() == personId){
                targetMessages.add(allMessages.get(i));
            }
        }
        return targetMessages;
    }

    public List<Messages> getMessagesByChatId(Long chatId){
        List<Messages> allMessages = messageRepository.findAll();
        List<Messages> targetMessages = new ArrayList<>();
        for (int i = 0; i < allMessages.size(); i++){
            if (allMessages.get(i).getChatId().getChatId() == chatId){
                targetMessages.add(allMessages.get(i));
            }
        }
        return targetMessages;
    }
    public Messages addNewMessage(Messages message){
        System.out.println(message);
        return messageRepository.save(message);
    }

    public void deleteMessage(Long messageId){
        boolean exists = messageRepository.existsById(messageId);
        if (!exists){
            throw new IllegalStateException("Message with id " + messageId + " doesn't exsist");
        }
        messageRepository.deleteById(messageId);
        System.out.println(messageId);
    }
}

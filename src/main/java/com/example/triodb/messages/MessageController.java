package com.example.triodb.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "messages")
public class MessageController {
    public final MessageService messageService;
    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Messages> getMessages(){
        List<Messages> sortedMessages = messageService.getMessages();
        for (int i = 0; i < sortedMessages.size(); i++){
            for (int j = 0; j < sortedMessages.size()-1; j++){
                if (sortedMessages.get(j).getMessageId() > sortedMessages.get(j+1).getMessageId()){
                    Messages temp = sortedMessages.get(j);
                    sortedMessages.set(j, sortedMessages.get(j+1));
                    sortedMessages.set(j+1, temp);
                }
            }
        }
        return sortedMessages;
    }

    @GetMapping(path ="{messageId}")
    public List<Messages> getMessage(@PathVariable("messageId") Long messageId){
        return messageService.getMessage(messageId);
    }
    @GetMapping(path = "/person/{personId}")
    public List<Messages> getMessagesByPersonId(@PathVariable("personId") Long personId){
        return messageService.getMessagesByPersonId(personId);
    }
    @GetMapping(path = "/chat/{chatId}")
    public List<Messages> getMessagesByChatId(@PathVariable("chatId") Long chatId){
        List<Messages> sortedMessages = messageService.getMessagesByChatId(chatId);
        for (int i = 0; i < sortedMessages.size(); i++){
            for (int j = 0; j < sortedMessages.size()-1; j++){
                if (sortedMessages.get(j).getMessageId() > sortedMessages.get(j+1).getMessageId()){
                    Messages temp = sortedMessages.get(j);
                    sortedMessages.set(j, sortedMessages.get(j+1));
                    sortedMessages.set(j+1, temp);
                }
            }
        }
        return sortedMessages;
    }
    @PostMapping
    public void addNewMessage(@RequestBody Messages message){
        message.setDateTime(Timestamp.valueOf(LocalDateTime.now().plusSeconds(60 * 60 * 3)));
        messageService.addNewMessage(message);
    }
    @DeleteMapping(path = "{messageId}")
    public void deleteMessage(@PathVariable("messageId") Long messageId){
        messageService.deleteMessage(messageId);
    }
}


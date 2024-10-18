package com.example.triodb.chats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(path = "chats")
public class ChatController {
    public final ChatService chatService;
    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public List<Chats> getChats(){
        List<Chats> sortedChats = chatService.getChats();
        for (int i = 0; i < sortedChats.size(); i++){
            for (int j = 0; j < sortedChats.size()-1; j++){
                if (sortedChats.get(j).getChatId() > sortedChats.get(j+1).getChatId()){
                    Chats temp = sortedChats.get(j);
                    sortedChats.set(j, sortedChats.get(j+1));
                    sortedChats.set(j+1, temp);
                }
            }
        }
        return sortedChats;
    }

    @GetMapping(path ="{chatId}")
    public List<Chats> getChat(@PathVariable("chatId") Long messageId){
        return chatService.getChat(messageId);
    }
    @GetMapping(path = "/person/{personId}")
    public List<Chats> getChatsByPersonId(@PathVariable("personId") Long personId){
        List<Chats> sortedChats = chatService.getChatsByPersonId(personId);
        for (int i = 0; i < sortedChats.size(); i++){
            for (int j = 0; j < sortedChats.size()-1; j++){
                Timestamp date0 = sortedChats.get(j).getLastDate();
                Timestamp date1 = sortedChats.get(j+1).getLastDate();
                if (date0.before(date1)){
                    Chats temp = sortedChats.get(j);
                    sortedChats.set(j, sortedChats.get(j+1));
                    sortedChats.set(j+1, temp);
                }
            }
        }
        return sortedChats;
    }
    @PostMapping
    public void addNewChat(@RequestBody Chats message){
        chatService.addNewChat(message);
    }
    @DeleteMapping(path = "{chatId}")
    public void deleteChat(@PathVariable("chatId") Long messageId){
        chatService.deleteChat(messageId);
    }
    @PutMapping(path = "/lastMsgAndDate/{chatId}")
    public ResponseEntity<String> updateChatLastMsgAndDate(
            @PathVariable("chatId") Long chatId,
            @RequestParam(required = false) String lastMessage,
            @RequestParam(required = false) Timestamp lastDate){
        chatService.updateChatLastMsgAndDate(chatId, lastMessage,
                Timestamp.valueOf(lastDate.toLocalDateTime().plusSeconds(60 * 60 * 3)));
        return ResponseEntity.ok("{\"line\":\"Chat updated\"}");
    }
}


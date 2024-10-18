package com.example.triodb.chat_real_time;

import com.example.triodb.messages.MessageService;
import com.example.triodb.messages.Messages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Controller
public class SendContentController {
    public final SimpMessagingTemplate simpMessagingTemplate;
    public final MessageService messageService;

    public SendContentController(SimpMessagingTemplate simpMessagingTemplate, MessageService messageService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageService = messageService;
    }

    @MessageMapping("/chat")
    public ResponseEntity<String> processMessage(@Payload Messages message) {
        System.out.println(message.getSenderId() + " " + message.getGetterId() + " " + message.getContent());
        message.setDateTime(Timestamp.valueOf(LocalDateTime.now().plusSeconds(60 * 60 * 3)));
        Messages saved = messageService.addNewMessage(message);
        simpMessagingTemplate.convertAndSendToUser(
                message.getGetterId().getPersonId().toString(),"/queue/messages",
                new SendContent(saved.getContent()));
        return ResponseEntity.ok("{\"line\":\"Message saved\"}");
    }
}

package com.example.triodb.messages;

import com.example.triodb.chats.Chats;
import com.example.triodb.persons.Persons;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="messages")
public class Messages {
    @Id
    @Column(name = "message_id", nullable = false)
    @SequenceGenerator(
            name = "messages_sequence",
            sequenceName = "messages_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "messages_sequence"
    )
    private Long messageId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id", referencedColumnName = "person_id")
    private Persons senderId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "getter_id", referencedColumnName = "person_id")
    private Persons getterId;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "date_time")
    private Timestamp dateTime;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_id", referencedColumnName = "chat_id")
    private Chats chatId;
}

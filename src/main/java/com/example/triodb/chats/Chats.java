package com.example.triodb.chats;

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
@Table(name="chats")
public class Chats {
    @Id
    @Column(name = "chat_id", nullable = false)
    @SequenceGenerator(
            name = "chats_sequence",
            sequenceName = "chats_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "chats_sequence"
    )
    private Long chatId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person1_id", referencedColumnName = "person_id")
    private Persons person1Id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person2_id", referencedColumnName = "person_id")
    private Persons person2Id;
    @Basic
    @Column(name = "last_message")
    private String lastMessage;
    @Basic
    @Column(name = "last_date")
    private Timestamp lastDate;
}

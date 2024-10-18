package com.example.triodb.event_types;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="event_types")
public class EventTypes {
    @Id
    @Column(name = "eventt_id", nullable = false)
    @SequenceGenerator(
            name = "eventt_sequence",
            sequenceName = "eventt_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "eventt_sequence"
    )
    private Long eventtId;
    @Basic
    @Column(name = "eventt_name")
    private String eventTypeName;
}

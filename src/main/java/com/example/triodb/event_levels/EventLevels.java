package com.example.triodb.event_levels;

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
@Table(name="event_levels")
public class EventLevels {
    @Id
    @Column(name = "eventl_id", nullable = false)
    @SequenceGenerator(
            name = "eventl_sequence",
            sequenceName = "eventl_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "eventl_sequence"
    )
    private Long eventlId;
    @Basic
    @Column(name = "level_name", nullable = true)
    private String levelName;
    @Basic
    @Column(name = "color_code", nullable = true)
    private Integer colorCode;
}

package com.example.triodb.waste_groups;

import com.example.triodb.event_types.EventTypes;
import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="waste_groups")
public class WasteGroups {
    @Id
    @Column(name = "wasteg_id", nullable = false)
    @SequenceGenerator(
            name = "wasteg_seq",
            sequenceName = "wasteg_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wasteg_seq"
    )
    private Long wastegId;
    @Basic
    @Column(name = "wasteg_name")
    private String wastegName;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "eventt_id", referencedColumnName = "eventt_id")
    private EventTypes eventType;
}

package com.example.triodb.waste_types;

import com.example.triodb.waste_groups.WasteGroups;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="waste_types")
public class WasteTypes {
    @Id
    @Column(name = "wastet_id", nullable = false)
    @SequenceGenerator(
            name = "wastet_sequence",
            sequenceName = "wastet_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wastet_sequence"
    )
    private Long wastetId;
    @Basic
    @Column(name = "wastet_name")
    private String wastetName;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wasteg_id", referencedColumnName = "wasteg_id")
    private WasteGroups wasteGroup;
}

package com.example.triodb.equipment_types;

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
@Table(name="equipment_types")
public class EquipmentTypes {
    @Id
    @Column(name = "equipmentt_id", nullable = false)
    @SequenceGenerator(
            name = "equipmentt_sequence",
            sequenceName = "equipmentt_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "equipmentt_sequence"
    )
    private Long equipmenttId;
    @Basic
    @Column(name = "equipment_part_id")
    private Long equipmentPartId;
    @Basic
    @Column(name = "type_name", nullable = true)
    private String typeName;
}

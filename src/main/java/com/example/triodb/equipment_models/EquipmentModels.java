package com.example.triodb.equipment_models;

import com.example.triodb.equipment_types.EquipmentTypes;
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
@Table(name="equipment_models")
public class EquipmentModels {
    @Id
    @Column(name = "equipmentm_id", nullable = false)
    @SequenceGenerator(
            name = "equipmentm_sequence",
            sequenceName = "equipmentm_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "equipmentm_sequence"
    )
    private Long equipmentmId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipmentt_id", referencedColumnName = "equipmentt_id")
    private EquipmentTypes equipmenttId;
    @Basic
    @Column(name = "model_name", nullable = true)
    private String modelName;
    @Basic
    @Column(name = "image_name", nullable = true)
    private String ImageName;
}

package com.example.triodb.maintenance_types;

import com.example.triodb.equipment_models.EquipmentModels;
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
@Table(name="maintenance_types")
public class MaintenanceTypes {
    @Id
    @Column(name = "maintenancet_id", nullable = false)
    @SequenceGenerator(
            name = "maint_sequence",
            sequenceName = "maint_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "maint_sequence"
    )
    private Long maintenancetId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipmentm_id", referencedColumnName = "equipmentm_id")
    private EquipmentModels equipmentmId;
    @Basic
    @Column(name = "number", nullable = true)
    private Integer number;
}

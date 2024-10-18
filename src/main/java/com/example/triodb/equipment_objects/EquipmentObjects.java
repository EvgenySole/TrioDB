package com.example.triodb.equipment_objects;

import com.example.triodb.equipment_history.EquipmentHistory;
import com.example.triodb.equipment_models.EquipmentModels;
import com.example.triodb.persons.Persons;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="equipment_objects")
public class EquipmentObjects {
    @Id
    @Column(name = "equipmento_id", nullable = false)
    @SequenceGenerator(
            name = "equipmento_sequence",
            sequenceName = "equipmento_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "equipmento_sequence"
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Long equipmentoId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipmentm_id", referencedColumnName = "equipmentm_id")
    private EquipmentModels equipmentmId;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipmento_id", referencedColumnName = "equipmento_id")
    private List<EquipmentHistory> equipmenthId;
    @Basic
    @Column(name = "inventory_num", nullable = true)
    private Integer inventoryNum;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Persons person;
    @Basic
    @Column(name = "note", nullable = true)
    private String note;
}

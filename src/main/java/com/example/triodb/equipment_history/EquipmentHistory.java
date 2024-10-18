package com.example.triodb.equipment_history;

import com.example.triodb.departments.Departments;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="equipment_history")
public class EquipmentHistory {
    @Id
    @Column(name = "equipmenth_id", nullable = false)
    @SequenceGenerator(
            name = "equipmenth_sequence",
            sequenceName = "equipmenth_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "equipmenth_sequence"
    )
    private Long equipmenthId;
    @Basic
    @Column(name = "equipmento_id", nullable = true)
    private Long equipmentoId;
    @Basic
    @Column(name = "equipment_part_id", nullable = true)
    private Long equipmentoPartId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private Departments departmentId;
    @Basic
    @Column(name = "beg_date", nullable = true)
    private Date begDate;
    @Basic
    @Column(name = "end_date", nullable = true)
    private Date endDate;
}

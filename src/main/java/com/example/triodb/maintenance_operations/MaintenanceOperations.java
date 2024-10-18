package com.example.triodb.maintenance_operations;

import com.example.triodb.maintenance_types.MaintenanceTypes;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="maintenance_operations")
public class MaintenanceOperations {
    @Id
    @Column(name = "maintenanceo_id", nullable = false)
    @SequenceGenerator(
            name = "main_oper_sequence",
            sequenceName = "main_oper_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "main_oper_sequence"
    )
    private Long maintenanceoId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maintenancet_id", referencedColumnName = "maintenancet_id")
    private MaintenanceTypes maintenancetId;
    @Basic
    @Column(name = "number", nullable = true)
    private Integer number;
    @Basic
    @Column(name = "mainten_name", nullable = true)
    private String maintenName;
    @Basic
    @Column(name = "note", nullable = true)
    private String note;
    @Basic
    @Column(name = "photo_link", nullable = true)
    private String photoLink;
}

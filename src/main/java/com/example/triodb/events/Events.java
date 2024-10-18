package com.example.triodb.events;

import com.example.triodb.departments.Departments;
import com.example.triodb.equipment_objects.EquipmentObjects;
import com.example.triodb.event_levels.EventLevels;
import com.example.triodb.event_types.EventTypes;
import com.example.triodb.persons.Persons;
import com.example.triodb.tasks.Tasks;
import com.example.triodb.waste_groups.WasteGroups;
import com.example.triodb.waste_types.WasteTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="events")
public class Events {
    @Id
    @Column(name = "event_id", nullable = false)
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_sequence"
    )
    private Long eventId;
    @Basic
    @Column(name = "date_time", nullable = true)
    private Timestamp dateTime;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipmento_id", referencedColumnName = "equipmento_id")
    private EquipmentObjects equipmentoId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="person_id", referencedColumnName = "person_id")
    private Persons personId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private Tasks taskId;
    @Basic
    @Column(name = "note")
    private String note;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "eventl_id", referencedColumnName = "eventl_id")
    private EventLevels eventlId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "eventt_id", referencedColumnName = "eventt_id")
    private EventTypes eventtId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wasteg_id", referencedColumnName = "wasteg_id")
    private WasteGroups wastegId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wastet_id", referencedColumnName = "wastet_id")
    private WasteTypes wastetId;
    @Basic
    @Column(name = "image_name")
    private String imageName;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private Departments departmentId;
}

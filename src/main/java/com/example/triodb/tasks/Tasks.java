package com.example.triodb.tasks;

import com.example.triodb.equipment_objects.EquipmentObjects;
import com.example.triodb.persons.Persons;
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
@Table(name="tasks")
public class Tasks {
    @Id
    @Column(name = "task_id", nullable = false)
    @SequenceGenerator(
            name = "tasks_sequence",
            sequenceName = "tasks_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tasks_sequence"
    )
    private Long taskId;
    @Basic
    @Column(name = "event_id", nullable = true)
    private Long eventId;
    @Basic
    @Column(name = "date_time", nullable = true)
    private Timestamp dateTime;
    @Basic
    @Column(name = "order_text", nullable = true)
    private String orderText;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "head_person_id", referencedColumnName = "person_id")
    private Persons headPersonId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "target_worker_id", referencedColumnName = "person_id")
    private Persons targetWorkerId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipmento_id", referencedColumnName = "equipmento_id")
    private EquipmentObjects equipmentoId;
    @Basic
    @Column(name = "done", nullable = true)
    private String done;
}

package com.example.triodb.departments_history;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="departments_history")
public class DepartmentsHistory {
    @Id
    @Column(name = "departmenth_id", nullable = false)
    @SequenceGenerator(
            name = "departmenth_sequence",
            sequenceName = "departmenth_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "departmenth_sequence"
    )
    private Long departmenthId;
    @Basic
    @Column(name = "department_id", nullable = true)
    private Long departmentId;
    @Basic
    @Column(name = "department_name", nullable = true)
    private String departmentName;
    @Basic
    @Column(name = "head_worker_id", nullable = true)
    private Long headWorkerId;
    @Basic
    @Column(name = "par_dep_id", nullable = true)
    private Long parDepId;
    @Basic
    @Column(name = "beg_date", nullable = true)
    private Date begDate;
    @Basic
    @Column(name = "end_date", nullable = true)
    private Date endDate;
}

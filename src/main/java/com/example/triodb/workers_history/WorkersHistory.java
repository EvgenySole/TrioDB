package com.example.triodb.workers_history;

import com.example.triodb.departments.Departments;
import com.example.triodb.persons.Persons;
import com.example.triodb.posts.Posts;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="workers_history")
public class WorkersHistory {
    @Id
    @Column(name = "workerh_id", nullable = false)
    @SequenceGenerator(
            name = "workersh_sequence",
            sequenceName = "workersh_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "workersh_sequence"
    )
    private Long workerhId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Persons personId;
    @Basic
    @Column(name = "enter_num", nullable = true)
    private Integer enterNum;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    private Posts postId;
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

package com.example.triodb.persons_history;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="persons_history")
public class PersonsHistory {
    @Id
    @Column(name = "personh_id", nullable = false)
    @SequenceGenerator(
            name = "persons_sequence",
            sequenceName = "persons_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "persons_sequence"
    )
    private Long personhId;
    @Basic
    @Column(name = "person_real_id", nullable = true)
    private Long personRealId;
    @Basic
    @Column(name = "name", nullable = true)
    private String name;
    @Basic
    @Column(name = "passport", nullable = true)
    private String passport;
    @Basic
    @Column(name = "beg_date", nullable = true)
    private Date begDate;
    @Basic
    @Column(name = "end_date", nullable = true)
    private Date endDate;
}

package com.example.triodb.persons;

import com.example.triodb.persons_history.PersonsHistory;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="persons")
public class Persons {
    @Id
    @Column(name = "person_id", nullable = false)
    @SequenceGenerator(
            name = "persons_sequence",
            sequenceName = "persons_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "persons_sequence"
    )
    private Long personId;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_real_id", referencedColumnName = "person_id")
    private List<PersonsHistory> personRealId;
    @Basic
    @Column(name = "gender")
    private Integer gender;
    @Basic
    @Column(name = "birthdate")
    private Date birthdate;
    @Basic
    @Column(name = "login")
    private String login;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "role")
    private String role;
    @Basic
    @Column(name = "active")
    private Boolean active;
    @Basic
    @Column(name = "avatar")
    private String avatar;
}

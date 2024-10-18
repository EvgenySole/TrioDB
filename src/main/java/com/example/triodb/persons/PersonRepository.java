package com.example.triodb.persons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Persons, Long> {
    Persons findByLoginIgnoreCase(String loginId);
    Boolean existsByLogin(String login);
    Persons findPersonsByPersonId(Long personId);
}

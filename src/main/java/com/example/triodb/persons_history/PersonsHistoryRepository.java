package com.example.triodb.persons_history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonsHistoryRepository extends JpaRepository<PersonsHistory, Long> {
    PersonsHistory findByName(String name);
    List<PersonsHistory> findAllByPersonRealId(Long personId);
    @Query("SELECT s FROM PersonsHistory s WHERE s.personhId = ?1")
    Optional<PersonsHistory> getAllByPersonhId();
}

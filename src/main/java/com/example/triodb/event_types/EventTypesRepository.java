package com.example.triodb.event_types;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EventTypesRepository extends JpaRepository<EventTypes, Long> {
    @Query("SELECT s FROM EventTypes s WHERE s.eventtId = ?1")
    Optional<EventTypes> findEventTypesById(String equipmentmId);
}

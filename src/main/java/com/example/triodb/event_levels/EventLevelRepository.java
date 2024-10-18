package com.example.triodb.event_levels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EventLevelRepository extends JpaRepository<EventLevels, Long> {
    @Query("SELECT s FROM EventLevels s WHERE s.eventlId = ?1")
    Optional<EventLevels> findEventLevelsById(String equipmentmId);
}

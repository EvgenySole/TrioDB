package com.example.triodb.workers_history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface WorkersHistoryRepository extends JpaRepository<WorkersHistory, Long> {
    @Query("SELECT s FROM WorkersHistory s WHERE s.workerhId = ?1")
    Optional<WorkersHistory> findEquipmentHistoryById(String departmentId);
}

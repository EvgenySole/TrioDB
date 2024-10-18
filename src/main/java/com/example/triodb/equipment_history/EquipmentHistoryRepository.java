package com.example.triodb.equipment_history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EquipmentHistoryRepository extends JpaRepository<EquipmentHistory, Long> {
    @Query("SELECT s FROM EquipmentHistory s WHERE s.equipmenthId = ?1")
    Optional<EquipmentHistory> findEquipmentHistoryById(String departmentId);
    @Query("SELECT s FROM EquipmentHistory s WHERE s.equipmentoId = ?1")
    EquipmentHistory findEquipmentHistoryByEquipmentoId(String equipmentoId);
}

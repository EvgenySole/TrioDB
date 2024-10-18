package com.example.triodb.maintenance_operations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MaintenanceOperationRepository extends JpaRepository<MaintenanceOperations, Long> {
    @Query("SELECT s FROM MaintenanceOperations s WHERE s.maintenanceoId = ?1")
    Optional<MaintenanceOperations> findEquipmentHistoryById(String departmentId);
}

package com.example.triodb.maintenance_types;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MaintenanceTypeRepository extends JpaRepository<MaintenanceTypes, Long> {
    @Query("SELECT s FROM MaintenanceTypes s WHERE s.maintenancetId = ?1")
    Optional<MaintenanceTypes> findEventTypesById(String equipmentmId);
}

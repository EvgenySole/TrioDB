package com.example.triodb.equipment_types;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EquipmentTypeRepository extends JpaRepository<EquipmentTypes, Long> {
    @Query("SELECT s FROM EquipmentTypes s WHERE s.equipmenttId = ?1")
    Optional<EquipmentTypes> findEquipmentTypesById(String equipmentmId);
}

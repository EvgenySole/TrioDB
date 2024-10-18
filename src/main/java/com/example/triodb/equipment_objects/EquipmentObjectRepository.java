package com.example.triodb.equipment_objects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EquipmentObjectRepository extends JpaRepository<EquipmentObjects, Long> {
    @Query("SELECT s FROM EquipmentObjects s WHERE s.equipmentoId = ?1")
    Optional<EquipmentObjects> findEquipmentObjectsById(String equipmentoId);
    EquipmentObjects getByInventoryNum(Integer invNum);
}

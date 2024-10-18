package com.example.triodb.equipment_models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentModelRepository extends JpaRepository<EquipmentModels, Long> {
    //@Query("SELECT s FROM EquipmentObjects s WHERE s.equipmentoId = ?1")
    @Query("SELECT s FROM EquipmentModels s WHERE s.equipmentmId = ?1")
    Optional<EquipmentModels> findEquipmentModelsById(String equipmentmId);
//    @Query("SELECT equipmento_id, model_name, inventory_num FROM EquipmentModels AS obj_id JOIN EquipmentModels AS mod_id\n" +
//            "  ON obj_id.equipmentm_id = mod_id.equipmentm_id")
//    Optional<EquipmentModels> findEquipmentObjectByName(String model_name);
//    @Query("SELECT equipmento_id, model_name, inventory_num FROM equipment_objects AS obj_id JOIN equipment_models AS mod_id\n" +
//            "  ON obj_id.equipmentm_id = mod_id.equipmentm_id")
//    Optional<EquipmentObjects> findEquipmentObjectByName(String model_name);
}

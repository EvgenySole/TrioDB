package com.example.triodb.equipment_types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class EquipmentTypeService {

    public final EquipmentTypeRepository equipmentTypeRepository;

    @Autowired
    public EquipmentTypeService(EquipmentTypeRepository equipmentModelRepository) {
        this.equipmentTypeRepository = equipmentModelRepository;
    }
    public List<EquipmentTypes> getEquipmentTypes() {
        return equipmentTypeRepository.findAll();
    }

    public List<EquipmentTypes> getEquipmentType(Long equipmentTypeId) {
        boolean exists = equipmentTypeRepository.existsById(equipmentTypeId);
        if (!exists){
            throw new IllegalStateException("EquipmentType with id " + equipmentTypeId + " doesn't exsist");
        }
        return equipmentTypeRepository.findAllById(Collections.singleton(equipmentTypeId));
    }

    public void addNewEquipmentType(EquipmentTypes equipmentType){
        equipmentTypeRepository.save(equipmentType);
        System.out.println(equipmentType);
    }

    public void deleteEquipmentType(Long equipmentTypeId){
        boolean exists = equipmentTypeRepository.existsById(equipmentTypeId);
        if (!exists){
            throw new IllegalStateException("EquipmentType with id " + equipmentTypeId + " doesn't exsist");
        }
        equipmentTypeRepository.deleteById(equipmentTypeId);
        System.out.println(equipmentTypeId);
    }

//    @Transactional
//    public void updateEquipmentType(Long equipmenttId, Long equipmentPartId, String typeName) {
//        EquipmentTypes equipmentObject = equipmentTypeRepository.findById(equipmenttId).orElseThrow(
//                () -> new IllegalStateException("EquipmentType with id " + equipmenttId +
//                        "doesn't exists")
//        );
//
//        if (equipmenttId != null  && !Objects.equals(equipmentObject.getEquipmenttId(), equipmenttId)) {
//            equipmentObject.setEquipmenttId(equipmenttId);
//        }
//
//        if (equipmentPartId != null && !Objects.equals(equipmentObject.getEquipmentPartId(), equipmentPartId)) {
//            equipmentObject.setEquipmentPartId(equipmentPartId);
//        }
//
//        if (typeName != null && !Objects.equals(equipmentObject.getModelName(), typeName)) {
//            equipmentObject.setModelName(typeName);
//        }
//    }

}

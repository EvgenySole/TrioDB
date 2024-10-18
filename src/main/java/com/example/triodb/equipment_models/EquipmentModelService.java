package com.example.triodb.equipment_models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class EquipmentModelService {

    public final EquipmentModelRepository equipmentModelRepository;

    @Autowired
    public EquipmentModelService(EquipmentModelRepository equipmentModelRepository) {
        this.equipmentModelRepository = equipmentModelRepository;
    }
    public List<EquipmentModels> getEquipmentModels() {
        return equipmentModelRepository.findAll();
    }

    public List<EquipmentModels> getEquipmentModel(Long equipmentModelId) {
        boolean exists = equipmentModelRepository.existsById(equipmentModelId);
        if (!exists){
            throw new IllegalStateException("EquipmentModel with id " + equipmentModelId + " doesn't exsist");
        }
        return equipmentModelRepository.findAllById(Collections.singleton(equipmentModelId));
    }

    public void addNewEquipmentModel(EquipmentModels equipmentModel){
        equipmentModelRepository.save(equipmentModel);
        System.out.println(equipmentModel);
    }

    public void deleteEquipmentModel(Long equipmentModelId){
        boolean exists = equipmentModelRepository.existsById(equipmentModelId);
        if (!exists){
            throw new IllegalStateException("EquipmentModel with id " + equipmentModelId + " doesn't exsist");
        }
        equipmentModelRepository.deleteById(equipmentModelId);
        System.out.println(equipmentModelId);
    }

//    @Transactional
//    public void updateEquipmentModel(Long equipmentmId, Long equipmenttId, String model_name) {
//        EquipmentModels equipmentObject = equipmentModelRepository.findById(equipmentmId).orElseThrow(
//                () -> new IllegalStateException("EquipmentModel with id " + equipmentmId +
//                        "doesn't exists")
//        );
//
//        if (equipmentmId != null  && !Objects.equals(equipmentObject.getEquipmenttId(), equipmenttId)) {
//            equipmentObject.setEquipmenttId(equipmenttId);
//        }
//
//        if (equipmenttId != null && !Objects.equals(equipmentObject.getEquipmentmId(), equipmenttId)) {
//            equipmentObject.setEquipmentmId(equipmenttId);
//        }
//
//        if (model_name != null && !Objects.equals(equipmentObject.getModelName(), model_name)) {
//            equipmentObject.setModelName(model_name);
//        }
//    }

}

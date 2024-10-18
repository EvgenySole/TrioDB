package com.example.triodb.equipment_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class EquipmentHistoryService {

    public final EquipmentHistoryRepository equipmentHistoryRepository;

    @Autowired
    public EquipmentHistoryService(EquipmentHistoryRepository equipmentHistoryRepository) {
        this.equipmentHistoryRepository = equipmentHistoryRepository;
    }
    public List<EquipmentHistory> getEquipmentHistory() {
        return equipmentHistoryRepository.findAll();
    }

    public List<EquipmentHistory> getEquipmentHistory(Long equipmentHistoryId) {
        boolean exists = equipmentHistoryRepository.existsById(equipmentHistoryId);
        if (!exists){
            throw new IllegalStateException("EquipmentHistory with id " + equipmentHistoryId + " doesn't exsist");
        }
        return equipmentHistoryRepository.findAllById(Collections.singleton(equipmentHistoryId));
    }

    public void addNewEquipmentHistory(EquipmentHistory equipmentHistory){
        equipmentHistoryRepository.save(equipmentHistory);
        System.out.println(equipmentHistory);
    }

    public void deleteEquipmentHistory(Long equipmentHistoryId){
        boolean exists = equipmentHistoryRepository.existsById(equipmentHistoryId);
        if (!exists){
            throw new IllegalStateException("EquipmentHistory with id " + equipmentHistoryId + " doesn't exsist");
        }
        equipmentHistoryRepository.deleteById(equipmentHistoryId);
        System.out.println(equipmentHistoryId);
    }

    @Transactional
    public void updateEquipmentHistory(Long equipmenthId, Long equipmentoId, Long equipmentoPartId,
                                         Long departmentId, Date begDate, Date endDate) {
        EquipmentHistory departmentHistory = equipmentHistoryRepository.findById(equipmenthId).orElseThrow(
                () -> new IllegalStateException("EquipmentHistory with id " + equipmenthId +
                        "doesn't exists")
        );

//        if (equipmenthId != null  && !Objects.equals(departmentHistory.getDepartmentId(), equipmenthId)) {
//            departmentHistory.setEquipmenthId(equipmenthId);
//        }
//        if (equipmentoId != null  && !Objects.equals(departmentHistory.getEquipmentoId(), equipmentoId)) {
//            departmentHistory.setEquipmentoId(equipmentoId);
//        }
//        if (equipmentoPartId != null  && !Objects.equals(departmentHistory.getEquipmentoPartId(), equipmentoPartId)) {
//            departmentHistory.setEquipmentoPartId(equipmentoPartId);
//        }
//        if (departmentId != null  && !Objects.equals(departmentHistory.getDepartmentId(), departmentId)) {
//            departmentHistory.setDepartmentId(departmentId);
//        }
//        if (begDate != null  && !Objects.equals(departmentHistory.getBegDate(), begDate)) {
//            departmentHistory.setBegDate(begDate);
//        }
//        if (endDate != null  && !Objects.equals(departmentHistory.getEndDate(), endDate)) {
//            departmentHistory.setEndDate(endDate);
//        }
    }

}

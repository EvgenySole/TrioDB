package com.example.triodb.equipment_objects;

import com.example.triodb.departments.DepartmentRepository;
import com.example.triodb.departments.Departments;
import com.example.triodb.departments_history.DepartmentsHistory;
import com.example.triodb.equipment_history.EquipmentHistory;
import com.example.triodb.equipment_history.EquipmentHistoryRepository;
import com.example.triodb.equipment_models.EquipmentModelRepository;
import com.example.triodb.equipment_models.EquipmentModels;
import com.example.triodb.persons.PersonRepository;
import com.example.triodb.persons.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;

@Service
public class EquipmentObjectService {

    public final EquipmentObjectRepository equipmentObjectRepository;
    public final EquipmentModelRepository equipmentModelRepository;
    public final PersonRepository personRepository;
    public final DepartmentRepository departmentRepository;
    public final EquipmentHistoryRepository equipmentHistoryRepository;

    @Autowired
    public EquipmentObjectService(EquipmentObjectRepository equipmentObjectRepository, EquipmentModelRepository equipmentModelRepository, PersonRepository personRepository, DepartmentRepository departmentRepository, EquipmentHistoryRepository equipmentHistoryRepository) {
        this.equipmentObjectRepository = equipmentObjectRepository;
        this.equipmentModelRepository = equipmentModelRepository;
        this.personRepository = personRepository;
        this.departmentRepository = departmentRepository;
        this.equipmentHistoryRepository = equipmentHistoryRepository;
    }
    public List<EquipmentObjects> getEquipmentObjects() {
        return equipmentObjectRepository.findAll();
    }
    public List<EquipmentObjects> getEquipmentObjectsByPersonId(Long personId) {
        List<EquipmentObjects> allObjects = equipmentObjectRepository.findAll();
        List<EquipmentObjects> targetObjects = new ArrayList<>();
        for (int i = 0; i < allObjects.size(); i++){
            if (allObjects.get(i).getPerson().getPersonId() == personId){
                targetObjects.add(allObjects.get(i));
            }
        }
//        if (targetEvents.isEmpty()){
//            throw new IllegalStateException("Eq Objects with person id " + personId + " doesn't exist");
//        }
        return targetObjects;
    }
    public List<EquipmentObjects> getEquipmentObject(Long equipmentObjectId) {
        boolean exists = equipmentObjectRepository.existsById(equipmentObjectId);
        if (!exists){
            throw new IllegalStateException("EquipmentObject with id " + equipmentObjectId + " doesn't exist");
        }
        return equipmentObjectRepository.findAllById(Collections.singleton(equipmentObjectId));
    }

    public ResponseEntity<String> addNewEquipmentObject(EquipmentObjects equipmentObject){
        equipmentObjectRepository.save(equipmentObject);
        EquipmentObjects equipmentObject2 = equipmentObjectRepository.getByInventoryNum(equipmentObject.getInventoryNum());
        List<EquipmentHistory> equipmenthId = new ArrayList<>();
        EquipmentHistory equipmentHistory = new EquipmentHistory();
        equipmentHistory.setEquipmentoId(equipmentObject2.getEquipmentoId());
        equipmentHistory.setEquipmentoPartId(equipmentObject2.getEquipmentoId());
        equipmentHistory.setBegDate(Date.valueOf("2015-12-10"));
        equipmentHistory.setEndDate(Date.valueOf("2150-07-16"));
        equipmentHistory.setDepartmentId(equipmentObject.getEquipmenthId().get(0).getDepartmentId());
        equipmentHistoryRepository.save(equipmentHistory);
        equipmenthId.add(equipmentHistory);
        equipmentObject2.setEquipmenthId(equipmenthId);


        System.out.println(equipmentObject);
        return ResponseEntity.ok("{\"line\":\"Object saved!\"}");
    }

    public void deleteEquipmentObject(Long equipmentObjectId){
        boolean exists = equipmentObjectRepository.existsById(equipmentObjectId);
        if (!exists){
            throw new IllegalStateException("EquipmentObject with id " + equipmentObjectId + " doesn't exsist");
        }
        equipmentObjectRepository.deleteById(equipmentObjectId);
        System.out.println(equipmentObjectId);
    }



    @Transactional
    public void updateEquipmentObject(Long equipmentoId, Long equipmentmId, Integer inventoryNum,
                                      Long personId, Long departmentId, String note) {
        EquipmentObjects equipmentObject = equipmentObjectRepository.findById(equipmentoId).orElseThrow(
                () -> new IllegalStateException("EquipmentObject with id " + equipmentoId +
                        "doesn't exists")
        );

        Optional<EquipmentModels> equipmentModels = equipmentModelRepository.findEquipmentModelsById(String.valueOf(equipmentmId));
        if (equipmentModels.get() != null  && !Objects.equals(equipmentObject.getEquipmentmId(), equipmentModels.get())) {
            equipmentObject.setEquipmentmId(equipmentModels.get());
        }

        if (inventoryNum != null && !Objects.equals(equipmentObject.getInventoryNum(), inventoryNum)) {
            equipmentObject.setInventoryNum(inventoryNum);
        }

        if (note != null && !Objects.equals(equipmentObject.getNote(), note)) {
            equipmentObject.setNote(note);
        }

        Persons persons = personRepository.findPersonsByPersonId(personId);
        if (persons != null && !Objects.equals(equipmentObject.getPerson(), persons)) {
            equipmentObject.setPerson(persons);
        }

        EquipmentHistory equipmentHistory = equipmentHistoryRepository
                .findEquipmentHistoryByEquipmentoId(String.valueOf(equipmentoId));
        if (equipmentHistory != null && !Objects.equals(equipmentObject.getEquipmenthId(), List.of(equipmentHistory))) {
            Departments departments = departmentRepository
                    .findDepartmentsById(String.valueOf(departmentId));
            if (departments != null && !Objects.equals(equipmentObject.getEquipmenthId().get(0).getDepartmentId(), departments)) {
                equipmentHistory.setDepartmentId(departments);
                equipmentObject.setEquipmenthId(List.of(equipmentHistory));
            }

        }

//        if (departments != null && !Objects.equals(equipmentObject.getEquipmenthId().get(0).getDepartmentId(), departments)) {
//            //equipmentObject.getEquipmenthId().get(0).setDepartmentId(departments.get());
//        }
    }

}

package com.example.triodb.maintenance_operations;

import com.example.triodb.maintenance_types.MaintenanceTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class MaintenanceOperationService {

    public final MaintenanceOperationRepository maintenanceOperationRepository;

    @Autowired
    public MaintenanceOperationService(MaintenanceOperationRepository maintenanceOperationRepository) {
        this.maintenanceOperationRepository = maintenanceOperationRepository;
    }
    public List<MaintenanceOperations> getMaintenanceOperations() {
        return maintenanceOperationRepository.findAll();
    }

    public List<MaintenanceOperations> getMaintenanceOperation(Long maintenanceoId) {
        boolean exists = maintenanceOperationRepository.existsById(maintenanceoId);
        if (!exists){
            throw new IllegalStateException("MaintenanceOperation with id " + maintenanceoId + " doesn't exsist");
        }
        return maintenanceOperationRepository.findAllById(Collections.singleton(maintenanceoId));
    }

    public void addNewMaintenanceOperation(MaintenanceOperations maintenanceOperation){
        maintenanceOperationRepository.save(maintenanceOperation);
        System.out.println(maintenanceOperation);
    }

    public void deleteMaintenanceOperation(Long maintenanceoId){
        boolean exists = maintenanceOperationRepository.existsById(maintenanceoId);
        if (!exists){
            throw new IllegalStateException("MaintenanceOperation with id " + maintenanceoId + " doesn't exsist");
        }
        maintenanceOperationRepository.deleteById(maintenanceoId);
        System.out.println(maintenanceoId);
    }

    @Transactional
    public void updateEquipmentHistory(Long maintenanceoId, MaintenanceTypes maintenancetId, Integer number,
                                       String maintenName, String note, String photoLink) {
        MaintenanceOperations departmentHistory = maintenanceOperationRepository.findById(maintenanceoId).orElseThrow(
                () -> new IllegalStateException("MaintenanceOperation with id " + maintenanceoId +
                        "doesn't exists")
        );

        if (maintenanceoId != null  && !Objects.equals(departmentHistory.getMaintenanceoId(), maintenanceoId)) {
            departmentHistory.setMaintenanceoId(maintenanceoId);
        }
        if (maintenancetId != null  && !Objects.equals(departmentHistory.getMaintenancetId(), maintenancetId)) {
            departmentHistory.setMaintenancetId(maintenancetId);
        }
        if (number != null  && !Objects.equals(departmentHistory.getNumber(), number)) {
            departmentHistory.setNumber(number);
        }
        if (maintenName != null  && !Objects.equals(departmentHistory.getMaintenName(), maintenName)) {
            departmentHistory.setMaintenName(maintenName);
        }
        if (note != null  && !Objects.equals(departmentHistory.getNote(), note)) {
            departmentHistory.setNote(note);
        }
        if (photoLink != null  && !Objects.equals(departmentHistory.getPhotoLink(), photoLink)) {
            departmentHistory.setPhotoLink(photoLink);
        }
    }
}

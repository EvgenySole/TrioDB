package com.example.triodb.maintenance_types;

import com.example.triodb.equipment_models.EquipmentModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class MaintenanceTypeService {

    public final MaintenanceTypeRepository maintenanceTypeRepository;

    @Autowired
    public MaintenanceTypeService(MaintenanceTypeRepository maintenanceTypeRepository) {
        this.maintenanceTypeRepository = maintenanceTypeRepository;
    }
    public List<MaintenanceTypes> getMaintenanceTypes() {
        return maintenanceTypeRepository.findAll();
    }

    public List<MaintenanceTypes> getMaintenanceType(Long eventlId) {
        boolean exists = maintenanceTypeRepository.existsById(eventlId);
        if (!exists){
            throw new IllegalStateException("MaintenanceType with id " + eventlId + " doesn't exsist");
        }
        return maintenanceTypeRepository.findAllById(Collections.singleton(eventlId));
    }

    public void addNewMaintenanceType(MaintenanceTypes maintenanceTypes){
        maintenanceTypeRepository.save(maintenanceTypes);
        System.out.println(maintenanceTypes);
    }

    public void deleteMaintenanceType(Long eventtId){
        boolean exists = maintenanceTypeRepository.existsById(eventtId);
        if (!exists){
            throw new IllegalStateException("MaintenanceType with id " + eventtId + " doesn't exsist");
        }
        maintenanceTypeRepository.deleteById(eventtId);
        System.out.println(eventtId);
    }

    @Transactional
    public void updateMaintenanceType(Long maintenancetId, EquipmentModels equipmentmId, Integer number) {
        MaintenanceTypes maintenanceTypes = maintenanceTypeRepository.findById(maintenancetId).orElseThrow(
                () -> new IllegalStateException("MaintenanceType with id " + maintenancetId +
                        "doesn't exists")
        );

        if (maintenancetId != null  && !Objects.equals(maintenanceTypes.getMaintenancetId(), maintenancetId)) {
            maintenanceTypes.setMaintenancetId(maintenancetId);
        }

        if (equipmentmId != null && !Objects.equals(maintenanceTypes.getEquipmentmId(), equipmentmId)) {
            maintenanceTypes.setEquipmentmId(equipmentmId);
        }

        if (number != null && !Objects.equals(maintenanceTypes.getNumber(), number)) {
            maintenanceTypes.setNumber(number);
        }
    }
}

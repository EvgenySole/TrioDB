package com.example.triodb.maintenance_types;

import com.example.triodb.equipment_models.EquipmentModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "maintenance_types")
public class MaintenanceTypeController {
    public final MaintenanceTypeService maintenanceTypeService;
    @Autowired
    public MaintenanceTypeController(MaintenanceTypeService maintenanceTypeService) {
        this.maintenanceTypeService = maintenanceTypeService;
    }

    @GetMapping
    public List<MaintenanceTypes> getMaintenanceTypes(){
        return maintenanceTypeService.getMaintenanceTypes();
    }

    @GetMapping(path ="{maintenance_typesId}")
    public List<MaintenanceTypes> getMaintenanceType(@PathVariable("maintenance_typesId") Long maintenanceTypesId){
        return maintenanceTypeService.getMaintenanceType(maintenanceTypesId);
    }
    @PostMapping
    public void registerNewMaintenanceType(@RequestBody MaintenanceTypes eventTypes){
        maintenanceTypeService.addNewMaintenanceType(eventTypes);
    }
    @DeleteMapping(path = "{maintenance_typesId}")
    public void deleteMaintenanceType(@PathVariable("maintenance_typesId") Long maintenanceTypesId){
        maintenanceTypeService.deleteMaintenanceType(maintenanceTypesId);
    }
    @PutMapping(path = "{maintenance_typesId}")
    public void updateMaintenanceType(
            @PathVariable("maintenance_typesId") Long maintenancetId,
            @RequestParam(required = false) EquipmentModels equipmentmId,
            @RequestParam(required = false) Integer number
    )
    {
        maintenanceTypeService.updateMaintenanceType(maintenancetId, equipmentmId, number);
    }
}

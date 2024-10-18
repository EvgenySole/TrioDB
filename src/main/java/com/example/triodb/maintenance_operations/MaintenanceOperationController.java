package com.example.triodb.maintenance_operations;

import com.example.triodb.maintenance_types.MaintenanceTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "maintenance_operations")
public class MaintenanceOperationController {
    public final MaintenanceOperationService maintenanceOperationService;
    @Autowired
    public MaintenanceOperationController(MaintenanceOperationService maintenanceOperationService) {
        this.maintenanceOperationService = maintenanceOperationService;
    }

    @GetMapping
    public List<MaintenanceOperations> getMaintenanceOperations(){
        return maintenanceOperationService.getMaintenanceOperations();
    }

    @GetMapping(path ="{maintenance_operationId}")
    public List<MaintenanceOperations> getMaintenanceOperation(@PathVariable("maintenance_operationId") Long maintenanceoId){
        return maintenanceOperationService.getMaintenanceOperation(maintenanceoId);
    }
    @PostMapping
    public void registerNewEquipmentHistory(@RequestBody MaintenanceOperations maintenanceOperation){
        maintenanceOperationService.addNewMaintenanceOperation(maintenanceOperation);
    }
    @DeleteMapping(path = "{maintenance_operationId}")
    public void deleteMaintenanceOperation(@PathVariable("maintenance_operationId") Long maintenanceoId){
        maintenanceOperationService.deleteMaintenanceOperation(maintenanceoId);
    }
    @PutMapping(path = "{maintenance_operationId}")
    public void updateMaintenanceOperation(
            @PathVariable("maintenance_operationId") Long maintenanceoId,
            @RequestParam(required = false) MaintenanceTypes maintenancetId,
            @RequestParam(required = false) Integer number,
            @RequestParam(required = false) String maintenName,
            @RequestParam(required = false) String note,
            @RequestParam(required = false) String photoLink
    )
    {
        maintenanceOperationService.updateEquipmentHistory(maintenanceoId, maintenancetId,
                number, maintenName, note, photoLink);
    }
}

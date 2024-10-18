package com.example.triodb.equipment_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "equipment_history")
public class EquipmentHistoryController {
    public final EquipmentHistoryService equipmentHistoryService;
    @Autowired
    public EquipmentHistoryController(EquipmentHistoryService equipmentHistoryService) {
        this.equipmentHistoryService = equipmentHistoryService;
    }

    @GetMapping
    public List<EquipmentHistory> getEquipmentHistory(){
        return equipmentHistoryService.getEquipmentHistory();
    }

    @GetMapping(path ="{equipmentHistoryId}")
    public List<EquipmentHistory> getEquipmentHistory(@PathVariable("equipmentHistoryId") Long departmentId){
        return equipmentHistoryService.getEquipmentHistory(departmentId);
    }
    @PostMapping
    public void registerNewEquipmentHistory(@RequestBody EquipmentHistory equipmentHistory){
        equipmentHistoryService.addNewEquipmentHistory(equipmentHistory);
    }
    @DeleteMapping(path = "{equipmentHistoryId}")
    public void deleteEquipmentHistory(@PathVariable("equipmentHistoryId") Long departmentsHistoryId){
        equipmentHistoryService.deleteEquipmentHistory(departmentsHistoryId);
    }
    @PutMapping(path = "{equipmentHistoryId}")
    public void updateEquipmentHistory(
            @PathVariable("equipmentHistoryId") Long equipmenthId,
            @RequestParam(required = false) Long equipmentoId,
            @RequestParam(required = false) Long equipmentoPartId,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Date begDate,
            @RequestParam(required = false) Date endDate
    )
    {
        equipmentHistoryService.updateEquipmentHistory(equipmenthId, equipmentoId,
                equipmentoPartId, departmentId, begDate, endDate);
    }



}

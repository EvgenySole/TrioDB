package com.example.triodb.equipment_types;

import com.example.triodb.equipment_models.EquipmentModelService;
import com.example.triodb.equipment_models.EquipmentModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "equipment_types")
public class EquipmentTypeController {
    public final EquipmentTypeService equipmentTypeService;
    @Autowired
    public EquipmentTypeController(EquipmentTypeService equipmentTypeService) {
        this.equipmentTypeService = equipmentTypeService;
    }

    @GetMapping
    public List<EquipmentTypes> getEquipmentTypes(){
        return equipmentTypeService.getEquipmentTypes();
    }

    @GetMapping(path ="{equipment_typeId}")
    public List<EquipmentTypes> getEquipmentType(@PathVariable("equipment_typeId") Long equipmentTypeID){
        return equipmentTypeService.getEquipmentType(equipmentTypeID);
    }
    @PostMapping
    public void registerNewEquipmentType(@RequestBody EquipmentTypes equipmentTypes){
        equipmentTypeService.addNewEquipmentType(equipmentTypes);
    }
    @DeleteMapping(path = "{equipment_typeId}")
    public void deleteEquipmentType(@PathVariable("equipment_typeId") Long equipmentTypeId){
        equipmentTypeService.deleteEquipmentType(equipmentTypeId);
    }
    @PutMapping(path = "{equipment_typeId}")
    public void updateEquipmentType(
            @PathVariable("equipment_typeId") Long equipmenttId,
            @RequestParam(required = false) Long equipmentPartId,
            @RequestParam(required = false) String typeName
    )
    {
        //equipmentTypeService.updateEquipmentType(equipmenttId, equipmentPartId, typeName);
    }



}

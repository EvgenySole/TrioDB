package com.example.triodb.equipment_models;

import com.example.triodb.equipment_types.EquipmentTypeService;
import com.example.triodb.equipment_types.EquipmentTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "equipment_models")
public class EquipmentModelController {
    public final EquipmentModelService equipmentModelService;
    public final EquipmentTypeService equipmentTypeService;
    @Autowired
    public EquipmentModelController(EquipmentModelService equipmentModelService, EquipmentTypeService equipmentTypeService) {
        this.equipmentModelService = equipmentModelService;
        this.equipmentTypeService = equipmentTypeService;
    }

    @GetMapping
    public List<EquipmentModels> getEquipmentModels(){
        return equipmentModelService.getEquipmentModels();
    }

    @GetMapping(path ="{equipment_modelId}")
    public List<EquipmentModels> getEquipmentModelId(@PathVariable("equipment_modelId") Long equipmentModelId){
        return equipmentModelService.getEquipmentModel(equipmentModelId);
    }
    @PostMapping
    public void registerNewEquipmentModel(@RequestBody EquipmentModels equipmentModel){
        //EquipmentTypes equipmentTypes = equipmentTypeService.getEquipmentType(equipmentModel.getEquipmenttId().getEquipmenttId()).get(0);
        //equipmentModel.setEquipmenttId(equipmentTypes);
        EquipmentTypes equipmentTypes = equipmentTypeService.getEquipmentType(equipmentModel.getEquipmenttId().getEquipmenttId()).get(0);
        equipmentModel.setEquipmenttId(equipmentTypes);
//        Map<String, Object> response = new HashMap<>();
//        response.put("id", equipmentModel.getEquipmentmId());
//        response.put("equT", equipmentModel.getEquipmenttId());
//        response.put("modelName", equipmentModel.getModelName());
//        return new ResponseEntity<>(response, HttpStatus.OK);
        equipmentModelService.addNewEquipmentModel(equipmentModel);
    }
    @DeleteMapping(path = "{equipment_modelId}")
    public void deleteEquipmentModel(@PathVariable("equipment_modelId") Long equipmentModelId){
        equipmentModelService.deleteEquipmentModel(equipmentModelId);
    }
    @PutMapping(path = "{equipment_modelId}")
    public void updateEquipmentModel(
            @PathVariable("equipment_modelId") Long equipmentmId,
            @RequestParam(required = false) Long equipmenttId,
            @RequestParam(required = false) String model_name
    )
    {
       // equipmentModelService.updateEquipmentModel(equipmentmId, equipmenttId, model_name);
    }



}

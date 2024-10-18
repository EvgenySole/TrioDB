package com.example.triodb.equipment_objects;

import com.example.triodb.equipment_history.EquipmentHistory;
import com.example.triodb.equipment_models.EquipmentModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "equipment_objects")
public class EquipmentObjectController {
    public final EquipmentObjectService equipmentObjectService;
    @Autowired
    public EquipmentObjectController(EquipmentObjectService equipmentObjectService) {
        this.equipmentObjectService = equipmentObjectService;
    }
    @GetMapping
    public List<EquipmentObjects> getEquipmentObjects(){
        List<EquipmentObjects> sortedEquipmentObjects = equipmentObjectService.getEquipmentObjects();
        return getEquipmentObjects(sortedEquipmentObjects);
    }
    @GetMapping(path ="{equipment_objectId}")
    public List<EquipmentObjects> getEquipmentObjectId(@PathVariable("equipment_objectId") Long equipmentObjectId){
        List<EquipmentObjects> sortedEquipmentObjects = equipmentObjectService.getEquipmentObject(equipmentObjectId);
        return getEquipmentObjects(sortedEquipmentObjects);
    }
    @GetMapping(path = "/person/{personId}")
    public List<EquipmentObjects> getEventsByPersonId(@PathVariable("personId") Long personId){
        List<EquipmentObjects> sortedEquipmentObjects = equipmentObjectService.getEquipmentObjectsByPersonId(personId);
        return getEquipmentObjects(sortedEquipmentObjects);
    }
    private List<EquipmentObjects> getEquipmentObjects(List<EquipmentObjects> sortedEquipmentObjects) {
        for (int i = 0; i < sortedEquipmentObjects.size(); i++){
            for (int j = 0; j < sortedEquipmentObjects.size()-1; j++){
                if (sortedEquipmentObjects.get(j).getEquipmentoId() < sortedEquipmentObjects.get(j+1).getEquipmentoId()){
                    EquipmentObjects temp = sortedEquipmentObjects.get(j);
                    sortedEquipmentObjects.set(j, sortedEquipmentObjects.get(j+1));
                    sortedEquipmentObjects.set(j+1, temp);
                }
            }
        }
        return sortedEquipmentObjects;
    }

    @PostMapping
    public ResponseEntity<String> registerNewEquipmentObject(@RequestBody EquipmentObjects equipmentObject){
        equipmentObjectService.addNewEquipmentObject(equipmentObject);
        return ResponseEntity.ok("{\"line\":\"Object saved\"}");
    }
    @DeleteMapping(path = "{equipment_objectId}")
    public ResponseEntity<String> deleteEquipmentObject(@PathVariable("equipment_objectId") Long equipmentObjectId){
        equipmentObjectService.deleteEquipmentObject(equipmentObjectId);
        return ResponseEntity.ok("{\"line\":\"Object deleted\"}");
    }
    @PutMapping(path = "{equipment_objectId}")
    public ResponseEntity<String> updateEquipmentObject(
            @PathVariable("equipment_objectId") Long equipmentoId,
            @RequestParam(required = false) Long equipmentmId,
            @RequestParam(required = false) Integer inventoryNum,
            @RequestParam(required = false) Long personId,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) String note
    )
    {
        equipmentObjectService.updateEquipmentObject(equipmentoId, equipmentmId, inventoryNum, personId,
                departmentId, note);
        return ResponseEntity.ok("{\"line\":\"Object updated\"}");
    }



}

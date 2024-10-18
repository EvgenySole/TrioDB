package com.example.triodb.waste_types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "waste_types")
public class WasteTypeController {
    public final WasteTypeService wasteTypeService;
    @Autowired
    public WasteTypeController(WasteTypeService wasteTypeService) {
        this.wasteTypeService = wasteTypeService;
    }

    @GetMapping
    public List<WasteTypes> getWasteTypes(){
        List<WasteTypes> sortedWasteTypes = wasteTypeService.getWasteTypes();
        for (int i = 0; i < sortedWasteTypes.size(); i++){
            for (int j = 0; j < sortedWasteTypes.size()-1; j++){
                if (sortedWasteTypes.get(j).getWastetId() > sortedWasteTypes.get(j+1).getWastetId()){
                    WasteTypes temp = sortedWasteTypes.get(j);
                    sortedWasteTypes.set(j, sortedWasteTypes.get(j+1));
                    sortedWasteTypes.set(j+1, temp);
                }
            }
        }
        return sortedWasteTypes;
    }

    @GetMapping(path ="{waste_typeId}")
    public List<WasteTypes> getWasteType(@PathVariable("waste_typeId") Long wasteTypeId){
        return wasteTypeService.getWasteType(wasteTypeId);
    }
    @PostMapping
    public void registerNewWasteType(@RequestBody WasteTypes wasteType){
        wasteTypeService.addNewWasteType(wasteType);
    }
    @DeleteMapping(path = "{waste_typeId}")
    public void deleteWasteType(@PathVariable("waste_typeId") Long wasteTypeId){
        wasteTypeService.deleteWasteType(wasteTypeId);
    }
}

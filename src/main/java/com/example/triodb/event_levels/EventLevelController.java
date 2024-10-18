package com.example.triodb.event_levels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "event_levels")
public class EventLevelController {
    public final EventLevelService equipmentTypeService;
    @Autowired
    public EventLevelController(EventLevelService equipmentTypeService) {
        this.equipmentTypeService = equipmentTypeService;
    }

    @GetMapping
    public List<EventLevels> getEventLevels(){
        return equipmentTypeService.getEventLevels();
    }

    @GetMapping(path ="{event_levelsId}")
    public List<EventLevels> getEquipmentType(@PathVariable("event_levelsId") Long equipmentTypeID){
        return equipmentTypeService.getEventLevel(equipmentTypeID);
    }
    @PostMapping
    public void registerNewEquipmentType(@RequestBody EventLevels equipmentTypes){
        equipmentTypeService.addNewEventLevel(equipmentTypes);
    }
    @DeleteMapping(path = "{event_levelsId}")
    public void deleteEquipmentType(@PathVariable("event_levelsId") Long equipmentTypeId){
        equipmentTypeService.deleteEventLevel(equipmentTypeId);
    }
    @PutMapping(path = "{event_levelsId}")
    public void updateEquipmentType(
            @PathVariable("event_levelsId") Long eventlId,
            @RequestParam(required = false) String levelName,
            @RequestParam(required = false) Integer colorCode
    )
    {
        equipmentTypeService.updateEventLevel(eventlId, levelName, colorCode);
    }



}

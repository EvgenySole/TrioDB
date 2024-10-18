package com.example.triodb.waste_groups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "waste_groups")
public class WasteGroupController {
    public final WasteGroupService wasteGroupService;
    public final WasteGroupRepository wasteGroupRepository;
    @Autowired
    public WasteGroupController(WasteGroupService wasteGroupService, WasteGroupRepository wasteGroupRepository) {
        this.wasteGroupService = wasteGroupService;
        this.wasteGroupRepository = wasteGroupRepository;
    }

    @GetMapping
    public List<WasteGroups> getWasteGroups(){
        List<WasteGroups> sortedWasteGroups = wasteGroupService.getWasteGroups();
        for (int i = 0; i < sortedWasteGroups.size(); i++){
            for (int j = 0; j < sortedWasteGroups.size()-1; j++){
                if (sortedWasteGroups.get(j).getWastegId() > sortedWasteGroups.get(j+1).getWastegId()){
                    WasteGroups temp = sortedWasteGroups.get(j);
                    sortedWasteGroups.set(j, sortedWasteGroups.get(j+1));
                    sortedWasteGroups.set(j+1, temp);
                }
            }
        }
        return sortedWasteGroups;
    }

    @GetMapping(path ="{waste_groupId}")
    public List<WasteGroups> getWasteGroup(@PathVariable("waste_groupId") Long wasteGroupId){
        return wasteGroupService.getWasteGroup(wasteGroupId);
    }
    @PostMapping
    public void registerNewWasteGroup(@RequestBody WasteGroups wasteGroups){
        wasteGroupService.addNewWasteGroup(wasteGroups);
    }
    @DeleteMapping(path = "{waste_groupId}")
    public void deleteWasteGroup(@PathVariable("waste_groupId") Long wasteGroupId){
        wasteGroupService.deleteWasteGroup(wasteGroupId);
    }
    @PutMapping(path = "{waste_groupId}")
    public void updateWasteGroup(
            @PathVariable("waste_groupId") Long wastetId,
            @RequestParam(required = false) String wasteName,
            @RequestParam(required = false) Long eventtId
    )
    {
//        wasteGroupService.updateWasteGroup(wastetId, wasteName, eventtId);
    }
}

package com.example.triodb.waste_groups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class WasteGroupService {

    public final WasteGroupRepository wasteGroupsRepository;

    @Autowired
    public WasteGroupService(WasteGroupRepository wasteTypeRepository) {
        this.wasteGroupsRepository = wasteTypeRepository;
    }
    public List<WasteGroups> getWasteGroups() {
        return wasteGroupsRepository.findAll();
    }

    public List<WasteGroups> getWasteGroup(Long wasteGroupsId) {
        boolean exists = wasteGroupsRepository.existsById(wasteGroupsId);
        if (!exists){
            throw new IllegalStateException("WasteType with id " + wasteGroupsId + " doesn't exsist");
        }
        return wasteGroupsRepository.findAllById(Collections.singleton(wasteGroupsId));
    }

    public void addNewWasteGroup(WasteGroups wasteGroupsId){
        wasteGroupsRepository.save(wasteGroupsId);
        System.out.println(wasteGroupsId);
    }

    public void deleteWasteGroup(Long wasteTypeId){
        boolean exists = wasteGroupsRepository.existsById(wasteTypeId);
        if (!exists){
            throw new IllegalStateException("WasteType with id " + wasteTypeId + " doesn't exsist");
        }
        wasteGroupsRepository.deleteById(wasteTypeId);
        System.out.println(wasteTypeId);
    }
}

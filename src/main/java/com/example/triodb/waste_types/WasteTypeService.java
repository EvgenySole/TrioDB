package com.example.triodb.waste_types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class WasteTypeService {

    public final WasteTypeRepository wasteTypeRepository;

    @Autowired
    public WasteTypeService(WasteTypeRepository wasteTypeRepository) {
        this.wasteTypeRepository = wasteTypeRepository;
    }
    public List<WasteTypes> getWasteTypes() {
        return wasteTypeRepository.findAll();
    }

    public List<WasteTypes> getWasteType(Long wasteTypeId) {
        boolean exists = wasteTypeRepository.existsById(wasteTypeId);
        if (!exists){
            throw new IllegalStateException("WasteType with id " + wasteTypeId + " doesn't exsist");
        }
        return wasteTypeRepository.findAllById(Collections.singleton(wasteTypeId));
    }

    public void addNewWasteType(WasteTypes wasteTypeId){
        wasteTypeRepository.save(wasteTypeId);
        System.out.println(wasteTypeId);
    }

    public void deleteWasteType(Long wasteTypeId){
        boolean exists = wasteTypeRepository.existsById(wasteTypeId);
        if (!exists){
            throw new IllegalStateException("WasteType with id " + wasteTypeId + " doesn't exsist");
        }
        wasteTypeRepository.deleteById(wasteTypeId);
        System.out.println(wasteTypeId);
    }
}

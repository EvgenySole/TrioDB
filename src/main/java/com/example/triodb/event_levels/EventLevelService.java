package com.example.triodb.event_levels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class EventLevelService {

    public final EventLevelRepository eventLevelRepository;

    @Autowired
    public EventLevelService(EventLevelRepository equipmentModelRepository) {
        this.eventLevelRepository = equipmentModelRepository;
    }
    public List<EventLevels> getEventLevels() {
        return eventLevelRepository.findAll();
    }

    public List<EventLevels> getEventLevel(Long eventlId) {
        boolean exists = eventLevelRepository.existsById(eventlId);
        if (!exists){
            throw new IllegalStateException("EventLevel with id " + eventlId + " doesn't exsist");
        }
        return eventLevelRepository.findAllById(Collections.singleton(eventlId));
    }

    public void addNewEventLevel(EventLevels equipmentType){
        eventLevelRepository.save(equipmentType);
        System.out.println(equipmentType);
    }

    public void deleteEventLevel(Long eventlId){
        boolean exists = eventLevelRepository.existsById(eventlId);
        if (!exists){
            throw new IllegalStateException("EventLevel with id " + eventlId + " doesn't exsist");
        }
        eventLevelRepository.deleteById(eventlId);
        System.out.println(eventlId);
    }

    @Transactional
    public void updateEventLevel(Long eventlId, String levelName, Integer colorCode) {
        EventLevels equipmentObject = eventLevelRepository.findById(eventlId).orElseThrow(
                () -> new IllegalStateException("EventLevel with id " + eventlId +
                        "doesn't exists")
        );

        if (eventlId != null  && !Objects.equals(equipmentObject.getEventlId(), eventlId)) {
            equipmentObject.setEventlId(eventlId);
        }

        if (levelName != null && !Objects.equals(equipmentObject.getLevelName(), levelName)) {
            equipmentObject.setLevelName(levelName);
        }

        if (colorCode != null && !Objects.equals(equipmentObject.getColorCode(), colorCode)) {
            equipmentObject.setColorCode(colorCode);
        }
    }
}

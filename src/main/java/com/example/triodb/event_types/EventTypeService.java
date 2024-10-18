package com.example.triodb.event_types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class EventTypeService {

    public final EventTypesRepository eventTypesRepository;

    @Autowired
    public EventTypeService(EventTypesRepository equipmentModelRepository) {
        this.eventTypesRepository = equipmentModelRepository;
    }
    public List<EventTypes> getEventTypes() {
        return eventTypesRepository.findAll();
    }

    public List<EventTypes> getEventType(Long eventlId) {
        boolean exists = eventTypesRepository.existsById(eventlId);
        if (!exists){
            throw new IllegalStateException("EventType with id " + eventlId + " doesn't exsist");
        }
        return eventTypesRepository.findAllById(Collections.singleton(eventlId));
    }

    public void addNewEventType(EventTypes eventType){
        eventTypesRepository.save(eventType);
        System.out.println(eventType);
    }

    public void deleteEventType(Long eventtId){
        boolean exists = eventTypesRepository.existsById(eventtId);
        if (!exists){
            throw new IllegalStateException("EventType with id " + eventtId + " doesn't exsist");
        }
        eventTypesRepository.deleteById(eventtId);
        System.out.println(eventtId);
    }

    @Transactional
    public void updateEventType(Long eventtId, Long eventtPartId, String eventTypeName) {
        EventTypes equipmentObject = eventTypesRepository.findById(eventtId).orElseThrow(
                () -> new IllegalStateException("EventType with id " + eventtId +
                        "doesn't exists")
        );

        if (eventtId != null  && !Objects.equals(equipmentObject.getEventtId(), eventtId)) {
            equipmentObject.setEventtId(eventtId);
        }

        if (eventTypeName != null && !Objects.equals(equipmentObject.getEventTypeName(), eventTypeName)) {
            equipmentObject.setEventTypeName(eventTypeName);
        }
    }
}

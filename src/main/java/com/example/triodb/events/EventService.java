package com.example.triodb.events;

import com.example.triodb.departments.Departments;
import com.example.triodb.equipment_objects.EquipmentObjects;
import com.example.triodb.event_levels.EventLevels;
import com.example.triodb.event_types.EventTypes;
import com.example.triodb.persons.Persons;
import com.example.triodb.tasks.Tasks;
import com.example.triodb.waste_groups.WasteGroups;
import com.example.triodb.waste_types.WasteTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class EventService {

    public final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public List<Events> getEvents() {
        return eventRepository.findAll();
    }

    public List<Events> getEvent(Long eventId) {
        boolean exists = eventRepository.existsById(eventId);
        if (!exists){
            throw new IllegalStateException("Event with id " + eventId + " doesn't exist");
        }
        return eventRepository.findAllById(Collections.singleton(eventId));
    }

    public List<Events> getEventsByPersonId(Long personId) {
        List<Events> allEvents = eventRepository.findAll();
        List<Events> targetEvents = new ArrayList<>();
        for (int i = 0; i < allEvents.size(); i++){
           if (allEvents.get(i).getPersonId().getPersonId() == personId){
               targetEvents.add(allEvents.get(i));
           }
        }
        return targetEvents;
    }

    public void addNewEvent(Events event){
        eventRepository.save(event);
    }

    public void deleteEvent(Long eventId){
        boolean exists = eventRepository.existsById(eventId);
        if (!exists){
            throw new IllegalStateException("Event with id " + eventId + " doesn't exist");
        }
        eventRepository.deleteById(eventId);
        System.out.println(eventId);
    }

    @Transactional
    public void updateEvent(Long eventId, Timestamp dateTime, EquipmentObjects equipmentoId, Persons personId,
                            Tasks taskId, String note, EventLevels eventlId, EventTypes eventtId,
                            WasteTypes wastetId, WasteGroups wastegId, String imageName, Departments departmentId) {
        Events event = eventRepository.findById(eventId).orElseThrow(
                () -> new IllegalStateException("Event with id " + eventId +
                        " doesn't exists")
        );

        if (dateTime != null  && !Objects.equals(event.getDateTime(), dateTime)) {
            event.setDateTime(dateTime);
        }

        if (equipmentoId != null && !Objects.equals(event.getEquipmentoId(), equipmentoId)) {
            event.setEquipmentoId(equipmentoId);
        }

        if (personId != null && !Objects.equals(event.getPersonId(), personId)) {
            event.setPersonId(personId);
        }

        if (taskId != null && !Objects.equals(event.getTaskId(), taskId)) {
            event.setTaskId(taskId);
        }

        if (note != null && !Objects.equals(event.getNote(), note)) {
            event.setNote(note);
        }

        if (eventlId != null && !Objects.equals(event.getEventlId(), eventlId)) {
            event.setEventlId(eventlId);
        }

        if (eventtId != null && !Objects.equals(event.getEventtId(), eventtId)) {
            event.setEventtId(eventtId);
        }

        if (wastetId != null && !Objects.equals(event.getWastetId(), wastetId)) {
            event.setWastetId(wastetId);
        }

        if (wastegId != null && !Objects.equals(event.getWastegId(), wastegId)) {
            event.setWastegId(wastegId);
        }

        if (imageName != null && !Objects.equals(event.getImageName(), imageName)) {
            event.setImageName(imageName);
        }

        if (departmentId != null && !Objects.equals(event.getDepartmentId(), departmentId)) {
            event.setDepartmentId(departmentId);
        }
    }


}

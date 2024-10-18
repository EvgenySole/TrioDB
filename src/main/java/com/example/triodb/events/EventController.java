package com.example.triodb.events;

import com.example.triodb.departments.Departments;
import com.example.triodb.equipment_objects.EquipmentObjectService;
import com.example.triodb.equipment_objects.EquipmentObjects;
import com.example.triodb.equipment_types.EquipmentTypeService;
import com.example.triodb.event_levels.EventLevelService;
import com.example.triodb.event_levels.EventLevels;
import com.example.triodb.event_types.EventTypeService;
import com.example.triodb.event_types.EventTypes;
import com.example.triodb.persons.PersonService;
import com.example.triodb.persons.Persons;
import com.example.triodb.tasks.TaskService;
import com.example.triodb.tasks.Tasks;
import com.example.triodb.waste_groups.WasteGroupService;
import com.example.triodb.waste_groups.WasteGroups;
import com.example.triodb.waste_types.WasteTypeService;
import com.example.triodb.waste_types.WasteTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(path = "events")
public class EventController {
    public final EventService eventService;
    public final EquipmentObjectService equipmentObjectService;
    public final PersonService personService;
    public final EquipmentTypeService equipmentTypeService;
    public final EventTypeService eventTypeService;
    public final TaskService taskService;
    public final EventLevelService eventLevelService;
    public final WasteTypeService wasteTypeService;
    public final WasteGroupService wasteGroupService;
    @Autowired
    public EventController(EventService eventService, EquipmentObjectService equipmentObjectService,
                           PersonService personService, EquipmentTypeService equipmentTypeService,
                           EventTypeService eventTypeService, TaskService taskService,
                           EventLevelService eventLevelService, WasteTypeService wasteTypeService,
                           WasteGroupService wasteGroupService) {
        this.eventService = eventService;
        this.equipmentObjectService = equipmentObjectService;
        this.personService = personService;
        this.equipmentTypeService = equipmentTypeService;
        this.eventTypeService = eventTypeService;
        this.taskService = taskService;
        this.eventLevelService = eventLevelService;
        this.wasteTypeService = wasteTypeService;
        this.wasteGroupService = wasteGroupService;
    }

    @GetMapping
    public List<Events> getEvents(){
        List<Events> sortedEvents = eventService.getEvents();
        return getEvents(sortedEvents);
    }

    @GetMapping(path ="{eventId}")
    public List<Events> getEvent(@PathVariable("eventId") Long eventId){
        List<Events> sortedEvents = eventService.getEvent(eventId);
        return getEvents(sortedEvents);
    }
    @GetMapping(path = "/person/{personId}")
    public List<Events> getEventsByPersonId(@PathVariable("personId") Long personId){
        List<Events> sortedEvents = eventService.getEventsByPersonId(personId);
        return getEvents(sortedEvents);
    }
    private List<Events> getEvents(List<Events> sortedEvents) {
        for (int i = 0; i < sortedEvents.size(); i++){
            for (int j = 0; j < sortedEvents.size()-1; j++){
                Timestamp date0 = sortedEvents.get(j).getDateTime();
                Timestamp date1 = sortedEvents.get(j+1).getDateTime();
                if (date0.before(date1)){
                    Events temp = sortedEvents.get(j);
                    sortedEvents.set(j, sortedEvents.get(j+1));
                    sortedEvents.set(j+1, temp);
                }
            }
        }
        return sortedEvents;
    }

    @PostMapping
    public ResponseEntity<String> registerNewEvent(@RequestBody Events event){
//        if (event.getEventId() == null){
//            event.setEventId(null);
//        }
//        if (event.getEquipmentoId().getEquipmentoId() == -1){
//            event.setEquipmentoId(null);
//        }
        eventService.addNewEvent(event);
//        EquipmentObjects equipmentObjects = equipmentObjectService.getEquipmentObject(event.getEquipmentoId().getEquipmentoId()).get(0);
//        Persons persons = personService.getPerson(event.getPersonId().getPersonId()).get(0);
//        Tasks tasks = taskService.getTask(event.getTaskId().getTaskId()).get(0);
//        EventLevels eventLevels = eventLevelService.getEventLevel(event.getEventlId().getEventlId()).get(0);
//        EventTypes eventTypes = eventTypeService.getEventType(event.getEventtId().getEventtId()).get(0);
//        WasteTypes wasteTypes = wasteTypeService.getWasteType(event.getWastetId().getWastetId()).get(0);
//        WasteGroups wasteGroups= wasteGroupService.getWasteGroup(event.getWastegId().getWastegId()).get(0);
//        Timestamp dateTime = Timestamp.valueOf(event.getDateTime().toLocalDateTime());
//        Events event2 = new Events();
//        event2.setEquipmentoId(equipmentObjects);
//        event2.setPersonId(persons);
//        event2.setTaskId(tasks);
//        event2.setEventlId(eventLevels);
//        event2.setWastetId(wasteTypes);
//        event2.setWastegId(wasteGroups);
//        event2.setEventtId(eventTypes);
//        event2.setDateTime(dateTime);
//        event2.setNote(event.getNote());
//        event2.setImageName(event.getImageName());
        //eventService.addNewEvent(event2);
        return ResponseEntity.ok("{\"line\":\"Event saved\"}");
    }
    @DeleteMapping(path = "{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable("eventId") Long eventId){
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok("{\"line\":\"Event deleted\"}");
    }
    @PutMapping(path = "{eventId}")
    public ResponseEntity<String> updateEvent(
            @PathVariable("eventId") Long eventId,
            @RequestParam(required = false) Timestamp dateTime,
            @RequestParam(required = false) EquipmentObjects equipmentoId,
            @RequestParam(required = false) Persons personId,
            @RequestParam(required = false) Tasks taskId,
            @RequestParam(required = false) String note,
            @RequestParam(required = false) EventLevels eventlId,
            @RequestParam(required = false) EventTypes eventtId,
            @RequestParam(required = false) WasteTypes wastetId,
            @RequestParam(required = false) WasteGroups wastegId,
            @RequestParam(required = false) String imageName,
            @RequestParam(required = false) Departments departmentId
            )
    {
        eventService.updateEvent(eventId, Timestamp.valueOf(dateTime.toLocalDateTime().plusSeconds(60*60*3)),
                equipmentoId, personId, taskId, note, eventlId, eventtId, wastetId, wastegId, imageName, departmentId);
        return ResponseEntity.ok("{\"line\":\"Event updated\"}");
    }

}

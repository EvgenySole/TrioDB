package com.example.triodb.event_types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "event_types")
public class EventTypeController {
    public final EventTypeService eventTypeService;
    @Autowired
    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @GetMapping
    public List<EventTypes> getEventTypes(){
        List<EventTypes> sortedEventTypes = eventTypeService.getEventTypes();

        for (int i = 0; i < sortedEventTypes.size(); i++){
            for (int j = 0; j < sortedEventTypes.size()-1; j++){
                if (sortedEventTypes.get(j).getEventtId() > sortedEventTypes.get(j+1).getEventtId()){
                    EventTypes temp = sortedEventTypes.get(j);
                    sortedEventTypes.set(j, sortedEventTypes.get(j+1));
                    sortedEventTypes.set(j+1, temp);
                }
            }
        }
        return sortedEventTypes;
    }

    @GetMapping(path ="{event_typesId}")
    public List<EventTypes> getEventType(@PathVariable("event_typesId") Long eventTypeID){
        return eventTypeService.getEventType(eventTypeID);
    }
    @PostMapping
    public void registerNewEventType(@RequestBody EventTypes eventTypes){
        eventTypeService.addNewEventType(eventTypes);
    }
    @DeleteMapping(path = "{event_typesId}")
    public void deleteEventType(@PathVariable("event_typesId") Long eventTypeID){
        eventTypeService.deleteEventType(eventTypeID);
    }
    @PutMapping(path = "{event_typesId}")
    public void updateEventType(
            @PathVariable("event_typesId") Long eventtId,
            @RequestParam(required = false) Long eventtPartId,
            @RequestParam(required = false) String eventTypeName
    )
    {
        eventTypeService.updateEventType(eventtId, eventtPartId, eventTypeName);
    }
}

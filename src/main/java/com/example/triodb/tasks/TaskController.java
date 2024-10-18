package com.example.triodb.tasks;

import com.example.triodb.equipment_objects.EquipmentObjectService;
import com.example.triodb.equipment_objects.EquipmentObjects;
import com.example.triodb.persons.PersonService;
import com.example.triodb.persons.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(path = "tasks")
public class TaskController {
    public final TaskService taskService;
    public final PersonService personService;
    public final EquipmentObjectService equipmentObjectService;
    @Autowired
    public TaskController(TaskService taskService, PersonService personService, EquipmentObjectService equipmentObjectService) {
        this.taskService = taskService;
        this.personService = personService;
        this.equipmentObjectService = equipmentObjectService;
    }

    @GetMapping
    public List<Tasks> getTasks(){
        List<Tasks> sortedTasks = taskService.getTasks();
        return getTasks(sortedTasks);
    }

    @GetMapping(path ="{taskId}")
    public List<Tasks> getTask(@PathVariable("taskId") Long taskId){
        List<Tasks> sortedTasks = taskService.getTask(taskId);
        return getTasks(sortedTasks);
    }
    @GetMapping(path = "/person/{personId}")
    public List<Tasks> getTasksByPersonId(@PathVariable("personId") Long personId){
        List<Tasks> sortedTasks = taskService.getTasksByPersonId(personId);
        return getTasks(sortedTasks);
    }
    private List<Tasks> getTasks(List<Tasks> sortedTasks) {
        for (int i = 0; i < sortedTasks.size(); i++){
            for (int j = 0; j < sortedTasks.size()-1; j++){
                Timestamp date0 = sortedTasks.get(j).getDateTime();
                Timestamp date1 = sortedTasks.get(j+1).getDateTime();
                if (date0.before(date1)){
                    Tasks temp = sortedTasks.get(j);
                    sortedTasks.set(j, sortedTasks.get(j+1));
                    sortedTasks.set(j+1, temp);
                }
            }
        }
        return sortedTasks;
    }
    @PostMapping
    public ResponseEntity<String> registerNewTask(@RequestBody Tasks task){
        if (task.getEventId() == -1){
            task.setEventId(null);
        }
        if (task.getEquipmentoId().getEquipmentoId() == -1){
            task.setEquipmentoId(null);
        }
        taskService.addNewTask(task);
        return ResponseEntity.ok("{\"line\":\"Task saved\"}");
    }
    @DeleteMapping(path = "{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable("taskId") Long eventId){
        taskService.deleteTask(eventId);
        return ResponseEntity.ok("{\"line\":\"Task Deleted\"}");
    }
    @PutMapping(path = "{taskId}")
    public ResponseEntity<String> updateTask(
            @PathVariable("taskId") Long taskId,
            @RequestParam(required = false) Long eventId,
            @RequestParam(required = false) Timestamp dateTime,
            @RequestParam(required = false) String order,
            @RequestParam(required = false) Persons headPersonId,
            @RequestParam(required = false) Persons targetWorkerId,
            @RequestParam(required = false) EquipmentObjects equipmentoId,
            @RequestParam(required = false) String done
            )
    {
        taskService.updateTask(taskId, eventId, Timestamp.valueOf(dateTime.toLocalDateTime().plusSeconds(60*60*3)),
                order, headPersonId, targetWorkerId, equipmentoId, done);
        return ResponseEntity.ok("{\"line\":\"Task updated\"}");
    }
}

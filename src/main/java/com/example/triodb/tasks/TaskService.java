package com.example.triodb.tasks;

import com.example.triodb.equipment_objects.EquipmentObjects;
import com.example.triodb.persons.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {

    public final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Tasks> getTasks() {
        return taskRepository.findAll();
    }

    public List<Tasks> getTask(Long taskId) {
        boolean exists = taskRepository.existsById(taskId);
        if (!exists){
            throw new IllegalStateException("Task with id " + taskId + " doesn't exist");
        }
        return taskRepository.findAllById(Collections.singleton(taskId));
    }
    public List<Tasks> getTasksByPersonId(Long personId) {
        List<Tasks> allTasks = taskRepository.findAll();
        List<Tasks> targetTasks = new ArrayList<>();
        for (int i = 0; i < allTasks.size(); i++){
            if (allTasks.get(i).getHeadPersonId().getPersonId() == personId){
                targetTasks.add(allTasks.get(i));
            }
        }
        return targetTasks;
    }
    public void addNewTask(Tasks task){
        System.out.println("Task" + task);
        System.out.println("Task" + task.getOrderText());
        taskRepository.save(task);
    }

    public void deleteTask(Long taskId){
        boolean exists = taskRepository.existsById(taskId);
        if (!exists){
            throw new IllegalStateException("Task with id " + taskId + " doesn't exist");
        }
        taskRepository.deleteById(taskId);
        System.out.println(taskId);
    }

    @Transactional
    public void updateTask(Long taskId, Long eventId, Timestamp dateTime, String order,
                           Persons headPersonId, Persons targetWorkerId, EquipmentObjects equipmentoId,
                           String done) {
        Tasks task = taskRepository.findById(taskId).orElseThrow(
                () -> new IllegalStateException("Task with id " + taskId +
                        "doesn't exists")
        );

        if (eventId != null && !Objects.equals(task.getTaskId(), eventId)) {
            task.setEventId(eventId);
        }

        if (dateTime != null && !Objects.equals(task.getDateTime(), dateTime)) {
            task.setDateTime(dateTime);
        }

        if (order != null && !Objects.equals(task.getOrderText(), order)) {
            task.setOrderText(order);
        }

        if (headPersonId != null && !Objects.equals(task.getHeadPersonId(), headPersonId)) {
            task.setHeadPersonId(headPersonId);
        }

        if (targetWorkerId != null && !Objects.equals(task.getTargetWorkerId(), targetWorkerId)) {
            task.setTargetWorkerId(targetWorkerId);
        }

        if (equipmentoId != null && !Objects.equals(task.getEquipmentoId(), equipmentoId)) {
            task.setEquipmentoId(equipmentoId);
        }

        if (done != null && !Objects.equals(task.getDone(), done)) {
            task.setDone(done);
        }
    }
}

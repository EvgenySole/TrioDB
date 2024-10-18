package com.example.triodb.workers_history;

import com.example.triodb.persons_history.PersonsHistory;
import com.example.triodb.persons_history.PersonsHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "workers_history")
public class WorkersHistoryController {
    public final WorkersHistoryService workersHistoryService;
    @Autowired
    public WorkersHistoryController(WorkersHistoryService workersHistoryService) {
        this.workersHistoryService = workersHistoryService;
    }

    @GetMapping
    public List<WorkersHistory> getWorkersHistory(){
        return workersHistoryService.getWorkersHistory();
    }

    @GetMapping(path ="{workers_historyId}")
    public List<WorkersHistory> getWorkersHistory(@PathVariable("workers_historyId") Long personHistoryId){
        return workersHistoryService.getWorkersHistory(personHistoryId);
    }
    @PostMapping
    public void registerNewWorkersHistory(@RequestBody WorkersHistory workersHistory){
        workersHistoryService.addNewWorkersHistory(workersHistory);
    }
    @DeleteMapping(path = "{workers_historyId}")
    public void deleteWorkersHistory(@PathVariable("workers_historyId") Long workerId){
        workersHistoryService.deleteWorkersHistory(workerId);
    }
    @PutMapping(path = "{workers_historyId}")
    public void updateWorkersHistory(
            @PathVariable("workers_historyId") Long workerhId,
            @RequestParam(required = false) Long personId,
            @RequestParam(required = false) Integer enterNum,
            @RequestParam(required = false) Long postId,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Date begDate,
            @RequestParam(required = false) Date endDate
    )
    {
//        workersHistoryService.updateWorkersHistory(workerhId,
//                personId, enterNum, postId, departmentId, begDate, endDate);
    }



}

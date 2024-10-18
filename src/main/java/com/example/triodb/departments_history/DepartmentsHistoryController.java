package com.example.triodb.departments_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "departments_history")
public class DepartmentsHistoryController {
    public final DepartmentsHistoryService departmentsHistoryService;
    @Autowired
    public DepartmentsHistoryController(DepartmentsHistoryService departmentsHistoryService) {
        this.departmentsHistoryService = departmentsHistoryService;
    }

    @GetMapping
    public List<DepartmentsHistory> getDepartmentsHistory(){
        return departmentsHistoryService.getDepartmentsHistory();
    }

    @GetMapping(path ="{departmentsHistoryId}")
    public List<DepartmentsHistory> getDepartmentsHistoryId(@PathVariable("departmentsHistoryId") Long departmentId){
        return departmentsHistoryService.getDepartmentsHistory(departmentId);
    }
    @PostMapping
    public void registerNewDepartmentsHistory(@RequestBody DepartmentsHistory departmentsHistory){
        departmentsHistoryService.addNewDepartmentsHistory(departmentsHistory);
    }
    @DeleteMapping(path = "{departmentsHistoryId}")
    public void deleteDepartmentsHistory(@PathVariable("departmentsHistoryId") Long departmentsHistoryId){
        departmentsHistoryService.deleteDepartmentsHistory(departmentsHistoryId);
    }
    @PutMapping(path = "{departmentsHistoryId}")
    public void updateDepartmentsHistory(
            @PathVariable("departmentsHistoryId") Long departmentHistoryId,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) String departmentName,
            @RequestParam(required = false) Long headWorkerId,
            @RequestParam(required = false) Long parDepId,
            @RequestParam(required = false) Date begDate,
            @RequestParam(required = false) Date endDate
    )
    {
        departmentsHistoryService.updateDepartmentsHistory(departmentHistoryId, departmentId, departmentName,
                headWorkerId, parDepId, begDate, endDate);
    }



}

package com.example.triodb.departments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "departments")
public class DepartmentController {
    public final DepartmentService departmentService;
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Departments> getDepartments(){
        List<Departments> sortedDepartments = departmentService.getDepartments();
        for (int i = 0; i < sortedDepartments.size(); i++){
            for (int j = 0; j < sortedDepartments.size()-1; j++){
                if (sortedDepartments.get(j).getDepartmentId() > sortedDepartments.get(j+1).getDepartmentId()){
                    Departments temp = sortedDepartments.get(j);
                    sortedDepartments.set(j, sortedDepartments.get(j+1));
                    sortedDepartments.set(j+1, temp);
                }
            }
        }
        return sortedDepartments;
    }

    @GetMapping(path ="{departmentId}")
    public List<Departments> getDepartmentId(@PathVariable("departmentId") Long departmentId){
        return departmentService.getDepartment(departmentId);
    }
    @PostMapping
    public void registerNewEquipmentModel(@RequestBody Departments department){
        departmentService.addNewDepartment(department);
    }
    @DeleteMapping(path = "{departmentId}")
    public void deleteEquipmentModel(@PathVariable("departmentId") Long departmentId){
        departmentService.deleteDepartment(departmentId);
    }
    @PutMapping(path = "{departmentId}")
    public void updateEquipmentModel(
            @PathVariable("departmentId") Long departmentId
    )
    {
        departmentService.updateDepartment(departmentId);
    }
}

package com.example.triodb.departments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
@Service
public class DepartmentService {

    public final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    public List<Departments> getDepartments() {
        return departmentRepository.findAll();
    }

    public List<Departments> getDepartment(Long departmentId) {
        boolean exists = departmentRepository.existsById(departmentId);
        if (!exists){
            throw new IllegalStateException("EquipmentModel with id " + departmentId + " doesn't exsist");
        }
        return departmentRepository.findAllById(Collections.singleton(departmentId));
    }

    public void addNewDepartment(Departments department){
        departmentRepository.save(department);
        System.out.println(department);
    }

    public void deleteDepartment(Long departmentId){
        boolean exists = departmentRepository.existsById(departmentId);
        if (!exists){
            throw new IllegalStateException("EquipmentModel with id " + departmentId + " doesn't exsist");
        }
        departmentRepository.deleteById(departmentId);
        System.out.println(departmentId);
    }

    @Transactional
    public void updateDepartment(Long departmentId) {
        Departments department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new IllegalStateException("Department with id " + departmentId +
                        "doesn't exists")
        );
        if (departmentId != null  && !Objects.equals(department.getDepartmentId(), departmentId)) {
            department.setDepartmentId(departmentId);
        }
    }

}

package com.example.triodb.departments_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class DepartmentsHistoryService {

    public final DepartmentsHistoryRepository departmentsHistoryRepository;

    @Autowired
    public DepartmentsHistoryService(DepartmentsHistoryRepository departmentsHistoryRepository) {
        this.departmentsHistoryRepository = departmentsHistoryRepository;
    }
    public List<DepartmentsHistory> getDepartmentsHistory() {
        return departmentsHistoryRepository.findAll();
    }

    public List<DepartmentsHistory> getDepartmentsHistory(Long departmentId) {
        boolean exists = departmentsHistoryRepository.existsById(departmentId);
        if (!exists){
            throw new IllegalStateException("EquipmentModel with id " + departmentId + " doesn't exsist");
        }
        return departmentsHistoryRepository.findAllById(Collections.singleton(departmentId));
    }

    public void addNewDepartmentsHistory(DepartmentsHistory department){
        departmentsHistoryRepository.save(department);
        System.out.println(department);
    }

    public void deleteDepartmentsHistory(Long departmentId){
        boolean exists = departmentsHistoryRepository.existsById(departmentId);
        if (!exists){
            throw new IllegalStateException("EquipmentModel with id " + departmentId + " doesn't exsist");
        }
        departmentsHistoryRepository.deleteById(departmentId);
        System.out.println(departmentId);
    }

    @Transactional
    public void updateDepartmentsHistory(Long departmentHistoryId, Long departmentId, String departmentName,
                                         Long headWorkerId, Long parDepId, Date begDate, Date endDate) {
        DepartmentsHistory departmentHistory = departmentsHistoryRepository.findById(departmentHistoryId).orElseThrow(
                () -> new IllegalStateException("DepartmentHistory with id " + departmentHistoryId +
                        "doesn't exists")
        );

        if (departmentHistoryId != null  && !Objects.equals(departmentHistory.getDepartmentId(), departmentHistoryId)) {
            departmentHistory.setDepartmentId(departmentHistoryId);
        }
        if (departmentId != null  && !Objects.equals(departmentHistory.getDepartmentId(), departmentId)) {
            departmentHistory.setDepartmentId(departmentId);
        }
        if (departmentName != null  && !Objects.equals(departmentHistory.getDepartmentName(), departmentName)) {
            departmentHistory.setDepartmentName(departmentName);
        }
        if (headWorkerId != null  && !Objects.equals(departmentHistory.getHeadWorkerId(), headWorkerId)) {
            departmentHistory.setHeadWorkerId(headWorkerId);
        }
        if (parDepId != null  && !Objects.equals(departmentHistory.getParDepId(), parDepId)) {
            departmentHistory.setParDepId(parDepId);
        }
        if (begDate != null  && !Objects.equals(departmentHistory.getBegDate(), begDate)) {
            departmentHistory.setBegDate(begDate);
        }
        if (endDate != null  && !Objects.equals(departmentHistory.getEndDate(), endDate)) {
            departmentHistory.setEndDate(endDate);
        }
    }

}

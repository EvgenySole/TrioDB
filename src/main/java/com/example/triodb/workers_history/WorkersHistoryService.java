package com.example.triodb.workers_history;

import com.example.triodb.persons_history.PersonsHistory;
import com.example.triodb.persons_history.PersonsHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class WorkersHistoryService {

    public final WorkersHistoryRepository workersHistoryRepository;

    @Autowired
    public WorkersHistoryService(WorkersHistoryRepository workersHistoryRepository) {
        this.workersHistoryRepository = workersHistoryRepository;
    }
    public List<WorkersHistory> getWorkersHistory() {
        return workersHistoryRepository.findAll();
    }

    public List<WorkersHistory> getWorkersHistory(Long workersHistoryId) {
        boolean exists = workersHistoryRepository.existsById(workersHistoryId);
        if (!exists){
            throw new IllegalStateException("WorkersHistory with id " + workersHistoryId + " doesn't exsist");
        }
        return workersHistoryRepository.findAllById(Collections.singleton(workersHistoryId));
    }

    public void addNewWorkersHistory(WorkersHistory workersHistory){
        workersHistoryRepository.save(workersHistory);
        System.out.println(workersHistory);
    }

    public void deleteWorkersHistory(Long workersHistoryId){
        boolean exists = workersHistoryRepository.existsById(workersHistoryId);
        if (!exists){
            throw new IllegalStateException("WorkersHistory with id " + workersHistoryId + " doesn't exsist");
        }
        workersHistoryRepository.deleteById(workersHistoryId);
        System.out.println(workersHistoryId);
    }

    @Transactional
    public void updateWorkersHistory(Long workerhId, Long personId, Integer enterNum,
                                     Long postId, Long departmentId, Date begDate, Date endDate) {
        WorkersHistory workersHistory = workersHistoryRepository.findById(workerhId).orElseThrow(
                () -> new IllegalStateException("WorkersHistory with id " + workerhId +
                        "doesn't exists")
        );

//        if (workerhId != null  && !Objects.equals(workersHistory.getWorkerhId(), workerhId)) {
//            workersHistory.setWorkerhId(workerhId);
//        }
//        if (personId != null  && !Objects.equals(workersHistory.getPersonId(), personId)) {
//            workersHistory.setPersonId(personId);
//        }
//        if (enterNum != null  && !Objects.equals(workersHistory.getEnterNum(), enterNum)) {
//            workersHistory.setEnterNum(enterNum);
//        }
//        if (postId != null  && !Objects.equals(workersHistory.getPostId(), postId)) {
//            workersHistory.setPostId(postId);
//        }
//        if (departmentId != null  && !Objects.equals(workersHistory.getDepartmentId(), departmentId)) {
//            workersHistory.setDepartmentId(departmentId);
//        }
//        if (begDate != null  && !Objects.equals(workersHistory.getBegDate(), begDate)) {
//            workersHistory.setBegDate(begDate);
//        }
//        if (endDate != null  && !Objects.equals(workersHistory.getEndDate(), endDate)) {
//            workersHistory.setEndDate(endDate);
//        }
    }

}

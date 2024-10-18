package com.example.triodb.departments_history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DepartmentsHistoryRepository extends JpaRepository<DepartmentsHistory, Long> {
    @Query("SELECT s FROM DepartmentsHistory s WHERE s.departmenthId = ?1")
    Optional<DepartmentsHistory> findDepartmentsHistoryById(String departmentId);
}

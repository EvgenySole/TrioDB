package com.example.triodb.departments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface DepartmentRepository extends JpaRepository<Departments, Long> {
    @Query("SELECT s FROM Departments s WHERE s.departmentId = ?1")
    Departments findDepartmentsById(String departmentId);
}

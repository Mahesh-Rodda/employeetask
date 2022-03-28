package com.cjss.employeeT2.repository;

import com.cjss.employeeT2.entity.EmployeeDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailsRepo extends JpaRepository<EmployeeDetailsEntity,String> {
}

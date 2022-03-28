package com.cjss.employeeT2.repository;

import com.cjss.employeeT2.entity.EmployeeSalaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeSalaryRepo extends JpaRepository<EmployeeSalaryEntity,String> {
    List<EmployeeSalaryEntity> findByPayableIgnoreCaseContaining(String payable);
}


package com.cjss.employeeT2.repository;

import com.cjss.employeeT2.entity.EmployeeAttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeAttendanceRepo extends JpaRepository<EmployeeAttendanceEntity,String> {

//    List<EmployeeAttendanceEntity> findByDateContains(String date);
List<EmployeeAttendanceEntity> findByHolidayTrue();

}

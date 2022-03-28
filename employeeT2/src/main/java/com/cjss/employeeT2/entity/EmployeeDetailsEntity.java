package com.cjss.employeeT2.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class EmployeeDetailsEntity {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String email;
    @OneToOne(mappedBy = "employeeDetails")
    EmployeeSalaryEntity employeeSalary;
    @OneToMany(mappedBy = "employeeDetails")
    List<EmployeeAddressEntity> employeeAddressSet = new ArrayList<>();
    @OneToMany(mappedBy = "employeeDetails")
    List<EmployeeAttendanceEntity> employeeAttendanceList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmployeeSalaryEntity getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(EmployeeSalaryEntity employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public List<EmployeeAddressEntity> getEmployeeAddressSet() {
        return employeeAddressSet;
    }

    public void setEmployeeAddressSet(List<EmployeeAddressEntity> employeeAddressSet) {
        this.employeeAddressSet = employeeAddressSet;
    }

    public List<EmployeeAttendanceEntity> getEmployeeAttendanceList() {
        return employeeAttendanceList;
    }

    public void setEmployeeAttendanceList(List<EmployeeAttendanceEntity> employeeAttendanceList) {
        this.employeeAttendanceList = employeeAttendanceList;
    }

}

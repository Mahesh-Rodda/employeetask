package com.cjss.employeeT2.model;


import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public class EmployeeDetails {
    private String id;
    @NotNull
    private String name;
    @Email
    private String email;
    private List<EmployeeAddress> employeeAddressList;
    //    @NotNull(message = "list should not empty")
    private EmployeeSalary employeeSalaryList;
    //    @NotEmpty(message = "list should not empty")
    private List<EmployeeAttendance> employeeAttendanceList;

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

    public List<EmployeeAddress> getEmployeeAddressList() {
        return employeeAddressList;
    }

    public void setEmployeeAddressList(List<EmployeeAddress> employeeAddressList) {
        this.employeeAddressList = employeeAddressList;
    }

    public EmployeeSalary getEmployeeSalaryList() {
        return employeeSalaryList;
    }

    public void setEmployeeSalaryList(EmployeeSalary employeeSalaryList) {
        this.employeeSalaryList = employeeSalaryList;
    }

    public List<EmployeeAttendance> getEmployeeAttendanceList() {
        return employeeAttendanceList;
    }

    public void setEmployeeAttendanceList(List<EmployeeAttendance> employeeAttendanceList) {
        this.employeeAttendanceList = employeeAttendanceList;
    }

    @Override
    public String toString() {
        return "EmployeeDetails{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", employeeAddressList=" + employeeAddressList +
                ", employeeSalaryList=" + employeeSalaryList +
                ", employeeAttendanceList=" + employeeAttendanceList +
                '}';
    }
}

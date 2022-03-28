package com.cjss.employeeT2.model;

import java.util.List;

public class EmployeeAttendanceSalary {
    private String id;
    private String name;
    private  List<String> phoneNumber;
    private  double salary;
    private Integer NoOfDaysOff;

    public EmployeeAttendanceSalary(String id, String name, List<String> phoneNumber, double salary, Integer noOfDaysOff) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.NoOfDaysOff = noOfDaysOff;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setNoOfDaysOff(Integer noOfDaysOff) {
        NoOfDaysOff = noOfDaysOff;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public Integer getNoOfDaysOff() {
        return NoOfDaysOff;
    }
}

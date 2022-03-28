package com.cjss.employeeT2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class EmployeeSalaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private double salary;
    private  String payable;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private EmployeeDetailsEntity employeeDetails;

    public EmployeeSalaryEntity() {
    }

    public EmployeeSalaryEntity(String id, double salary, String payable, EmployeeDetailsEntity employeeDetails) {
        this.id = id;
        this.salary = salary;
        this.payable = payable;
        this.employeeDetails = employeeDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPayable() {
        return payable;
    }

    public void setPayable(String payable) {
        this.payable = payable;
    }

    public EmployeeDetailsEntity getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetailsEntity employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

}

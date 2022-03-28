package com.cjss.employeeT2.model;

public class EmployeeSalaryDetails {
    private String id;
    private String name;
    private  String email;
    private double salary;
    private  String payable;

    public EmployeeSalaryDetails(String id, String name, String email, double salary, String payable) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.payable = payable;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getSalary() {
        return salary;
    }

    public String getPayable() {
        return payable;
    }
}

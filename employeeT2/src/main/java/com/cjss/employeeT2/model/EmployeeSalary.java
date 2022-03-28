package com.cjss.employeeT2.model;


public class EmployeeSalary {
    private String id;
    private double salary;
    private  String payable;

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

    @Override
    public String toString() {
        return "EmployeeSalary{" +
                "id='" + id + '\'' +
                ", salary=" + salary +
                ", payable='" + payable + '\'' +
                '}';
    }
}

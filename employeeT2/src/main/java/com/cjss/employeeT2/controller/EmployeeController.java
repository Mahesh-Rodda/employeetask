package com.cjss.employeeT2.controller;

import com.cjss.employeeT2.entity.EmployeeDetailsEntity;
import com.cjss.employeeT2.model.EmployeeAttendanceSalary;
import com.cjss.employeeT2.model.EmployeeDetails;
import com.cjss.employeeT2.model.EmployeeSalaryDetails;
import com.cjss.employeeT2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/add")
    public EmployeeDetailsEntity addEmployee(@RequestBody EmployeeDetails employeeModel){ return employeeService.addEmployee(employeeModel);}
    @GetMapping("/country/{country}")
    public Set<EmployeeDetails> getEmployeeByCountry(@PathVariable String country){ return  employeeService.getEmployeeByCountry(country);}
    @GetMapping("/city/{city1}/{city2}")
    public Set<EmployeeDetails> getEmployeeByCity(@PathVariable String city1, @PathVariable String city2){ return  employeeService.getEmployeeByCity(city1,city2);}
    @GetMapping("/city2/{city}/{country}")
    public Set<EmployeeDetails> getEmployeeByCityAndCountry(@PathVariable String city, @PathVariable String country){ return  employeeService.getEmployeeByCityAndCountry(city,country);}
    @GetMapping("/salary")
    public List<EmployeeSalaryDetails> getEmployeeSalaryDetails(){ return employeeService.getEmployeeSalaryDetails();}
    @GetMapping("/salary/{payable}")
    public List<EmployeeSalaryDetails> getEmployeeSalaryDetails(@PathVariable String payable){  return employeeService.getEmployeeSalaryDetails(payable);}
    @GetMapping("/attend/{date}")
    public List<EmployeeAttendanceSalary> getEmployeeAttend(@PathVariable String date){ return employeeService.getEmployeeAttend(date); }
}

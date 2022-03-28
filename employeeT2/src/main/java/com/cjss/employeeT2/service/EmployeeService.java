package com.cjss.employeeT2.service;

import com.cjss.employeeT2.entity.EmployeeAddressEntity;
import com.cjss.employeeT2.entity.EmployeeAttendanceEntity;
import com.cjss.employeeT2.entity.EmployeeDetailsEntity;
import com.cjss.employeeT2.entity.EmployeeSalaryEntity;
import com.cjss.employeeT2.model.*;
import com.cjss.employeeT2.repository.EmployeeAddressRepo;
import com.cjss.employeeT2.repository.EmployeeAttendanceRepo;
import com.cjss.employeeT2.repository.EmployeeDetailsRepo;
import com.cjss.employeeT2.repository.EmployeeSalaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeDetailsRepo employeeDetailsRepo;
    @Autowired
    EmployeeSalaryRepo employeeSalaryRepo;
    @Autowired
    EmployeeAddressRepo employeeAddressRepo;
    @Autowired
    EmployeeAttendanceRepo employeeAttendanceRepo;


    public EmployeeDetailsEntity addEmployee(EmployeeDetails employeeModel){
        EmployeeDetailsEntity employeeDetailsEntity = new EmployeeDetailsEntity();
        employeeDetailsEntity.setName(employeeModel.getName());
        employeeDetailsEntity.setEmail(employeeModel.getEmail());
        EmployeeSalaryEntity employeeSalaryEntity = new EmployeeSalaryEntity();
        employeeSalaryEntity.setSalary(employeeModel.getEmployeeSalaryList().getSalary());
        employeeSalaryEntity.setPayable(employeeModel.getEmployeeSalaryList().getPayable());
        employeeSalaryEntity.setEmployeeDetails(employeeDetailsEntity);
        employeeSalaryRepo.save(employeeSalaryEntity);
        employeeDetailsEntity.setEmployeeSalary(employeeSalaryEntity);

        List<EmployeeAddressEntity> employeeAddresses = new ArrayList<>();
        employeeModel.getEmployeeAddressList().forEach(employeeAddress -> {
            EmployeeAddressEntity employeeAddressEntity = new EmployeeAddressEntity();
            employeeAddressEntity.setLine1(employeeAddress.getLine1());
            employeeAddressEntity.setLine2(employeeAddress.getLine2());
            employeeAddressEntity.setCity(employeeAddress.getCity());
            employeeAddressEntity.setPhoneNumber(employeeAddress.getPhoneNumber());
            employeeAddressEntity.setCountry(employeeAddress.getCountry());
            employeeAddressEntity.setEmployeeDetails(employeeDetailsEntity);
            employeeAddresses.add(employeeAddressRepo.save(employeeAddressEntity));
        });
        List<EmployeeAttendanceEntity> employeeAttendanceEntities =new ArrayList<>();
        employeeModel.getEmployeeAttendanceList().forEach(employeeAttendance -> {
            EmployeeAttendanceEntity employeeAttendanceEntity = new EmployeeAttendanceEntity();
            employeeAttendanceEntity.setDate(employeeAttendance.getDate());
            employeeAttendanceEntity.setHoliday(employeeAttendance.isHoliday());
            employeeAttendanceEntity.setReasonForHoliday(employeeAttendance.getReasonForHoliday());
            employeeAttendanceEntity.setEmployeeDetails(employeeDetailsEntity);
            employeeAttendanceRepo.save(employeeAttendanceEntity);
            employeeAttendanceEntities.add(employeeAttendanceEntity);
        });
        employeeDetailsEntity.setEmployeeAddressSet(employeeAddresses);
      employeeDetailsEntity.setEmployeeAttendanceList(employeeAttendanceEntities);
      return employeeDetailsRepo.save(employeeDetailsEntity);
    }
    public Set<EmployeeDetails> getEmployeeByCountry(String country){
                List<EmployeeAddressEntity> a = employeeAddressRepo.findByCountryIgnoreCase(country);
      Set<EmployeeDetailsEntity> b = a.stream().map(EmployeeAddressEntity::getEmployeeDetails).collect(Collectors.toSet());
      return  b.stream().map(this::getEmployeeModel).collect(Collectors.toSet());
    }
    public Set<EmployeeDetails> getEmployeeByCity(String city1, String city2){
        List<EmployeeAddressEntity> a = employeeAddressRepo.findByCityOrCityIgnoreCase(city1,city2);
        Set<String> a1 = a.stream().map(EmployeeAddressEntity::getEmployeeDetails).map(EmployeeDetailsEntity::getId).collect(Collectors.toSet());
        return   a1.stream().map(e -> employeeDetailsRepo.findById(e).orElse(null)).map(this::getEmployeeModel).collect(Collectors.toSet());
    }
    public Set<EmployeeDetails> getEmployeeByCityAndCountry(String city, String country){
        List<EmployeeAddressEntity> a = employeeAddressRepo.findByCityAndCountryIgnoreCase(city,country);
        return   a.stream().map(EmployeeAddressEntity::getEmployeeDetails).map(this::getEmployeeModel).collect(Collectors.toSet());
    }

    public List<EmployeeSalaryDetails> getEmployeeSalaryDetails(){
       List<EmployeeDetailsEntity> empDls = employeeDetailsRepo.findAll();
      return empDls.stream().map(this::getEmployeeWithSalary).collect(Collectors.toList());
    }
    public List<EmployeeSalaryDetails> getEmployeeSalaryDetails(String payable){

        List<EmployeeSalaryEntity> sal =employeeSalaryRepo.findByPayableIgnoreCaseContaining(payable);
        List<EmployeeDetailsEntity> emp = sal.stream().map(EmployeeSalaryEntity::getEmployeeDetails).collect(Collectors.toList());
        return emp.stream().map(this::getEmployeeWithSalary).collect(Collectors.toList());
    }
    public List<EmployeeAttendanceSalary> getEmployeeAttend(String date){
       List<EmployeeAttendanceEntity> empAtd = employeeAttendanceRepo.findByDateEndsWith(date);
        Set<String> e1 = empAtd.stream().map(EmployeeAttendanceEntity::getEmployeeDetails).map(EmployeeDetailsEntity::getId).collect(Collectors.toSet());
        List<Integer> size = new ArrayList<>();
                e1.forEach(e2 -> {List<EmployeeAttendanceEntity> e = empAtd.stream().filter(EmployeeAttendanceEntity::isHoliday).filter(empAtd1 -> empAtd1.getEmployeeDetails().getId().equalsIgnoreCase(e2)).collect(Collectors.toList()); size.add(e.size());});
        List<EmployeeAttendanceEntity> empAtt = empAtd.stream().filter(EmployeeAttendanceEntity::isHoliday).collect(Collectors.toList());
        System.out.println(empAtt);
      Set<EmployeeDetailsEntity> e = e1.stream().map(em -> employeeDetailsRepo.findById(em).orElse(null)).collect(Collectors.toSet());
        List<EmployeeAttendanceSalary> eAS = new ArrayList<>();
        AtomicInteger i =  new AtomicInteger();
        e.forEach(a -> {
            EmployeeAttendanceSalary employeeAttendanceSalary = new EmployeeAttendanceSalary(
                    a.getId(), a.getName(),
                    a.getEmployeeAddressSet().stream().map(EmployeeAddressEntity::getPhoneNumber).collect(Collectors.toList()),
                    a.getEmployeeSalary().getSalary(), size.get(i.getAndIncrement()));
            eAS.add(employeeAttendanceSalary);
        });
        return eAS;

    }
    public  EmployeeSalaryDetails getEmployeeWithSalary(EmployeeDetailsEntity employeeDetailsEntity){
        return new EmployeeSalaryDetails(
                employeeDetailsEntity.getId(),employeeDetailsEntity.getName(),employeeDetailsEntity.getEmail(),
                employeeDetailsEntity.getEmployeeSalary().getSalary(),employeeDetailsEntity.getEmployeeSalary().getPayable());
    }
    public EmployeeDetails getEmployeeModel(EmployeeDetailsEntity employeeDetailsEntity){
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setId(employeeDetailsEntity.getId());
        employeeDetails.setName(employeeDetailsEntity.getName());
        employeeDetails.setEmail(employeeDetailsEntity.getEmail());
        EmployeeSalary employeeSalary = new EmployeeSalary();
        employeeSalary.setId(employeeDetailsEntity.getEmployeeSalary().getId());
        employeeSalary.setSalary(employeeDetailsEntity.getEmployeeSalary().getSalary());
        employeeSalary.setPayable(employeeDetailsEntity.getEmployeeSalary().getPayable());
        employeeDetails.setEmployeeSalaryList(employeeSalary);
        List<EmployeeAddress> employeeAddresses = new ArrayList<>();
        employeeDetailsEntity.getEmployeeAddressSet().forEach(employeeAddressEntity -> {
            EmployeeAddress employeeAddress = new EmployeeAddress();
            employeeAddress.setLine1(employeeAddressEntity.getLine1());
            employeeAddress.setLine2(employeeAddressEntity.getLine2());
            employeeAddress.setId(employeeAddressEntity.getId());
            employeeAddress.setCity(employeeAddressEntity.getCity());
            employeeAddress.setCountry(employeeAddressEntity.getCountry());
            employeeAddress.setPhoneNumber(employeeAddressEntity.getPhoneNumber());
            employeeAddresses.add(employeeAddress);
        });
        employeeDetails.setEmployeeAddressList(employeeAddresses);
        List<EmployeeAttendance> employeeAttendances = new ArrayList<>();
        employeeDetailsEntity.getEmployeeAttendanceList().forEach(employeeAttendanceEntity -> {
            EmployeeAttendance employeeAttendance = new EmployeeAttendance();
            employeeAttendance.setId(employeeAttendanceEntity.getId());
            employeeAttendance.setDate(employeeAttendanceEntity.getDate());
            employeeAttendance.setHoliday(employeeAttendanceEntity.isHoliday());
            employeeAttendance.setReasonForHoliday(employeeAttendanceEntity.getReasonForHoliday());
            employeeAttendances.add(employeeAttendance);
        });
        employeeDetails.setEmployeeAttendanceList(employeeAttendances);
        return  employeeDetails;
    }
}

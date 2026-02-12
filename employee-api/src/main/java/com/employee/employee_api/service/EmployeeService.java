package com.employee.employee_api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.employee_api.model.Employee;
import com.employee.employee_api.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    // GET ALL
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    // GET BY ID
    public Employee getEmployeeById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // SAVE
    public Employee saveEmployee(Employee emp) {
        return repo.save(emp);
    }

    // DELETE
    public void deleteEmployee(Long id) {
        repo.deleteById(id);
        
    }

//UPDATE
public Employee updateEmployee(Long id, Employee emp) {

 Employee existing = repo.findById(id).orElse(null);

 if(existing != null) {
     existing.setName(emp.getName());
     existing.setEmail(emp.getEmail());
     existing.setDepartment(emp.getDepartment());

     return repo.save(existing);
 }

 return null;
}}


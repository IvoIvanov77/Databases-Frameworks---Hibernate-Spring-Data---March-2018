package com.example.demo.dtos;

import com.example.demo.entities.Employee;

import java.util.List;

public class ManagerDto extends EmployeeDto {

    private List<EmployeeDto> employees;

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }
}

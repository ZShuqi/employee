package com.example.employee;

import com.example.employee.Employees;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class EmployeeManager {

    private static Employees list = new Employees();

    // Manually initialize service to contain some employees
    static
    {
        Employee employee1 = new Employee(1, "Sarah", "Walker", "sarah@abc.com", "Backend Engineer");
        Employee employee2 = new Employee(2, "Noah", "Lewis", "noah@abc.com", "Team Leader");
        Employee employee3 = new Employee(3, "Lyn", "Stevens", "lyn@abc.com", "Manager");

        list.getEmployeeList().add(employee1);
        list.getEmployeeList().add(employee2);
        list.getEmployeeList().add(employee3);
    }

    public Employees getAllEmployees()
    {
        return list;
    }

    public void addEmployee(Employee employee)
    {
        list.getEmployeeList().add(employee);
    }
}

package com.example.employee;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.employee.Employee;
import com.example.employee.Employees;
import com.example.employee.EmployeeManager;


// Creating the REST controller
// a controller class is the actual implementation of the REST API
@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeManager employeeManager;

    @GetMapping(path = "/", produces = "application/json")
    public Employees getEmployees()
    {
        return employeeManager.getAllEmployees();
    }

    // @GetMapping(path = "/{employee_id}")
    // public String getEmployee(@PathVariable String employee_id)
    // {
    //     return "employee called is " + employee_id;
    // }

    // @GetMapping(path = "/{employee_id}", produces = "application/json")
    // public EmployeeManager getEmployee(@PathVariable String employee_id)
    // {
    //     return "employee called is " + employee_id;
    // }


    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee)
    {
        // Creating an ID of an employee
        // from the number of employees
        Integer id
            = employeeManager
                  .getAllEmployees()
                  .getEmployeeList()
                  .size()
              + 1;

        employee.setID(id);

        employeeManager.addEmployee(employee);

        URI location = ServletUriComponentsBuilder
                  .fromCurrentRequest()
                  .path("/{id}")
                  .buildAndExpand(employee.getId())
                  .toUri();

        return ResponseEntity.created(location).build();
    }
}

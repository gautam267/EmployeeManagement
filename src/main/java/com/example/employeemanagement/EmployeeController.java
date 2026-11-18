package com.example.employeemanagement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private List<Employee> employees = new ArrayList<>();

    private int nextId = 1;

    // Add new employee
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {

        employee.setId(nextId++);

        employees.add(employee);

        return employee;
    }


    // Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {

        for (Employee e : employees) {
            if (e.getId() == id) {
                return e;
            }
        }

        return null;
    }


    // Update employee by ID
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable int id,
            @RequestBody Employee updatedEmployee) {

        for (Employee e : employees) {

            if (e.getId() == id) {

                e.setName(updatedEmployee.getName());
                e.setDepartment(updatedEmployee.getDepartment());
                e.setSalary(updatedEmployee.getSalary());

                return ResponseEntity.ok(e);
            }
        }

        return ResponseEntity.notFound().build();
    }


    // Delete employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {

        boolean removed = employees.removeIf(e -> e.getId() == id);

        if (removed) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}

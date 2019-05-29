package com.neotic.assignment.controllers;

import com.neotic.assignment.domain.Employee;
import com.neotic.assignment.dto.EmployeeDto;
import com.neotic.assignment.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        Map<String, Object> response = new HashMap<>();
        Employee employee = new Employee().buildWithDto(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        if (savedEmployee == null) {
            response.put("status", false);
            response.put("message", "employee not added");
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        } else {
            response.put("status", true);
            response.put("message", "employee added");
            response.put("data", savedEmployee);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getEmployeeById(@PathVariable("id") Integer employeeId) {
        Map<String, Object> response = new HashMap<>();
        Employee employee = employeeRepository.findOne(employeeId);
        if (employee == null) {
            response.put("status", false);
            response.put("message", "employee not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.put("status", true);
            response.put("message", "employee found");
            response.put("data", employee);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/get/list", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllEmployees() {
        Map<String, Object> response = new HashMap<>();
        List<Employee> employeeList = employeeRepository.findAll();
        if (employeeList.isEmpty()) {
            response.put("status", false);
            response.put("message", "employees not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.put("status", true);
            response.put("message", "employees found");
            response.put("data", employeeList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        Map<String, Object> response = new HashMap<>();
        Employee employee = new Employee().buildWithDto(employeeDto);
        Employee updatedEmployee = employeeRepository.save(employee);
        if (updatedEmployee == null) {
            response.put("status", false);
            response.put("message", "employee not updated");
            return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
        } else {
            response.put("status", true);
            response.put("message", "employee updated");
            response.put("data", updatedEmployee);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable("id") Integer employeeId) {
        Map<String, Object> response = new HashMap<>();
        try {
            employeeRepository.delete(employeeId);
            response.put("status", true);
            response.put("message", "employee deleted");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", false);
            response.put("message", "employee not deleted");
            return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
        }
    }
}

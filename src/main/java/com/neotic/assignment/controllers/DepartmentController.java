package com.neotic.assignment.controllers;

import com.neotic.assignment.domain.Department;
import com.neotic.assignment.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> saveDepartment(@RequestBody Department department) {
        Map<String, Object> response = new HashMap<>();
        Department savedDepartment = departmentRepository.save(department);
        if (savedDepartment == null) {
            response.put("status", false);
            response.put("message", "department not added");
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        } else {
            response.put("status", true);
            response.put("message", "department added");
            response.put("data", savedDepartment);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getDepartmentsById(@PathVariable("id") Integer departmentId) {
        Map<String, Object> response = new HashMap<>();
        Department department = departmentRepository.findOne(departmentId);
        if (department == null) {
            response.put("status", false);
            response.put("message", "department not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.put("status", true);
            response.put("message", "department found");
            response.put("data", department);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/get/list", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllDepartments() {
        Map<String, Object> response = new HashMap<>();
        List<Department> departmentList = departmentRepository.findAll();
        if (departmentList.isEmpty()) {
            response.put("status", false);
            response.put("message", "departments not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.put("status", true);
            response.put("message", "departments found");
            response.put("data", departmentList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> updateDepartment(@RequestBody Department department) {
        Map<String, Object> response = new HashMap<>();
        Department updatedDepartment = departmentRepository.save(department);
        if (updatedDepartment == null) {
            response.put("status", false);
            response.put("message", "department not updated");
            return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
        } else {
            response.put("status", true);
            response.put("message", "department updated");
            response.put("data", updatedDepartment);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> deleteDepartment(@PathVariable("id") Integer departmentId) {
        Map<String, Object> response = new HashMap<>();
        try {
            departmentRepository.delete(departmentId);
            response.put("status", true);
            response.put("message", "department deleted");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", false);
            response.put("message", "department not deleted");
            return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
        }
    }
}

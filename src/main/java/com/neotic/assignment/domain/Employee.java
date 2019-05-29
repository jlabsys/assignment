package com.neotic.assignment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neotic.assignment.dto.EmployeeDto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author shehara
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "employee_id")
    private String employeeId;
    @JoinColumn(name = "department", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Department department;

    public Employee() {
    }

    public Employee(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee buildWithDto(EmployeeDto dto) {
        this.id = dto.getId();
        this.employeeId = dto.getEmployeeId();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        Department department = new Department(dto.getDepartmentId());
        this.department = department;
        return this;
    }

}

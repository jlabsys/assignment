package com.neotic.assignment.repositories;

import com.neotic.assignment.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}

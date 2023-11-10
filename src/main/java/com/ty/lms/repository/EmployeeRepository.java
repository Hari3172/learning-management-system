package com.ty.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.lms.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

	Optional<Employee> findByEmployeeEmailId(String username);

}

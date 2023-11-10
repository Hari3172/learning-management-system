package com.ty.lms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.lms.entity.EmployeeAttendence;

public interface AttendenceRepository extends JpaRepository<EmployeeAttendence, Integer> {

	List<EmployeeAttendence> findAllByAttendanceDate(LocalDate localDate);

}

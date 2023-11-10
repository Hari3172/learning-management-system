package com.ty.lms.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class EmployeeAttendence {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_seq")
	@SequenceGenerator(name = "attendance_seq", sequenceName = "attendance_seq", allocationSize = 1, initialValue = 1)
	private int attendanceId;
	private LocalDate attendanceDate;
	private Boolean attendenceMorning;
	private Boolean attendenceNoon;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "employeeAttendences")
	private List<Employee> employees;
}

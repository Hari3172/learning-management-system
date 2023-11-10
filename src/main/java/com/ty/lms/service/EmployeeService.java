package com.ty.lms.service;

import java.util.List;

import com.ty.lms.dto.AttendanceDTO;
import com.ty.lms.dto.EmployeeDTO;
import com.ty.lms.dto.EmployeeRegisterDTO;
import com.ty.lms.dto.EmployeeStatusDTO;
import com.ty.lms.dto.MockDetailsDTO;
import com.ty.lms.dto.PasswordResetDTO;

import jakarta.validation.Valid;

public interface EmployeeService {

	EmployeeDTO getEmployeeById(String employeeId);

	List<EmployeeDTO> getAllEmployees();

	Boolean updateEmployee(String employeeId, @Valid EmployeeRegisterDTO employeeUpdateDTO);

	Boolean addAttendance(String employeeId, @Valid AttendanceDTO attendanceDTO);

	List<AttendanceDTO> getEmployeeAttendanceByEmployeeId(String employeeId);

	List<MockDetailsDTO> getEmployeeMockRatingById(String employeeId);

	Boolean setEmployeeMockRating(String employeeId, Integer mockId, MockDetailsDTO mockDetailsDTO);

	Boolean updateEmployeeStatus(String employeeId, EmployeeStatusDTO employeeStatusDTO);

	Boolean resetPassword(@Valid PasswordResetDTO passwordResetDTO);

}


package com.ty.lms.controller;

import static com.ty.lms.constant.EmployeeConstants.EMPLOYEE_DATA_PROVIDED;
import static com.ty.lms.constant.EmployeeConstants.EMPLOYEE_DATA_UPDATED;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.lms.dto.AttendanceDTO;
import com.ty.lms.dto.EmployeeRegisterDTO;
import com.ty.lms.dto.EmployeeStatusDTO;
import com.ty.lms.dto.MockDetailsDTO;
import com.ty.lms.dto.PasswordResetDTO;
import com.ty.lms.exception.MockNotAttendedException;
import com.ty.lms.response.ApiResponse;
import com.ty.lms.service.EmployeeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "javainuseapi")
@RequestMapping(path = "/api/v1/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	@GetMapping(path = "/{employee-id}")
	public ResponseEntity<ApiResponse> getEmployee(@PathVariable(name = "employee-id") String employeeId) {
		log.info("GET request received for /api/v1/employees/{employee-id} with employee ID: {}", employeeId);
		return ResponseEntity.<ApiResponse>ok(ApiResponse.builder().data(employeeService.getEmployeeById(employeeId))
				.message(EMPLOYEE_DATA_PROVIDED).timestamp(LocalDateTime.now()).build());
	}

	@GetMapping(path = "/")
	public ResponseEntity<ApiResponse> getEmployees() {
		log.info("GET request received for /api/v1/employees/");
		return ResponseEntity.<ApiResponse>ok(ApiResponse.builder().data(employeeService.getAllEmployees())
				.message(EMPLOYEE_DATA_PROVIDED).timestamp(LocalDateTime.now()).build());
	}

	@PutMapping(path = "/{employee-id}")
	public ResponseEntity<ApiResponse> updateEmployee(@PathVariable(name = "employee-id") String employeeId,
			@RequestBody @Valid EmployeeRegisterDTO employeeUpdateDTO) {
		log.info("PUT request received for /api/v1/employees/{employee-id} with employee ID: {} and data: {}",
				employeeId, employeeUpdateDTO);
		return ResponseEntity.<ApiResponse>ok(
				ApiResponse.builder().data(employeeService.updateEmployee(employeeId, employeeUpdateDTO))
						.message(EMPLOYEE_DATA_UPDATED).timestamp(LocalDateTime.now()).build());
	}

	@PutMapping(path = "/{employee-id}/attendance")
	public ResponseEntity<ApiResponse> setAttendance(@PathVariable(name = "employee-id") String employeeId,
			@RequestBody AttendanceDTO attendanceDTO) {
		log.info(
				"PUT request received for /api/v1/employees/{employee-id}/attendance with employee ID: {} and data: {}",
				employeeId, attendanceDTO);
		return new ResponseEntity<ApiResponse>(
				ApiResponse.builder().data(employeeService.addAttendance(employeeId, attendanceDTO))
						.message(EMPLOYEE_DATA_UPDATED).timestamp(LocalDateTime.now()).build(),
				HttpStatus.ACCEPTED);

	}

	@GetMapping(path = "/{employee-id}/attendance")
	public ResponseEntity<ApiResponse> getEmployeeAttendance(@PathVariable(name = "employee-id") String employeeId) {
		log.info("GET request received for /api/v1/employees/{employee-id}/attendance with employee ID: {}",
				employeeId);
		return ResponseEntity.<ApiResponse>of(Optional
				.ofNullable(ApiResponse.builder().data(employeeService.getEmployeeAttendanceByEmployeeId(employeeId))
						.message(EMPLOYEE_DATA_PROVIDED).timestamp(LocalDateTime.now()).build()));
	}

	@GetMapping(path = "/{employee-id}/mock-rating")
	public ResponseEntity<ApiResponse> getEmployeeMockRating(@PathVariable(name = "employee-id") String employeeId) {
		log.info("GET request received for /api/v1/employees/{employee-id}/mock-rating with employee ID: {}",
				employeeId);
		return ResponseEntity
				.<ApiResponse>ok(ApiResponse.builder().data(employeeService.getEmployeeMockRatingById(employeeId))
						.message(EMPLOYEE_DATA_PROVIDED).timestamp(LocalDateTime.now()).build());
	}

	@PutMapping(path = "/{employee-id}/mock-rating/{mock-id}")
	public ResponseEntity<ApiResponse> setEmployeeMockRating(@PathVariable(name = "employee-id") String employeeId,
			@PathVariable(name = "mock-id") Integer mockId, @RequestBody MockDetailsDTO mockDetailsDTO) {
		log.info(
				"PUT request received for /api/v1/employees/{employee-id}/mock-rating/{mock-id} with employee ID: {} and mock ID: {} with data: {}",
				employeeId, mockId, mockDetailsDTO);
		Boolean isSetMockRating = employeeService.setEmployeeMockRating(employeeId, mockId, mockDetailsDTO);
		if (isSetMockRating) {
			log.info("Mock rating set successfully for employee with employee ID: {} and mock ID: {}", employeeId,
					mockId);
			return ResponseEntity.<ApiResponse>ok(ApiResponse.builder().data(isSetMockRating)
					.message(EMPLOYEE_DATA_UPDATED).timestamp(LocalDateTime.now()).build());
		}
		log.error("Failed to set mock rating for employee with employee ID: {} and mock ID: {}", employeeId, mockId);
		throw new MockNotAttendedException("Mock not attended");
	}

	@PutMapping(path = "/{employee-id}/change-status")
	public ResponseEntity<ApiResponse> updateEmployeeStatus(@PathVariable(name = "employee-id") String employeeId,
			@RequestBody EmployeeStatusDTO employeeStatusDTO) {
		log.info(
				"PUT request received for /api/v1/employees/{employee-id}/change-status with employee ID: {} and data: {}",
				employeeId, employeeStatusDTO);
		return ResponseEntity.<ApiResponse>ok(
				ApiResponse.builder().data(employeeService.updateEmployeeStatus(employeeId, employeeStatusDTO))
						.message(EMPLOYEE_DATA_UPDATED).timestamp(LocalDateTime.now()).build());
	}

	@PutMapping(path = "/reset-password")
	public ResponseEntity<ApiResponse> resetPassword(@RequestBody @Valid PasswordResetDTO passwordResetDTO) {
		log.info("PUT request received for /api/v1/employees/reset-password with data: {}", passwordResetDTO);
		return new ResponseEntity<ApiResponse>(
				ApiResponse.builder().data(employeeService.resetPassword(passwordResetDTO))
						.message(EMPLOYEE_DATA_UPDATED).timestamp(LocalDateTime.now()).build(),
				HttpStatus.ACCEPTED);
	}

}

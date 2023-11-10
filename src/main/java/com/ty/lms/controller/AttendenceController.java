package com.ty.lms.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.lms.response.ApiResponse;
import com.ty.lms.service.AttendenceService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "javainuseapi")
@RequestMapping(path = "/api/v1/attendences")
public class AttendenceController {

	private final AttendenceService attendenceService;

	@GetMapping(path = "/{attendence-date}")
	public ResponseEntity<ApiResponse> getAttendance(@PathVariable(name = "attendence-date") LocalDate localDate) {
		log.info("GET request received for /api/v1/attendance/{attendence-date} with attendence data: {}", localDate);
		return ResponseEntity
				.<ApiResponse>ok(ApiResponse.builder().data(attendenceService.getAttendenceByDate(localDate))
						.message("Attendence Provided...!!").timestamp(LocalDateTime.now()).build());
	}
}

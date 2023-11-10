package com.ty.lms.controller;

import static com.ty.lms.constant.EmployeeConstants.EMPLOYEE_APPROVED;
import static com.ty.lms.constant.EmployeeConstants.REQUEST_DATA_PROVIDED;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.lms.dto.RequestDTO;
import com.ty.lms.response.ApiResponse;
import com.ty.lms.service.RequestService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "javainuseapi")
@RequestMapping(path = "/api/v1/requests")
public class RequestController {

	private final RequestService requestService;

	@GetMapping(path = "/")
	public ResponseEntity<ApiResponse> requests() {
		log.info("GET request received for /api/v1/requests");
		return new ResponseEntity<ApiResponse>(ApiResponse.builder().data(requestService.getAllRequest())
				.message(REQUEST_DATA_PROVIDED).timestamp(LocalDateTime.now()).build(), HttpStatus.ACCEPTED);
	}

	@PutMapping(path = "/approve")
	public ResponseEntity<ApiResponse> requestApprove(@RequestBody @Valid RequestDTO requestDTO) {
		log.info("PUT request received for /api/v1/requests/approve with data: {}", requestDTO);
		return new ResponseEntity<ApiResponse>(ApiResponse.builder().data(requestService.requestApprove(requestDTO))
				.message(EMPLOYEE_APPROVED).timestamp(LocalDateTime.now()).build(), HttpStatus.ACCEPTED);
	}

}

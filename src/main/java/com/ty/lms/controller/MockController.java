package com.ty.lms.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.lms.dto.MockDTO;
import com.ty.lms.response.ApiResponse;
import com.ty.lms.service.BatchService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "javainuseapi")
@RequestMapping(path = "/api/v1/mocks")
public class MockController {

	private final BatchService batchService;

	@PostMapping(path = "/{batch-id}")
	public ResponseEntity<ApiResponse> setMockToBatch(@PathVariable(name = "batch-id") String batchId,
			@RequestBody MockDTO mockDTO) {
		log.info("POST request received for /api/v1/mocks/{batch-id} with batch ID: {} and data: {}", batchId, mockDTO);
		return new ResponseEntity<ApiResponse>(ApiResponse.builder().data(batchService.setMockToBatch(batchId, mockDTO))
				.message("somthing").timestamp(LocalDateTime.now()).build(), HttpStatus.ACCEPTED);

	}

}

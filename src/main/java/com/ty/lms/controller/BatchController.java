package com.ty.lms.controller;

import static com.ty.lms.constant.BatchConstants.BATCH_ALREADY_EXIST;
import static com.ty.lms.constant.BatchConstants.BATCH_DATA_PROVIDED;
import static com.ty.lms.constant.BatchConstants.BATCH_DATA_UPDATED;
import static com.ty.lms.constant.BatchConstants.BATCH_DELETED;
import static com.ty.lms.constant.BatchConstants.BATCH_REGISTERED;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.lms.dto.BatchDTO;
import com.ty.lms.exception.BatchAlreadyExistException;
import com.ty.lms.response.ApiResponse;
import com.ty.lms.service.BatchService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "javainuseapi")
@RequestMapping(path = "/api/v1/batchs")
public class BatchController {

	private final BatchService batchService;

	@GetMapping(path = "/{batch-name}/dashboard")
	public ResponseEntity<ApiResponse> getDashboardDetailsByBatchName(
			@PathVariable(name = "batch-name") String batchNames) {
		log.info("GET request received for /api/v1/batches/{batch-name}/dashboard with batch name: {}", batchNames);
		return new ResponseEntity<ApiResponse>(
				ApiResponse.builder().data(batchService.getDashboardDetailsByBatchName(batchNames))
						.message("Dashboard data provided!!").timestamp(LocalDateTime.now()).build(),
				HttpStatus.ACCEPTED);

	}

	@GetMapping(path = "/{batch-name}")
	public ResponseEntity<ApiResponse> getBatchEmployees(@PathVariable(name = "batch-name") String batchName) {
		log.info("GET request received for /api/v1/batches/{batch-name} with batch name: {}", batchName);
		return new ResponseEntity<ApiResponse>(
				ApiResponse.builder().data(batchService.getBatchEmployees(batchName))
						.message("Dashboard data provided!!").timestamp(LocalDateTime.now()).build(),
				HttpStatus.ACCEPTED);

	}

	@GetMapping(path = "/")
	public ResponseEntity<ApiResponse> getAllBatch() {
		log.info("GET request received for /api/v1/batches/");
		return new ResponseEntity<ApiResponse>(ApiResponse.builder().data(batchService.getAllBatch())
				.message(BATCH_DATA_PROVIDED).timestamp(LocalDateTime.now()).build(), HttpStatus.ACCEPTED);
	}

	@PostMapping(path = "/")
	public ResponseEntity<ApiResponse> setBatch(@RequestBody BatchDTO batchDTO) {
		log.info("POST request received for /api/v1/batches/ with data: {}", batchDTO);
		String batchId = batchService.register(batchDTO);
		if (batchId != null) {
			log.info("Batch registration successful for batch: {}", batchId);
			return new ResponseEntity<ApiResponse>(ApiResponse.builder().data(batchId).message(BATCH_REGISTERED)
					.timestamp(LocalDateTime.now()).build(), HttpStatus.ACCEPTED);
		}
		log.error("Batch registration failed. Batch already exists.");
		throw new BatchAlreadyExistException(BATCH_ALREADY_EXIST);

	}

	@PutMapping(path = "/{batch-name}")
	public ResponseEntity<ApiResponse> updateBatch(@PathVariable(name = "batch-name") String batchName,
			@RequestBody BatchDTO batchDTO) {
		log.info("PUT request received for /api/v1/batches/{batch-name} with batch name: {} and data: {}", batchName, batchDTO);
		return new ResponseEntity<ApiResponse>(ApiResponse.builder().data(batchService.update(batchName, batchDTO))
				.message(BATCH_DATA_UPDATED).timestamp(LocalDateTime.now()).build(), HttpStatus.ACCEPTED);

	}

	@DeleteMapping(path = "/{batch-name}")
	public ResponseEntity<ApiResponse> deleteBatch(@PathVariable(name = "batch-name") String batchName) {
		log.info("DELETE request received for /api/v1/batches/{batch-name} with batch name: {}", batchName);
		return new ResponseEntity<ApiResponse>(ApiResponse.builder().data(batchService.delete(batchName))
				.message(BATCH_DELETED).timestamp(LocalDateTime.now()).build(), HttpStatus.ACCEPTED);

	}

}

package com.ty.lms.service;

import java.util.List;

import com.ty.lms.dto.BatchDTO;
import com.ty.lms.dto.DashboardDTO;
import com.ty.lms.dto.EmployeeDTO;
import com.ty.lms.dto.MockDTO;

public interface BatchService {

	DashboardDTO getDashboardDetailsByBatchName(String batchNames);

	BatchDTO getBatchByBatchName(String batchNames);

	Boolean setMockToBatch(String batchId, MockDTO mockDTO);

	List<BatchDTO> getAllBatch();

	List<EmployeeDTO> getBatchEmployees(String batchName);

	String register(BatchDTO batchDTO);

	Boolean update(String batchName, BatchDTO batchDTO);

	Boolean delete(String batchName);

}

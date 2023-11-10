package com.ty.lms.service.implementation;

import static com.ty.lms.constant.BatchConstants.BATCH_NOT_FOUND;
import static com.ty.lms.constant.EmployeeConstants.EMPLOYEE_NOT_FOUND;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ty.lms.dto.RequestDTO;
import com.ty.lms.entity.Batch;
import com.ty.lms.entity.Employee;
import com.ty.lms.entity.Request;
import com.ty.lms.exception.BatchNotFoundException;
import com.ty.lms.exception.EmployeeNotFoundException;
import com.ty.lms.repository.BatchRepository;
import com.ty.lms.repository.EmployeeRepository;
import com.ty.lms.repository.RequestRepository;
import com.ty.lms.service.RequestService;
import com.ty.lms.util.EmployeeUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RequestServiceImpl implements RequestService {

	private final RequestRepository requestRepository;
	private final EmployeeRepository employeeRepository;
	private final BatchRepository batchRepository;
	private final EmployeeUtils employeeUtils;

	@Override
	public List<RequestDTO> getAllRequest() {
		log.info("Retrieving all requests.");
		List<Request> requests = requestRepository.findAll();
		return requests.stream().filter(e -> {
			if (e.getIsRejected() == null)
				return true;
			return false;
		}).map(employeeUtils::entityToDto).collect(Collectors.toList());
	}

	@Override
	public Boolean requestApprove(@Valid RequestDTO requestDTO) {
		log.info("Processing request approval for Employee ID: {}", requestDTO.getEmployeeId());
		Employee employee = employeeRepository.findById(requestDTO.getEmployeeId())
				.orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));
		Request request = employee.getRequest();
		if (requestDTO.getIsRejected() == true) {
			request.setRejectionReason(requestDTO.getRejectionReason());
			request.setIsRejected(true);
		} else if (requestDTO.getIsRejected() == false) {
			request.setIsRejected(false);
			Batch batch = batchRepository.findById(requestDTO.getBatchId())
					.orElseThrow(() -> new BatchNotFoundException(BATCH_NOT_FOUND));
			employee.setBatch(batch);
			batch.setEmployees(List.of(employee));
		}
		request.setEmployee(employee);
		requestRepository.save(request);
		log.info("Request approval completed for Employee ID: {}", requestDTO.getEmployeeId());
		return true;
	}

}

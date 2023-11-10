package com.ty.lms.service.implementation;

import static com.ty.lms.constant.BatchConstants.BATCH_NOT_FOUND;
import static com.ty.lms.constant.EmployeeConstants.EMPLOYEE_NOT_FOUND;
import static com.ty.lms.constant.MentorConstants.MENTOR_NOT_FOUND;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ty.lms.dto.BatchDTO;
import com.ty.lms.dto.DashboardDTO;
import com.ty.lms.dto.EmployeeDTO;
import com.ty.lms.dto.MockDTO;
import com.ty.lms.entity.Batch;
import com.ty.lms.entity.Education;
import com.ty.lms.entity.Employee;
import com.ty.lms.entity.Mentor;
import com.ty.lms.entity.Mock;
import com.ty.lms.entity.MockDetails;
import com.ty.lms.entity.enums.Performance;
import com.ty.lms.exception.BatchNotFoundException;
import com.ty.lms.exception.EmployeeNotFoundException;
import com.ty.lms.exception.MentorNotFoundException;
import com.ty.lms.repository.BatchRepository;
import com.ty.lms.repository.EmployeeRepository;
import com.ty.lms.repository.MentorRepository;
import com.ty.lms.repository.MockDetailsRepository;
import com.ty.lms.repository.MockRepository;
import com.ty.lms.service.BatchService;
import com.ty.lms.util.BatchUtils;
import com.ty.lms.util.EmployeeUtils;
import com.ty.lms.util.MockUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BatchServiceImpl implements BatchService {

	private final BatchRepository batchRepository;
	private final MockRepository mockRepository;
	private final MockDetailsRepository mockDetailsRepository;
	private final MentorRepository mentorRepository;
	private final EmployeeRepository employeeRepository;
	private final EmployeeUtils employeeUtils;

	@Override
	public DashboardDTO getDashboardDetailsByBatchName(String batchNames) {
		log.info("Retrieving dashboard details for batch: {}", batchNames);

		Batch batch = batchRepository.findByBatchName(batchNames).orElseThrow(() -> {
			log.error("Error while retrieving dashboard details for batch {}: {}", batchNames, BATCH_NOT_FOUND);
			throw new BatchNotFoundException(BATCH_NOT_FOUND);
		});

		List<Employee> employees = batch.getEmployees();

		log.debug("Retrieved employees: {}", employees);

		Performance batchPerformance = batch.getBatchPerformance();

		List<HashMap<String, Object>> list = employees.stream().map(e -> {
			HashMap<String, Object> hashMap = new HashMap<>();

			hashMap.put("Gender", e.getEmployeeGender());

			Set<Education> educations = e.getEducations();

			log.debug("Retrieved educations: {}", educations);

			int max = 0;
			Iterator<Education> iterator = educations.iterator();
			while (iterator.hasNext()) {
				Education ed = iterator.next();
				if (max < ed.getYearOfPassing().getValue()) {
					hashMap.put("Year", Year.of(ed.getYearOfPassing().getValue()));
					hashMap.put("Degree", ed.getEducationType());
				}
			}

			/*
			 * In Stream API
			 * 
			 * e.getEducations().stream()
			 * .collect(Collectors.maxBy(Comparator.comparing(Education::getYearOfPassing)))
			 * .map(ed -> { hashMap.put("Year", ed.getYearOfPassing().getValue());
			 * hashMap.put("Degree", ed.getEducationType()); return hashMap; });
			 *
			 */

			hashMap.put("Experience", e.getExperiences().stream().map(exp -> {

				if (exp.getDateOfReliening() == null) {
					return LocalDate.now().compareTo(exp.getDateOfJoining());
				} else {
					return exp.getDateOfJoining().compareTo(exp.getDateOfJoining());
				}

			}).collect(Collectors.summingInt(Integer::valueOf)));
			return hashMap;
		}).collect(Collectors.toList());

		log.debug("Computed list: {}", list);
		log.info("Dashboard details retrieved successfully for batch: {}", batchNames);
		return BatchUtils.entityToDTO(list, batchPerformance);
	}

	@Override
	public BatchDTO getBatchByBatchName(String batchNames) {
		log.info("Retrieving batch details for batch: {}", batchNames);
		return BatchUtils.entityToDTO(batchRepository.findByBatchName(batchNames)
				.orElseThrow(() -> new BatchNotFoundException(BATCH_NOT_FOUND)));
	}

	@Override
	public Boolean setMockToBatch(String batchId, MockDTO mockDTO) {
		log.info("Setting mock for batch: {}", batchId);

		Batch batch = batchRepository.findById(batchId).orElseThrow(() -> {
			log.error("Error while setting mock for batch {}: {}", batchId, BATCH_NOT_FOUND);
			throw new BatchNotFoundException(BATCH_NOT_FOUND);
		});

		if (batch.getEmployees().size() <= 0) {
			log.error("Error while setting mock for batch {}: {}", batchId);
			throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND);
		}

		Mentor mentor = mentorRepository.findById(mockDTO.getMentorId()).orElseThrow(() -> {
			log.error("Error while setting mock for batch {}: {}", batchId, MENTOR_NOT_FOUND);
			throw new MentorNotFoundException(MENTOR_NOT_FOUND);
		});
		Mock mock = MockUtils.dtoToEntity(mockDTO);
		mock.setBatch(batch);
		mock.setMentors(new ArrayList<>(Arrays.asList(mentor)));

		MockDetails mockDetails = MockDetails.builder().mock(mock).mockType("Mock").build();
		mockDetailsRepository.save(mockDetails);

		List<Employee> employees = batch.getEmployees().stream().map(e -> {
			e.setMockDetails(new ArrayList<>(Arrays.asList(mockDetails)));
			return e;
		}).collect(Collectors.toList());

		mockDetails.setEmployees(employees);

		employeeRepository.saveAll(employees);
		mockRepository.save(mock);
		log.info("Mock set successfully for batch: {}", batchId);

		return true;
	}

	@Override
	public List<BatchDTO> getAllBatch() {
		log.info("Retrieving all batches");
		return batchRepository.findAll().stream().map(BatchUtils::entityToDTO).collect(Collectors.toList());
	}

	@Override
	public List<EmployeeDTO> getBatchEmployees(String batchName) {
		log.info("Retrieving employees for batch: {}", batchName);
		return batchRepository.findByBatchName(batchName).orElseThrow(() -> new BatchNotFoundException(BATCH_NOT_FOUND))
				.getEmployees().stream().map(employeeUtils::entityToDto).collect(Collectors.toList());
	}

	@Override
	public String register(BatchDTO batchDTO) {
		log.info("Registering a new batch");
		Mentor mentor = mentorRepository.findById(batchDTO.getMentorId()).orElseThrow(() -> {
			log.error("Error while registering a new batch: {}", MENTOR_NOT_FOUND);
			throw new MentorNotFoundException(MENTOR_NOT_FOUND);
		});
		Optional<Batch> batchOP = batchRepository.findById(batchDTO.getBatchId());
		if (batchOP.isPresent()) {
			log.warn("Batch with ID {} already exists. Registration skipped.", batchDTO.getBatchId());
			return null;
		}
		Batch batch = BatchUtils.dtoToEntity(batchDTO);
		batch.setMentors(new ArrayList<>(Arrays.asList(mentor)));
		mentor.setBatchs(new ArrayList<>(Arrays.asList(batch)));
		Batch batch_ = batchRepository.save(batch);
		log.info("Registered a new batch with ID: {}", batch_.getBatchId());
		return batch_.getBatchId();
	}

	@Override
	public Boolean update(String batchName, BatchDTO batchDTO) {
		log.info("Updating batch: {}", batchName);
		Batch batch = batchRepository.findByBatchName(batchName).orElseThrow(() -> {
			log.error("Error while updating batch {}: {}", batchName, BATCH_NOT_FOUND);
			throw new BatchNotFoundException(BATCH_NOT_FOUND);
		});
		BeanUtils.copyProperties(batchDTO, batch);
		batch.setBatchStrength(batchDTO.getBatchStrength());
		batchRepository.save(batch);
		log.info("Batch {} updated successfully", batchName);
		return true;
	}

	@Override
	public Boolean delete(String batchName) {
		log.info("Deleting batch: {}", batchName);
		Batch batch = batchRepository.findByBatchName(batchName).orElseThrow(() -> {
			log.error("Error while deleting batch {}: {}", batchName, BATCH_NOT_FOUND);
			throw new BatchNotFoundException(BATCH_NOT_FOUND);
		});
		batch.setEmployees(null);
		batch.getMentors().stream().forEach(m -> m.setBatchs(null));
		batch.setMentors(null);
		batch.setTechnologies(null);
		batchRepository.delete(batch);
		log.info("Batch {} deleted successfully", batchName);
		return true;
	}
}

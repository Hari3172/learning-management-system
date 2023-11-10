package com.ty.lms.dto;

import java.time.LocalDate;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ty.lms.entity.enums.BatchStatus;
import com.ty.lms.entity.enums.BatchStrength;
import com.ty.lms.entity.enums.Performance;
import com.ty.lms.entity.enums.Technology;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class BatchDTO {

	private String batchId;
	private String batchName;
	private LocalDate batchStartDate;
	private LocalDate batchEndDate;
	
	@Builder.Default
	private Map<BatchStrength, Integer> batchStrength = new EnumMap<>(BatchStrength.class);
	private BatchStatus batchStatus;
	@Enumerated(EnumType.STRING)
	private Performance batchPerformance;
	private String mentorId;
	private List<EmployeeDTO> employees;
	private Set<Technology> technologies;
}

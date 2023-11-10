package com.ty.lms.dto;

import java.time.Year;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ty.lms.entity.enums.Gender;
import com.ty.lms.entity.enums.Performance;

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
public class DashboardDTO {

	private List<Gender> genders;
	private List<Year> yearOfPassing;
	private List<Integer> experience;
	private List<String> employeeDegrees;
	
	@JsonIgnore(value = true)
	private Performance batchPerformance;
}

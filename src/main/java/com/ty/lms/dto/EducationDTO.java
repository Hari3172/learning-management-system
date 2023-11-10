package com.ty.lms.dto;

import java.time.Year;

import com.ty.lms.entity.enums.EducationType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class EducationDTO {

	private EducationType educationType;

	private Year yearOfPassing;

	private Double percentages;

	@NotNull(message = "NULL data passed for universityName")
	@NotBlank(message = "BLANK data passes for universityName")
	private String universityName;

	@NotNull(message = "NULL data passed for instituteName")
	@NotBlank(message = "BLANK data passes for instituteName")
	private String instituteName;

	@NotNull(message = "NULL data passed for specialization")
	@NotBlank(message = "BLANK data passes for specialization")
	private String specialization;

	@NotNull(message = "NULL data passed for state")
	@NotBlank(message = "BLANK data passes for state")
	private String state;

}

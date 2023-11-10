package com.ty.lms.dto;

import java.time.LocalDate;

import com.ty.lms.entity.enums.Designation;

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
public class ExperienceDTO {
	
	
	@NotNull(message = "NULL data passed for companyName")
	@NotBlank(message = "BLANK data passes for companyName")
	private String companyName;

	private LocalDate dateOfJoining;

	private LocalDate dateOfReliening;

	private Designation designation;

	@NotNull(message = "NULL data passed for location")
	@NotBlank(message = "BLANK data passes for location")
	private String location;
}

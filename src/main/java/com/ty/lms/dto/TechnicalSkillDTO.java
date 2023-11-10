package com.ty.lms.dto;

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
public class TechnicalSkillDTO {

	@NotNull(message = "NULL data passed for skillType")
	@NotBlank(message = "BLANK data passes for skillType")
	private String technicalSkillType;

	@NotNull(message = "NULL data passed for skillName")
	@NotBlank(message = "BLANK data passes for skillName")
	private String technicalSkillName;

	@NotNull(message = "NULL data passed for skillRating")
	private Double technicalSkillRating;

	@NotNull(message = "NULL data passed for skillRating")
	private Double yearOfExperience;
}

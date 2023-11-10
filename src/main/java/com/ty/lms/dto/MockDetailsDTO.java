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
public class MockDetailsDTO {

	@NotNull(message = "NULL data passed for mockType")
	@NotBlank(message = "BLANK data passes for mockType")
	private String mockType;

	@NotNull(message = "NULL data passed for practicalKnowledge")
	private Double practicalKnowledge;

	@NotNull(message = "NULL data passed for theoriticalKnowledge")
	private Double theoriticalKnowledge;

	@NotNull(message = "NULL data passed for overallKnowledge")
	private Double overallKnowledge;

	@NotNull(message = "NULL data passed for detailsFeedback")
	@NotBlank(message = "BLANK data passes for detailsFeedback")
	private String detailsFeedback;
	
	private MockDTO mock;
}

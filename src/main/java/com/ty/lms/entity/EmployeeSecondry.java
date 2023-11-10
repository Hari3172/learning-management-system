package com.ty.lms.entity;

import com.ty.lms.entity.enums.MaritalStatus;

import jakarta.persistence.Embeddable;
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
@Embeddable
public class EmployeeSecondry {

	@NotNull(message = "NULL data passed for aadharNumber")
	private long aadharNumber;

	@NotNull(message = "NULL data passed for fatherName")
	@NotBlank(message = "BLANK data passes for fatherName")
	private String panNo;

	@NotNull(message = "NULL data passed for fatherName")
	@NotBlank(message = "BLANK data passes for fatherName")
	private String fatherName;

	@NotNull(message = "NULL data passed for motherName")
	@NotBlank(message = "BLANK data passes for motherName")
	private String motherName;

	@NotNull(message = "NULL data passed for spouseName")
	@NotBlank(message = "BLANK data passes for spouseName")
	private String spouseName;

	@NotNull(message = "NULL data passed for passportNumber")
	@NotBlank(message = "BLANK data passes for passportNumber")
	private String passportNumber;

//	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;
}

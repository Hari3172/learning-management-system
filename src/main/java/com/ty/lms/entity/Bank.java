package com.ty.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Entity
public class Bank {

	@Id
	private Long accountNumber;

	@NotNull(message = "NULL data passed for bankName")
	@NotBlank(message = "BLANK data passes for bankName")
	private String bankName;

	@NotNull(message = "NULL data passed for accountType")
	@NotBlank(message = "BLANK data passes for accountType")
	private String accountType;

	@NotNull(message = "NULL data passed for ifscCode")
	@NotBlank(message = "BLANK data passes for ifscCode")
	private String ifscCode;

	@NotNull(message = "NULL data passed for branch")
	@NotBlank(message = "BLANK data passes for branch")
	private String branch;

	@NotNull(message = "NULL data passed for state")
	@NotBlank(message = "BLANK data passes for state")
	private String state;
}

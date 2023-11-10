package com.ty.lms.dto;

import com.ty.lms.entity.enums.Contacts;

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
public class ContactDTO {
	
	@NotNull(message = "NULL data passed for contactNumber")
	private long contactNumber;
	
	@NotNull(message = "NULL data passed for contactType")
	@NotBlank(message = "BLANK data passes for contactType")
	private Contacts contactType;
}

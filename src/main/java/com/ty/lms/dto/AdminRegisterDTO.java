package com.ty.lms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class AdminRegisterDTO {

	@NotNull
	@NotBlank
	private String username;

	@JsonInclude(value = Include.ALWAYS)
	@NotNull
	@NotBlank
	private String password;

	@NotNull
	@NotBlank
	private String adminName;
}

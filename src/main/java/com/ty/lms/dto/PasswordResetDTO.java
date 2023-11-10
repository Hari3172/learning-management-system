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
public class PasswordResetDTO {

	@NotNull
	@NotBlank
	private String oldPassword;

	@NotNull
	@NotBlank
	private String newPassword;

	@NotNull
	@NotBlank
	private String reEnterPassword;
}

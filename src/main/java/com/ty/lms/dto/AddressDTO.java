package com.ty.lms.dto;

import com.ty.lms.entity.enums.AddressType;

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
public class AddressDTO {

	@NotNull(message = "NULL data passed for addressType")
	@NotBlank(message = "BLANK data passes for addressType")
	private AddressType addressType;

	@NotNull(message = "NULL data passed for doorNumber")
	private Integer doorNumber;

	@NotNull(message = "NULL data passed for street")
	@NotBlank(message = "BLANK data passes for street")
	private String street;

	@NotNull(message = "NULL data passed for locality")
	@NotBlank(message = "BLANK data passes for locality")
	private String locality;

	@NotNull(message = "NULL data passed for city")
	@NotBlank(message = "BLANK data passes for city")
	private String city;

	@NotNull(message = "NULL data passed for states")
	@NotBlank(message = "BLANK data passes for states")
	private String states;

	@NotNull(message = "NULL data passed for pincode")
	private Integer pincode;

	@NotNull(message = "NULL data passed for landmark")
	@NotBlank(message = "BLANK data passes for landmark")
	private String landmark;

}

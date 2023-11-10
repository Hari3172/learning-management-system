
package com.ty.lms.dto;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ty.lms.entity.enums.BloodGroup;
import com.ty.lms.entity.enums.Designation;
import com.ty.lms.entity.enums.EmployeeStatus;
import com.ty.lms.entity.enums.Gender;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class EmployeeRegisterDTO {

	@NotNull
	@NotBlank
	private String password;
	@NotNull
	@NotBlank
	private String employeeName;
	private LocalDate employeeDateOfJoining;
	private LocalDate employeeDateOfBirth;
	@Email
	private String employeeEmailId;
	private BloodGroup employeeBloodGroup;
	private Designation employeeDesignation;
	@Enumerated(EnumType.STRING)
	private Gender employeeGender;
	@NotNull
	@NotBlank
	private String employeeNationality;
	@Builder.Default
	private Map<EmployeeStatus, String> employeeStatus = new EnumMap<>(EmployeeStatus.class);
	private EmployeeSecondryDTO employeeSecondry;
	private Set<EducationDTO> educations;
	private List<AddressDTO> addresses;
	private BankDTO bank;
	private Set<TechnicalSkillDTO> technicalSkill;
	private Set<ExperienceDTO> experiences;
	private List<ContactDTO> contacts;
	private RequestDTO request;

}

package com.ty.lms.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.ty.lms.entity.enums.BloodGroup;
import com.ty.lms.entity.enums.Designation;
import com.ty.lms.entity.enums.EmployeeStatus;
import com.ty.lms.entity.enums.Gender;

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
public class EmployeeDTO {
	private String employeeId;
	private String employeeName;
	private LocalDate employeeDateOfJoining;
	private LocalDate employeeDateOfBirth;
	private String employeeEmailId;
	private BloodGroup employeeBloodGroup;
	private Designation employeeDesignation;
	private Gender employeeGender;
	private String employeeNationality;
	private EmployeeStatus employeeStatus;
	private EmployeeSecondryDTO employeeSecondry;
	private Set<EducationDTO> educations;
	private List<AddressDTO> addresses;
	private BankDTO bank;
	private Set<TechnicalSkillDTO> technicalSkills;
	private Set<ExperienceDTO> experiences;
	private List<ContactDTO> contacts;
}

package com.ty.lms.entity;

import java.time.Year;

import com.ty.lms.entity.enums.EducationType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
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
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "education_seq")
	@SequenceGenerator(name = "education_seq", sequenceName = "education_seq", allocationSize = 1, initialValue = 1)
	private Integer educationId;

	private EducationType educationType;

	private Year yearOfPassing;

	private Double percentages;

	@NotNull(message = "NULL data passed for universityName")
	@NotBlank(message = "BLANK data passes for universityName")
	private String universityName;

	@NotNull(message = "NULL data passed for instituteName")
	@NotBlank(message = "BLANK data passes for instituteName")
	private String instituteName;

	@NotNull(message = "NULL data passed for specialization")
	@NotBlank(message = "BLANK data passes for specialization")
	private String specialization;

	@NotNull(message = "NULL data passed for state")
	@NotBlank(message = "BLANK data passes for state")
	private String state;

	@JoinColumn(name = "employee_fk")
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;
}

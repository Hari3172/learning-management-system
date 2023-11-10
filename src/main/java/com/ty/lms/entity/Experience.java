package com.ty.lms.entity;

import java.time.LocalDate;

import com.ty.lms.entity.enums.Designation;

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
public class Experience {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "experience_seq")
	@SequenceGenerator(name = "experience_seq", sequenceName = "experience_seq", allocationSize = 1, initialValue = 1)
	private Integer experienceId;

	@NotNull(message = "NULL data passed for companyName")
	@NotBlank(message = "BLANK data passes for companyName")
	private String companyName;

	private LocalDate dateOfJoining;

	private LocalDate dateOfReliening;

//	@Enumerated(EnumType.STRING)
	private Designation designation;

	@NotNull(message = "NULL data passed for location")
	@NotBlank(message = "BLANK data passes for location")
	private String location;

	@JoinColumn(name = "employee_fk")
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;

}

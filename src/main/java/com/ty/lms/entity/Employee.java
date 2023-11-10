package com.ty.lms.entity;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import com.ty.lms.entity.enums.BloodGroup;
import com.ty.lms.entity.enums.Designation;
import com.ty.lms.entity.enums.EmployeeStatus;
import com.ty.lms.entity.enums.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
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
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
	@GenericGenerator(name = "employee_seq", strategy = "com.ty.lms.custom.EmployeeId")
	private String employeeId;

	@NotNull(message = "NULL data passed for employeeName")
	@NotBlank(message = "BLANK data passes for employeeName")
	private String employeeName;

	private LocalDate employeeDateOfJoining;

	private LocalDate employeeDateOfBirth;

	@Email
	private String employeeEmailId;

	private BloodGroup employeeBloodGroup;

	private Designation employeeDesignation;

	private Gender employeeGender;

	@NotNull(message = "NULL data passed for nationality")
	@NotBlank(message = "BLANK data passes for nationality")
	private String employeeNationality;

	@Builder.Default
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable
	private Map<EmployeeStatus, String> employeeStatus = new EnumMap<>(EmployeeStatus.class);

	@Embedded
	private EmployeeSecondry employeeSecondry;

	@JoinTable(name = "map_employee_education", joinColumns = @JoinColumn(name = "employee_fk"), inverseJoinColumns = @JoinColumn(name = "education_fk"))
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Education> educations;

	@JoinColumn(name = "bank_fk")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Bank bank;

	@JoinColumn(name = "batch_fk")
	@ManyToOne
	private Batch batch;

	@JoinTable(name = "map_employee_address", joinColumns = @JoinColumn(name = "employee_fk"), inverseJoinColumns = @JoinColumn(name = "address_fk"))
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Address> addresses;

	@JoinTable(name = "map_employee_tech_skill", joinColumns = @JoinColumn(name = "employee_fk"), inverseJoinColumns = @JoinColumn(name = "tech_skill_fk"))
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<TechnicalSkill> technicalSkills;

	@JoinTable(name = "map_employee_experience", joinColumns = @JoinColumn(name = "employee_fk"), inverseJoinColumns = @JoinColumn(name = "experience_fk"))
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Experience> experiences;

	@JoinTable(name = "map_employee_contact", joinColumns = @JoinColumn(name = "employee_fk"), inverseJoinColumns = @JoinColumn(name = "contact_fk"))
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Contact> contacts;

	@JoinTable(name = "map_employee_attendances", joinColumns = @JoinColumn(name = "employee_fk"), inverseJoinColumns = @JoinColumn(name = "attendances_fk"))
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<EmployeeAttendence> employeeAttendences;

	@JoinTable(name = "map_employee_mock_details", joinColumns = @JoinColumn(name = "employee_fk"), inverseJoinColumns = @JoinColumn(name = "mock_details_fk"))
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<MockDetails> mockDetails;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Request request;
}

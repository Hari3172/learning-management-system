package com.ty.lms.entity;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ty.lms.entity.enums.BatchStatus;
import com.ty.lms.entity.enums.BatchStrength;
import com.ty.lms.entity.enums.Performance;
import com.ty.lms.entity.enums.Technology;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
public class Batch {

	@Id
	private String batchId;
	@NotNull(message = "NULL data passed for batchName")
	@NotBlank(message = "BLANK data passes for batchName")
	@Column(unique = true)
	private String batchName;

	private LocalDate batchStartDate;

	private LocalDate batchEndDate;

	@Builder.Default
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable
	private Map<BatchStrength, Integer> batchStrength = new EnumMap<>(BatchStrength.class);

	private BatchStatus batchStatus;

	@Enumerated(EnumType.STRING)
	private Performance batchPerformance;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "batchs")
	private List<Mentor> mentors;

	@JoinTable(name = "map_batch_employee", joinColumns = @JoinColumn(name = "batch_fk"), inverseJoinColumns = @JoinColumn(name = "employee_fk"))
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Employee> employees;

	private Set<Technology> technologies;

}

package com.ty.lms.entity;

import java.time.LocalDate;
import java.util.List;

import com.ty.lms.entity.enums.Technology;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class Mock {
	@Id
	private Integer mockNumber;

	private LocalDate mockDate;

	private Technology technology;

	@JoinColumn(name = "batch_fk")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Batch batch;

	@ManyToMany(mappedBy = "mocks", fetch = FetchType.EAGER)
	private List<Mentor> mentors;

}

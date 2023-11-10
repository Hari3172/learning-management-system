package com.ty.lms.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
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
public class MockDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mock_details_seq")
	@SequenceGenerator(name = "mock_details_seq", sequenceName = "mock_details_seq", allocationSize = 1, initialValue = 1)
	private Integer mockDetailsId;

	private String mockType;

	private Double practicalKnowledge;

	private Double theoriticalKnowledge;

	private Double overallKnowledge;

	private String detailsFeedback;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "mockDetails")
	private List<Employee> employees;

	@JoinColumn(name = "mock_fk")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Mock mock;

}

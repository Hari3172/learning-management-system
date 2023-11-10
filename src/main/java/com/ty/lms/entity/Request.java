package com.ty.lms.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_seq")
	@SequenceGenerator(name = "request_seq", sequenceName = "request_seq", allocationSize = 1, initialValue = 1)
	private Integer requestId;
	private String rejectionReason;
	private Boolean isRejected;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Employee employee;

}

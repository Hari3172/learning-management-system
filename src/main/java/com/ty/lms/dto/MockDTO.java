package com.ty.lms.dto;

import java.time.LocalDate;

import com.ty.lms.entity.enums.Technology;

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
public class MockDTO {

	private Integer mockNumber;
	private LocalDate mockDate;
	private Technology technology;
	private String mentorId;
}

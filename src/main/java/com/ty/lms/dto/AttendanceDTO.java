package com.ty.lms.dto;

import java.time.LocalDate;

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
public class AttendanceDTO {
	private String employeeId;
	private String employeeName;
	private LocalDate attendanceDate;
	private Boolean attendenceMorning;
	private Boolean attendenceNoon;

}
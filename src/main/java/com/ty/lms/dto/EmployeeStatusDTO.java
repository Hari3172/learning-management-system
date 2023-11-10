package com.ty.lms.dto;

import java.util.Map;

import com.ty.lms.entity.enums.EmployeeStatus;

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
public class EmployeeStatusDTO {

	private Map<EmployeeStatus, String> employeeStatus;
}

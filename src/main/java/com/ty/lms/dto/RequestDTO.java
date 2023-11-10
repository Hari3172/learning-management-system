package com.ty.lms.dto;

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
public class RequestDTO {
	private Integer requestId;
	private String rejectionReason;
	private Boolean isRejected;
	private String batchId;
	private String employeeId;
	private EmployeeDTO employee;
}

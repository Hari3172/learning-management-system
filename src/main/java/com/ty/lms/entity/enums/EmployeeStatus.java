package com.ty.lms.entity.enums;

public enum EmployeeStatus {

	ACTIVE("Active"), ABSCONDED("Absconded"), TERMINATED("Terminated");

	private String status;

	EmployeeStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}

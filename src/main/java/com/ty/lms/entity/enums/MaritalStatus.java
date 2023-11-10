package com.ty.lms.entity.enums;

public enum MaritalStatus {

	SIGNIFICANT_OTHER("Significant_other"), MARRIED("Married"), SINGLE("Single"), DIVORCED("Divorced"),

	WIDOWED("Widowed");

	private String status;

	MaritalStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}

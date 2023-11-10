package com.ty.lms.entity.enums;

public enum BatchStatus {

	IN_PROGRESS("IN PROGRESS"), COMPLETED("COMPLETED"), TO_BE_STARTED("TO BE STARTED");

	private String status;

	BatchStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}

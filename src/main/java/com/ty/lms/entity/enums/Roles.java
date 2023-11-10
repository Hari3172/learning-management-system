package com.ty.lms.entity.enums;

public enum Roles {

	ADMIN("ADMIN"), MENTOR("MENTOR"), EMPLOYEE("EMPLOYEE");

	private final String role;

	Roles(String role) {
		this.role = role;
	}

	public String getRole() {
		return this.role;
	}

}

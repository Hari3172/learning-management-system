package com.ty.lms.entity.enums;

public enum Gender {

	MALE("Male"), FEMALE("Female"), OTHER("Other");

	private String gender;

	Gender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

}

package com.ty.lms.entity.enums;

public enum BloodGroup {

	A_POSITIVE("A+"), B_POSITIVE("B+"), O_POSITIVE("O+"), A_NEGETIVE("A-"), B_NEGETIVE("B-"), O_NEGETIVE("O-"),
	AB_POSITIVE("AB+"), AB_NEGETIVE("AB-");

	private String bloodGroup;

	BloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}
}

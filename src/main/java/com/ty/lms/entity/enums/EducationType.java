package com.ty.lms.entity.enums;

public enum EducationType {

	SSC("SSC"), HSC("HSC"), BACHELOR("BECHELOR"), MASTER("MASTER"), PHD("PHD");

	private String educationType;

	EducationType(String educationType) {
		this.educationType = educationType;
	}

	public String getEducationType() {
		return educationType;
	}
}

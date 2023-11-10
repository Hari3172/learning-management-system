package com.ty.lms.entity.enums;

public enum Contacts {
	PHONE_NUMBER("PHONE NUMBER"), MOBILE_NUMBER("MOBILE NUMBER");

	private String number;

	Contacts(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}
}

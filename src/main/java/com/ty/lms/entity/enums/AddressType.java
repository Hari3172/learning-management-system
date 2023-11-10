package com.ty.lms.entity.enums;

public enum AddressType {

	PERMANENT("PERMANENT"), TEMPORARY("TEMPORARY");

	private String addressType;

	AddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAddressType() {
		return addressType;
	}
}

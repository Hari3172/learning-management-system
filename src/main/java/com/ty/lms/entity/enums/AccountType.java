package com.ty.lms.entity.enums;

public enum AccountType {

	SAVING("SAVING ACCOUNT"), CURRENT("CURRENT ACCOUNT"), SALARY("SALARY ACCOUNT"), DEMAT("DEMAT ACCOUNT"),
	FD("FIXED DEPOSIT");

	private String accountType;

	AccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountType() {
		return accountType;
	}
}

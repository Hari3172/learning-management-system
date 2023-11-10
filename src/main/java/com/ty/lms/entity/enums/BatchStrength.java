package com.ty.lms.entity.enums;

public enum BatchStrength {
	INITIAL_STRENGTH("INITIAL STRENGTH"), DROPOUT("DROPOUT"), TERMINATED("TERMINATED"), ABSCONDING("ABSCONDING"),
	PRESENT_STRENGTH("PRESENT STRENGTH");

	private String batchStrength;

	BatchStrength(String batchStrength) {
		this.batchStrength = batchStrength;
	}

	public String getBatchStrength() {
		return batchStrength;
	}
}

package com.ty.lms.entity.enums;

public enum Performance {
	EXCELLENT("EXCELLENT"), GOOD("GOOD"), ABOVE_AVERAGE("ABOVE_AVERAGE"), AVERAGE("AVERAGE"),
	BELOW_AVERAGE("BELOW_AVERAGE");

	private String performance;

	Performance(String performance) {
		this.performance = performance;
	}

	public String getPerformance() {
		return performance;
	}
}

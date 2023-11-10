package com.ty.lms.entity.enums;

public enum Designation {

	/*
	 * LMS_ADMINISTRATOR("LMS_ADMINISTRATOR"), INSTRUCTOR("INSTRUCTOR"),
	 * LEARNER("LEARNER"), COURSE_DESIGNER("COURSE_DESIGNER"),
	 * LEARNING_AND_DEVELOPMENT_MANAGER("LEARNING_AND_DEVELOPMENT_MANAGER"),
	 * CONTENT_CREATOR("CONTENT_CREATOR"),
	 * ASSESSMENT_COORDINATOR("ASSESSMENT_COORDINATOR"),
	 * TECHNICAL_SUPPORT_SPESIALIST("TECHNICAL_SUPPORT_SPESIALIST"),
	 */
	TRAINEE("TRAINEE"), ASSOCIATE_SOFTWARE_ENGINEERS("ASSOCIATE SOFTWARE ENGINEERS"),
	SOFTWARE_ENGINEER("SOFTWARE ENGINEER"), SENIOR_SOFTWARE_ENGINEER("SENIOR SOFTWARE ENGINEER"),
	TECHNOLOGY_ANALYST("TECHNOLOGY ANALYST"), TECHNICAL_LEAD("TECHNICAL LEAD"), MANAGER("MANAGER"),
	ARCHITECT("ARCHITECT"), HR_MANAGER("HR MANAGER"), MARKETING_SPECIALIST("MARKETING SPECIALIST"),
	SALES_REPRESENTATIVE("SALES REPRESENTATIVE"), CUSTOMER_SUPPORT_AGENT("CUSTOMER SUPPORT AGENT"), CEO("CEO"),
	CFO("CFO"), CTO("CTO");

	private String designation;

	Designation(String designation) {
		this.designation = designation;
	}

	public String getDesignation() {
		return designation;
	}
}

package com.ty.lms.entity.enums;

public enum Technology {

	REACT("React"), ANGULAR("Angular"), JAVA_WITH_SPRING_BOOT("Java + Spring Boot"),
	NODE_AND_EXPRESS_JS("Node & Express Js");

	private final String technology;

	Technology(String technology) {
		this.technology = technology;
	}

	public String getTechnology() {
		return technology;
	}
}

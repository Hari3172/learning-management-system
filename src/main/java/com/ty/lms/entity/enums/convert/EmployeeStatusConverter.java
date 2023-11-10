package com.ty.lms.entity.enums.convert;

import java.util.stream.Stream;

import com.ty.lms.entity.enums.EmployeeStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EmployeeStatusConverter implements AttributeConverter<EmployeeStatus, String> {

	@Override
	public String convertToDatabaseColumn(EmployeeStatus employeeStatus) {
		if (employeeStatus == null)
			return null;
		return employeeStatus.getStatus();
	}

	@Override
	public EmployeeStatus convertToEntityAttribute(String dbData) {
		if (dbData == null)
			return null;
		try {
			return Stream.of(EmployeeStatus.values()).filter(es -> es.getStatus().equals(dbData)).findFirst()
					.orElseThrow(IllegalAccessException::new);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}

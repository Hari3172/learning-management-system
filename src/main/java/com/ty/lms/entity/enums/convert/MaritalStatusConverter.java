package com.ty.lms.entity.enums.convert;

import java.util.stream.Stream;

import com.ty.lms.entity.enums.MaritalStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MaritalStatusConverter implements AttributeConverter<MaritalStatus, String> {

	@Override
	public String convertToDatabaseColumn(MaritalStatus maritalStatus) {
		if (maritalStatus == null)
			return null;
		return maritalStatus.getStatus();
	}

	@Override
	public MaritalStatus convertToEntityAttribute(String dbData) {
		if (dbData == null)
			return null;
		try {
			return Stream.of(MaritalStatus.values()).filter(ms -> ms.getStatus().equals(dbData)).findFirst()
					.orElseThrow(IllegalAccessException::new);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}

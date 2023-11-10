package com.ty.lms.entity.enums.convert;

import java.util.stream.Stream;

import com.ty.lms.entity.enums.EducationType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EducationTypeConverter implements AttributeConverter<EducationType, String> {

	@Override
	public String convertToDatabaseColumn(EducationType educationType) {
		if (educationType == null)
			return null;
		return educationType.getEducationType();
	}

	@Override
	public EducationType convertToEntityAttribute(String dbData) {
		if (dbData == null)
			return null;
		try {
			return Stream.of(EducationType.values()).filter(e -> e.getEducationType().equals(dbData)).findFirst()
					.orElseThrow(IllegalAccessException::new);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}

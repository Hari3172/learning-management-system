package com.ty.lms.entity.enums.convert;

import java.util.stream.Stream;

import com.ty.lms.entity.enums.Gender;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

	@Override
	public String convertToDatabaseColumn(Gender gender) {
		if (gender == null)
			return null;
		return gender.getGender();
	}

	@Override
	public Gender convertToEntityAttribute(String dbData) {
		if (dbData == null)
			return null;
		try {
			return Stream.of(Gender.values()).filter(c -> c.getGender().equals(dbData)).findFirst()
					.orElseThrow(IllegalAccessException::new);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}

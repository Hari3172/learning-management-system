package com.ty.lms.entity.enums.convert;

import java.util.stream.Stream;

import com.ty.lms.entity.enums.BloodGroup;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
@Converter(autoApply = true)
public class BloodGroupConverter implements AttributeConverter<BloodGroup, String> {

	@Override
	public String convertToDatabaseColumn(BloodGroup bloodGroup) {
		if (bloodGroup == null)
			return null;
		return bloodGroup.getBloodGroup();
	}

	@Override
	public BloodGroup convertToEntityAttribute(String dbData) {
		if (dbData == null)
			return null;
		try {
			return Stream.of(BloodGroup.values()).filter(bg -> bg.getBloodGroup().equals(dbData)).findFirst()
					.orElseThrow(IllegalAccessException::new);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}

package com.ty.lms.entity.enums.convert;

import java.util.stream.Stream;

import com.ty.lms.entity.enums.Technology;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TechnologyConverter implements AttributeConverter<Technology, String> {

	@Override
	public String convertToDatabaseColumn(Technology technology) {
		if (technology == null)
			return null;
		return technology.getTechnology();
	}

	@Override
	public Technology convertToEntityAttribute(String dbData) {
		if (dbData == null)
			return null;
		try {
			return Stream.of(Technology.values()).filter(tech -> tech.getTechnology().equals(dbData)).findFirst()
					.orElseThrow(IllegalAccessException::new);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}

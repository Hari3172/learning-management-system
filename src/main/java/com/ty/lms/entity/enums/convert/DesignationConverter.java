package com.ty.lms.entity.enums.convert;

import java.util.stream.Stream;

import com.ty.lms.entity.enums.Designation;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DesignationConverter implements AttributeConverter<Designation, String> {

	@Override
	public String convertToDatabaseColumn(Designation designation) {
		if (designation == null)
			return null;
		return designation.getDesignation();
	}

	@Override
	public Designation convertToEntityAttribute(String dbData) {
		if (dbData == null)
			return null;
		try {
			return Stream.of(Designation.values()).filter(d -> d.getDesignation().equals(dbData)).findFirst()
					.orElseThrow(IllegalAccessException::new);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}

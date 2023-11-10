package com.ty.lms.entity.enums.convert;

import java.util.stream.Stream;

import com.ty.lms.entity.enums.BatchStrength;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
@Converter(autoApply = true)
public class BatchStremgthConverter implements AttributeConverter<BatchStrength, String> {

	@Override
	public String convertToDatabaseColumn(BatchStrength batchStrength) {
		if (batchStrength == null)
			return null;
		return batchStrength.getBatchStrength();
	}

	@Override
	public BatchStrength convertToEntityAttribute(String dbData) {
		if (dbData == null)
			return null;
		try {
			return Stream.of(BatchStrength.values()).filter(bs -> bs.getBatchStrength().equals(dbData)).findFirst()
					.orElseThrow(IllegalAccessException::new);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}

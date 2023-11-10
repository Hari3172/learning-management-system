package com.ty.lms.entity.enums.convert;

import java.util.stream.Stream;

import com.ty.lms.entity.enums.BatchStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BatchStatusConverter implements AttributeConverter<BatchStatus, String> {

	@Override
	public String convertToDatabaseColumn(BatchStatus batchStatus) {
		if (batchStatus == null)
			return null;
		return batchStatus.getStatus();
	}

	@Override
	public BatchStatus convertToEntityAttribute(String dbData) {
		if (dbData == null)
			return null;
		try {
			return Stream.of(BatchStatus.values()).filter(es -> es.getStatus().equals(dbData)).findFirst()
					.orElseThrow(IllegalAccessException::new);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}

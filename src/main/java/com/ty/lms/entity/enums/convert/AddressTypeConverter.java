package com.ty.lms.entity.enums.convert;

import java.util.stream.Stream;

import com.ty.lms.entity.enums.AddressType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AddressTypeConverter implements AttributeConverter<AddressType, String> {

	@Override
	public String convertToDatabaseColumn(AddressType addressType) {
		if (addressType == null)
			return null;
		return addressType.getAddressType();
	}

	@Override
	public AddressType convertToEntityAttribute(String dbData) {
		if (dbData == null)
			return null;
		try {
			return Stream.of(AddressType.values()).filter(at -> at.getAddressType().equals(dbData)).findFirst()
					.orElseThrow(IllegalAccessException::new);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}

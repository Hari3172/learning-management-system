package com.ty.lms.entity.enums.convert;

import java.util.stream.Stream;

import com.ty.lms.entity.enums.Contacts;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ContactsConverter implements AttributeConverter<Contacts, String> {

	@Override
	public String convertToDatabaseColumn(Contacts contacts) {
		if (contacts == null)
			return null;
		return contacts.getNumber();
	}

	@Override
	public Contacts convertToEntityAttribute(String dbData) {
		if (dbData == null)
			return null;
		try {
			return Stream.of(Contacts.values()).filter(c -> c.getNumber().equals(dbData)).findFirst()
					.orElseThrow(IllegalAccessException::new);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}

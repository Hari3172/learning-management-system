//package com.ty.lms.entity.enums.convert;
//
//import java.util.stream.Stream;
//
//import com.ty.lms.entity.enums.Roles;
//
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//
//@Converter(autoApply = true)
//public class RolesConverter implements AttributeConverter<Roles, String> {
//
//	@Override
//	public String convertToDatabaseColumn(Roles roles) {
//		if (roles == null)
//			return null;
//		return roles.getRole();
//	}
//
//	@Override
//	public Roles convertToEntityAttribute(String dbData) {
//		if (dbData == null)
//			return null;
//		try {
//			return Stream.of(Roles.values()).filter(role -> role.getRole().equals(dbData)).findFirst()
//					.orElseThrow(IllegalAccessException::new);
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//}

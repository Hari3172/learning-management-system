package com.ty.lms.util;

import org.springframework.stereotype.Component;

import com.ty.lms.dto.AdminRegisterDTO;
import com.ty.lms.entity.Admin;

import jakarta.validation.Valid;

@Component
public class AdminUtils {

	public Admin dtoToEntity(@Valid AdminRegisterDTO adminRegisterDTO) {
		return Admin.builder().adminName(adminRegisterDTO.getAdminName()).build();
	}

}

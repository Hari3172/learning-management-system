package com.ty.lms.util;

import org.springframework.beans.BeanUtils;

import com.ty.lms.dto.MockDTO;
import com.ty.lms.entity.Mock;

public class MockUtils {

	public static Mock dtoToEntity(MockDTO mockDTO) {
		Mock mock = Mock.builder().build();
		BeanUtils.copyProperties(mockDTO, mock);
		return mock;
	}
}

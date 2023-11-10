package com.ty.lms.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ty.lms.dto.RequestDTO;
import com.ty.lms.entity.Request;

@Component
public class RequestUtils {

	public Request dtoToEntity(RequestDTO requestDTO) {
		Request request = Request.builder().build();
		BeanUtils.copyProperties(requestDTO, request);
		request.setIsRejected(null);
		return request;
	}
}

package com.ty.lms.service;

import java.util.List;

import com.ty.lms.dto.RequestDTO;

import jakarta.validation.Valid;

public interface RequestService {

	List<RequestDTO> getAllRequest();

	Boolean requestApprove(@Valid RequestDTO requestDTO);

}

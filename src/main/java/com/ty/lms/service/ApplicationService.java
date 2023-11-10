package com.ty.lms.service;

import com.ty.lms.dto.EmployeeRegisterDTO;
import com.ty.lms.dto.MentorRegisterDTO;

import jakarta.validation.Valid;

public interface ApplicationService {

	String registerEmployee(@Valid EmployeeRegisterDTO employeeRegisterDTO);

	String registerMentor(@Valid MentorRegisterDTO mentorRegisterDTO);

}

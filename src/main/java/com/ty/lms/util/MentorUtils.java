package com.ty.lms.util;

import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ty.lms.dto.MentorDTO;
import com.ty.lms.dto.MentorRegisterDTO;
import com.ty.lms.entity.Mentor;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MentorUtils {

	private final EmployeeUtils employeeUtils;

	public Mentor dtoToEntity(@Valid MentorRegisterDTO mentorRegisterDTO) {
		Mentor mentor = Mentor.builder().build();
		BeanUtils.copyProperties(mentorRegisterDTO, mentor);
		mentor.setTechnicalSkills(mentorRegisterDTO.getTechnicalSkills().stream().map(employeeUtils::dtoToEntity)
				.collect(Collectors.toSet()));
		return mentor;
	}

	public MentorDTO entityToDto(Mentor mentor) {
		MentorDTO mentorDTO = MentorDTO.builder().build();
		BeanUtils.copyProperties(mentor, mentorDTO);
		mentorDTO.setTechnicalSkills(
				mentor.getTechnicalSkills().stream().map(employeeUtils::entityToDto).collect(Collectors.toSet()));
		return mentorDTO;
	}

}

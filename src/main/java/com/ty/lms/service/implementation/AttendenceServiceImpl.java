package com.ty.lms.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ty.lms.dto.AttendanceDTO;
import com.ty.lms.repository.AttendenceRepository;
import com.ty.lms.service.AttendenceService;
import com.ty.lms.util.AttendenceUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AttendenceServiceImpl implements AttendenceService {

	private final AttendenceRepository attendenceRepository;

	@Override
	public List<AttendanceDTO> getAttendenceByDate(LocalDate localDate) {
		log.info("Retrieving attendance for date: {}", localDate);
		List<AttendanceDTO> attendanceDTOs = attendenceRepository.findAllByAttendanceDate(localDate).stream()
				.map(AttendenceUtils::entityToDto).collect(Collectors.toList());
		log.info("Retrieved attendance for date: {}", localDate);
		return attendanceDTOs;
	}

}

package com.ty.lms.service;

import java.time.LocalDate;
import java.util.List;

import com.ty.lms.dto.AttendanceDTO;

public interface AttendenceService {

	List<AttendanceDTO> getAttendenceByDate(LocalDate localDate);

}

package com.ty.lms.util;

import org.springframework.beans.BeanUtils;

import com.ty.lms.dto.AttendanceDTO;
import com.ty.lms.entity.EmployeeAttendence;

public class AttendenceUtils {

	public static AttendanceDTO entityToDto(EmployeeAttendence attendence) {
		AttendanceDTO attendanceDTO = AttendanceDTO.builder().build();
		BeanUtils.copyProperties(attendence, attendanceDTO);
		attendence.getEmployees().stream().forEach(e -> {
			attendanceDTO.setEmployeeId(e.getEmployeeId());
			attendanceDTO.setEmployeeName(e.getEmployeeName());
		});
		return attendanceDTO;
	}
}

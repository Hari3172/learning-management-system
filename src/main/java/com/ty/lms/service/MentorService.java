package com.ty.lms.service;

import java.util.List;

import com.ty.lms.dto.MentorDTO;

public interface MentorService {

	List<MentorDTO> getAllMentors();

	Boolean update(String mentorId, MentorDTO mentorDTO);

	MentorDTO getMentor(String mentorId);

	Boolean remove(String mentorId);

}

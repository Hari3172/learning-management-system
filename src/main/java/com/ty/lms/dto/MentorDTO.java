package com.ty.lms.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MentorDTO {
	private String mentorId;
	private String mentorName;
	private String mentorEmailId;
	private Set<TechnicalSkillDTO> technicalSkills;
}

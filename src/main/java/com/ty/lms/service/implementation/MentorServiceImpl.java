package com.ty.lms.service.implementation;

import static com.ty.lms.constant.MentorConstants.MENTOR_NOT_FOUND;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.ty.lms.dto.MentorDTO;
import com.ty.lms.entity.Mentor;
import com.ty.lms.entity.TechnicalSkill;
import com.ty.lms.exception.MentorNotFoundException;
import com.ty.lms.repository.MentorRepository;
import com.ty.lms.repository.TechnicalSkillRepository;
import com.ty.lms.service.MentorService;
import com.ty.lms.util.MentorUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MentorServiceImpl implements MentorService {

	private final MentorRepository mentorRepository;
	private final TechnicalSkillRepository technicalSkillRepository;
	private final MentorUtils mentorUtils;

	@Override
	public List<MentorDTO> getAllMentors() {
		log.info("Getting all mentors");
		List<MentorDTO> mentorDTOs = mentorRepository.findAll().stream().map(mentorUtils::entityToDto)
				.collect(Collectors.toList());
		log.debug("Retrieved {} mentors", mentorDTOs.size());
		return mentorDTOs;
	}

	@Override
	public Boolean update(String mentorId, MentorDTO mentorDTO) {
		log.info("Updating mentor with ID: {}", mentorId);
		Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(() -> {
			log.error("Mentor not found: {}", MENTOR_NOT_FOUND);
			throw new MentorNotFoundException(MENTOR_NOT_FOUND);
		});
		log.debug("Mentor found: {}", mentor);
		BeanUtils.copyProperties(mentorDTO, mentor);
		Iterator<TechnicalSkill> iterator = mentor.getTechnicalSkills().iterator();
		Set<TechnicalSkill> technicalSkills_ = Sets.newHashSet();
		while (iterator.hasNext()) {
			TechnicalSkill technicalSkill = iterator.next();
			Optional<TechnicalSkill> skillDB = technicalSkillRepository.findById(technicalSkill.getTechnicalSkillId());
			if (skillDB.isPresent()) {
				technicalSkills_.add(skillDB.get());
			} else {
				technicalSkills_.add(technicalSkill);
			}
		}
		mentor.setTechnicalSkills(technicalSkills_);
		log.info("Mentor with ID {} updated successfully", mentorId);
		return true;
	}

	@Override
	public MentorDTO getMentor(String mentorId) {
		log.info("Getting mentor with ID: {}", mentorId);
		MentorDTO mentorDTO = mentorUtils.entityToDto(mentorRepository.findById(mentorId).orElseThrow(() -> {
			log.error("Mentor not found with ID: {}", mentorId);
			throw new MentorNotFoundException(MENTOR_NOT_FOUND);
		}));
		log.info("Retrieved mentor with ID: {}", mentorId);
		return mentorDTO;
	}

	@Override
	public Boolean remove(String mentorId) {
		log.info("Removing mentor with ID: {}", mentorId);
		Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(() -> {
			log.error("Mentor not found with ID: {}", mentorId);
			throw new MentorNotFoundException(MENTOR_NOT_FOUND);
		});
		mentor.setBatchs(null);
		mentor.setMocks(null);
		mentor.setTechnicalSkills(null);
		mentorRepository.delete(mentor);
		log.info("Mentor with ID {} removed successfully", mentorId);
		return true;
	}

}

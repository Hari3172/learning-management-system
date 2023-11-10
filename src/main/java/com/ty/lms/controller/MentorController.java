package com.ty.lms.controller;

import static com.ty.lms.constant.MentorConstants.MENTOR_ALREADY_EXIST;
import static com.ty.lms.constant.MentorConstants.MENTOR_DATA_PROVIDED;
import static com.ty.lms.constant.MentorConstants.MENTOR_DATA_UPDATED;
import static com.ty.lms.constant.MentorConstants.MENTOR_DELETED;
import static com.ty.lms.constant.MentorConstants.MENTOR_REGISTERED;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.lms.dto.MentorDTO;
import com.ty.lms.dto.MentorRegisterDTO;
import com.ty.lms.exception.EmployeeAlreadyExistException;
import com.ty.lms.response.ApiResponse;
import com.ty.lms.service.ApplicationService;
import com.ty.lms.service.MentorService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "javainuseapi")
@RequestMapping(path = "/api/v1/mentors")
public class MentorController {

	private final MentorService mentorService;
	private final ApplicationService applicationService;

	@PostMapping(path = "/register")
	public ResponseEntity<ApiResponse> registerEmployee(@RequestBody @Valid MentorRegisterDTO mentorRegisterDTO) {
		log.info("POST request received for /api/v1/mentors/register with data: {}", mentorRegisterDTO);
		String mentor = applicationService.registerMentor(mentorRegisterDTO);
		if (mentor != null) {
			log.info("Mentor registration successful: {}", mentor);
			return new ResponseEntity<ApiResponse>(ApiResponse.builder().data(mentor).message(MENTOR_REGISTERED)
					.timestamp(LocalDateTime.now()).build(), HttpStatus.CREATED);
		}
		log.error("Mentor registration failed. Mentor already exists", MENTOR_ALREADY_EXIST);
		throw new EmployeeAlreadyExistException(MENTOR_ALREADY_EXIST);
	}

	@GetMapping(path = "/")
	public ResponseEntity<ApiResponse> getMentors() {
		log.info("GET request received for /api/v1/mentors/");
		return new ResponseEntity<ApiResponse>(ApiResponse.builder().data(mentorService.getAllMentors())
				.message(MENTOR_DATA_PROVIDED).timestamp(LocalDateTime.now()).build(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "/{mentor-id}")
	public ResponseEntity<ApiResponse> getMentor(@PathVariable(name = "mentor-id") String mentorId) {
		log.info("GET request received for /api/v1/mentors/mentor-id with mentor ID: {}", mentorId);
		return ResponseEntity.<ApiResponse>ok(ApiResponse.builder().data(mentorService.getMentor(mentorId))
				.message(MENTOR_DATA_PROVIDED).timestamp(LocalDateTime.now()).build());
	}

	@DeleteMapping(path = "/{mentor-id}")
	public ResponseEntity<ApiResponse> removeMentor(@PathVariable(name = "mentor-id") String mentorId) {
		log.info("DELETE request received for /api/v1/mentors/mentor-id with mentor ID: {}", mentorId);
		return ResponseEntity.<ApiResponse>ok(ApiResponse.builder().data(mentorService.remove(mentorId))
				.message(MENTOR_DELETED).timestamp(LocalDateTime.now()).build());
	}

	@PutMapping(path = "/")
	public ResponseEntity<ApiResponse> update(@PathVariable(name = "mentor-id") String mentorId,
			@RequestBody MentorDTO mentorDTO) {
		log.info("PUT request received for /api/v1/mentors/ with mentor ID: {} and data", mentorId, mentorDTO);
		return ResponseEntity.<ApiResponse>ok(ApiResponse.builder().data(mentorService.update(mentorId, mentorDTO))
				.message(MENTOR_DATA_UPDATED).timestamp(LocalDateTime.now()).build());
	}

}

package com.ty.lms.controller;

import static com.ty.lms.constant.AdminConstants.REQUEST_NOT_UPDATED;
import static com.ty.lms.constant.EmployeeConstants.EMPLOYEE_ALREADY_EXIST;
import static com.ty.lms.constant.EmployeeConstants.EMPLOYEE_REGISTERED;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.lms.dto.EmployeeRegisterDTO;
import com.ty.lms.dto.LoginDTO;
import com.ty.lms.exception.EmployeeAlreadyExistException;
import com.ty.lms.exception.RequestNotApprovedException;
import com.ty.lms.response.ApiResponse;
import com.ty.lms.service.AppUserService;
import com.ty.lms.service.ApplicationService;
import com.ty.lms.util.JwtsUtils;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "javainuseapi")
@RequestMapping(path = "/api/v1/users")
public class LMSApplicationController {

	private final AppUserService appUserService;
	private final ApplicationService applicationService;
	private final AuthenticationManager authenticationManager;
	private final JwtsUtils jwtsUtils;

	@PostMapping(path = "/")
	public ResponseEntity<ApiResponse> registerEmployee(@RequestBody @Valid EmployeeRegisterDTO employeeRegisterDTO) {
		log.info("POST request received for /api/v1/users/ with data: {}", employeeRegisterDTO);
		String employeeId = applicationService.registerEmployee(employeeRegisterDTO);
		if (employeeId != null) {
			log.info("Employee registered successfully with ID: {}", employeeId);
			return new ResponseEntity<ApiResponse>(ApiResponse.builder().data(employeeId).message(EMPLOYEE_REGISTERED)
					.timestamp(LocalDateTime.now()).build(), HttpStatus.CREATED);
		}
		log.error("Failed to register employee. Employee already exists.");
		throw new EmployeeAlreadyExistException(EMPLOYEE_ALREADY_EXIST);
	}

	@PostMapping(path = "/login")
	public ResponseEntity<ApiResponse> login(@RequestBody @Valid LoginDTO loginDTO) {
		log.info("POST request received for /api/v1/users/login with data: {}", loginDTO);
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
		if (appUserService.findUser(loginDTO.getUsername()).getRoles().stream()
				.filter(r -> r.getRoleName().equals("ROLE_EMPLOYEE")).findFirst().isPresent()) {
			if (appUserService.isUserApproved(loginDTO.getUsername())) {
				log.info("User logged in successfully: {}", loginDTO.getUsername());
				return new ResponseEntity<ApiResponse>(ApiResponse.builder()
						.token(jwtsUtils.generateToken(loginDTO.getUsername())).timestamp(LocalDateTime.now()).build(),
						HttpStatus.ACCEPTED);
			}
			log.error("User login failed. Request not approved for user: {}", loginDTO.getUsername(),
					REQUEST_NOT_UPDATED);
			throw new RequestNotApprovedException(REQUEST_NOT_UPDATED);
		}
		log.info("User with username: {} logged in successfully", loginDTO.getUsername());
		return new ResponseEntity<ApiResponse>(ApiResponse.builder()
				.token(jwtsUtils.generateToken(loginDTO.getUsername())).timestamp(LocalDateTime.now()).build(),
				HttpStatus.ACCEPTED);
	}

}

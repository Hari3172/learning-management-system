package com.ty.lms.service.implementation;

import static com.ty.lms.constant.AppUserConstants.USERNAME_NOT_FOUND;
import static com.ty.lms.constant.EmployeeConstants.EMPLOYEE_NOT_FOUND;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ty.lms.entity.AppUser;
import com.ty.lms.exception.EmployeeNotFoundException;
import com.ty.lms.repository.AppUserRepository;
import com.ty.lms.repository.EmployeeRepository;
import com.ty.lms.service.AppUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService {

	private final AppUserRepository appUserRepository;
	private final EmployeeRepository employeeRepository;

	@Override
	public Boolean isUserApproved(String username) {
		log.info("Checking if user is approved: {}", username);
		boolean isApproved = employeeRepository
				.findByEmployeeEmailId(appUserRepository.findById(username).orElseThrow(() -> {
					log.error("Error checking user approval status. Username not found: {}", username,
							USERNAME_NOT_FOUND);
					throw new UsernameNotFoundException("User with username " + username + " doesn't exists!!");
				}).getUsername()).orElseThrow(() -> {
					log.error("Error checking user approval status. Employee not found for username: {}", username,
							EMPLOYEE_NOT_FOUND);
					throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND);
				}).getRequest().getIsRejected() != null;
		log.info("User approval status for {}: {}", username, isApproved);
		return isApproved;
	}

	@Override
	public AppUser findUser(String username) {
		log.info("Finding user: {}", username);
		AppUser appUser = appUserRepository.findById(username).orElseThrow(() -> {
			log.error("Error finding user. User not found: {}", username,
					"User with username " + username + " doesn't exist!!");
			throw new UsernameNotFoundException("User with username " + username + " doesn't exist!!");
		});
		log.info("Found user: {}", username);
		return appUser;
	}

}

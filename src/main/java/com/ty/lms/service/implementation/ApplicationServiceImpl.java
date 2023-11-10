package com.ty.lms.service.implementation;

import static com.ty.lms.constant.RoleConstants.ROLE_NOT_FOUND;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.ty.lms.dto.EmployeeRegisterDTO;
import com.ty.lms.dto.MentorRegisterDTO;
import com.ty.lms.entity.AppUser;
import com.ty.lms.entity.Employee;
import com.ty.lms.entity.Mentor;
import com.ty.lms.entity.Request;
import com.ty.lms.entity.Role;
import com.ty.lms.exception.RoleNotFoundException;
import com.ty.lms.repository.AppUserRepository;
import com.ty.lms.repository.EmployeeRepository;
import com.ty.lms.repository.MentorRepository;
import com.ty.lms.repository.RequestRepository;
import com.ty.lms.repository.RoleRepository;
import com.ty.lms.service.ApplicationService;
import com.ty.lms.util.EmployeeUtils;
import com.ty.lms.util.MentorUtils;
import com.ty.lms.util.RequestUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ApplicationServiceImpl implements ApplicationService {

	private final PasswordEncoder passwordEncoder;
	private final EmployeeUtils employeeUtils;
	private final MentorUtils mentorUtils;
	private final RequestUtils requestUtils;
	private final AppUserRepository appUserRepository;
	private final RoleRepository roleRepository;
	private final EmployeeRepository employeeRepository;
	private final MentorRepository mentorRepository;
	private final RequestRepository requestRepository;

	@Override
	public String registerEmployee(EmployeeRegisterDTO employeeRegisterDTO) {

		log.info("Registering an employee: " + employeeRegisterDTO.getEmployeeEmailId());
		Role role = roleRepository.findById("ROLE_EMPLOYEE")
				.orElseThrow(() -> new RoleNotFoundException(ROLE_NOT_FOUND));

		Optional<AppUser> userOP = appUserRepository.findById(employeeRegisterDTO.getEmployeeEmailId());

		if (userOP.isEmpty()) {
			AppUser user = AppUser.builder().username(employeeRegisterDTO.getEmployeeEmailId())
					.password(passwordEncoder.encode(employeeRegisterDTO.getPassword())).roles(Sets.newHashSet())
					.build();

			role.getAppUsers().add(user);
			user.getRoles().add(role);

			Employee employee = employeeUtils.dtoToEntity(employeeRegisterDTO);
			employee.setEmployeeStatus(employeeRegisterDTO.getEmployeeStatus());
			Employee employee_ = employeeRepository.save(employee);

			appUserRepository.save(user);

			Request request = requestUtils.dtoToEntity(employeeRegisterDTO.getRequest());
			request.setEmployee(employee_);
			employee_.setRequest(request);
			requestRepository.save(request);
			return employee_.getEmployeeId();
		}
		log.error("An error occurred while registering an employee.");
		return null;
	}

	@Override
	public String registerMentor(MentorRegisterDTO mentorRegisterDTO) {
		log.info("Registering a mentor: " + mentorRegisterDTO.getMentorEmailId());
		Role role = roleRepository.findById("ROLE_MENTOR").orElseThrow(() -> new RoleNotFoundException(ROLE_NOT_FOUND));
		Optional<AppUser> userOP = appUserRepository.findById(mentorRegisterDTO.getMentorEmailId());
		if (userOP.isEmpty()) {
			AppUser user = AppUser.builder().username(mentorRegisterDTO.getMentorEmailId())
					.password(passwordEncoder.encode(mentorRegisterDTO.getPassword())).roles(Sets.newHashSet()).build();
			role.getAppUsers().add(user);
			user.getRoles().add(role);
			Mentor mentor = mentorRepository.save(mentorUtils.dtoToEntity(mentorRegisterDTO));
			appUserRepository.save(user);
			return mentor.getMentorId();
		}
		log.error("An error occurred while registering a mentor.");
		return null;
	}

}
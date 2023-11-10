package com.ty.lms.service.implementation;

import static com.ty.lms.constant.AppUserConstants.USERNAME_NOT_FOUND;
import static com.ty.lms.constant.EmployeeConstants.EMPLOYEE_NOT_FOUND;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.ty.lms.dto.AddressDTO;
import com.ty.lms.dto.AttendanceDTO;
import com.ty.lms.dto.ContactDTO;
import com.ty.lms.dto.EmployeeDTO;
import com.ty.lms.dto.EmployeeRegisterDTO;
import com.ty.lms.dto.EmployeeStatusDTO;
import com.ty.lms.dto.MockDetailsDTO;
import com.ty.lms.dto.PasswordResetDTO;
import com.ty.lms.entity.Address;
import com.ty.lms.entity.AppUser;
import com.ty.lms.entity.Bank;
import com.ty.lms.entity.Contact;
import com.ty.lms.entity.Employee;
import com.ty.lms.entity.MockDetails;
import com.ty.lms.exception.EmployeeNotFoundException;
import com.ty.lms.repository.AppUserRepository;
import com.ty.lms.repository.BankRepository;
import com.ty.lms.repository.EmployeeRepository;
import com.ty.lms.repository.MockDetailsRepository;
import com.ty.lms.service.EmployeeService;
import com.ty.lms.util.EmployeeUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeUtils employeeUtils;
	private final EmployeeRepository employeeRepository;
	private final MockDetailsRepository mockDetailsRepository;
	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;
	private final BankRepository bankRepository;

	@Override
	public EmployeeDTO getEmployeeById(String employeeId) {
		log.info("Retrieving employee by ID: {}", employeeId);
		EmployeeDTO employeeDTO = employeeUtils.entityToDto(employeeRepository.findById(employeeId).orElseThrow(() -> {
			log.error("Employee not found with ID: {}", employeeId);
			return new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND);
		}));
		log.info("Retrieved employee with ID: {}", employeeId);
		return employeeDTO;
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		log.info("Retrieving all employees");
		List<EmployeeDTO> employeeDTOs = employeeRepository.findAll().stream().filter(emp -> {
			Boolean isRejected = emp.getRequest().getIsRejected();
			if (isRejected != null && !isRejected) {
				return true;
			}
			return false;
		}).map(emp -> employeeUtils.entityToDto(emp)).collect(Collectors.toList());
		log.info("Retrieved {} employees", employeeDTOs.size());
		return employeeDTOs;
	}

	@Override
	public Boolean updateEmployee(String employeeId, @Valid EmployeeRegisterDTO employeeUpdateDTO) {
		log.info("Updating employee with ID: {}", employeeId);
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND));
		/*
		 * EmployeeSecondry Information Update
		 * 
		 */
		log.info("Updating employee secondary information");
		BeanUtils.copyProperties(employeeUpdateDTO.getEmployeeSecondry(), employee.getEmployeeSecondry());
		/*
		 * Address Update
		 * 
		 */
		log.info("Updating employee addresses");
		employee.getAddresses()
				.addAll(EmployeeServiceImpl.updateAddress(employee.getAddresses(), employeeUpdateDTO.getAddresses())
						.stream().map(employeeUtils::dtoToEntity).collect(Collectors.toList()));
		log.info("Filtering and updating employee educations");
		employee.setEducations(employee.getEducations().stream()
				.filter(edu -> !employeeUpdateDTO.getEducations().contains(edu)).collect(Collectors.toSet()));
		/*
		 * Bank Update
		 * 
		 */
		log.info("Updating employee bank information");
		Long accountNumber = employee.getBank().getAccountNumber();
		employee.setBank(null);
		bankRepository.deleteById(accountNumber);
		Bank bank = Bank.builder().build();
		BeanUtils.copyProperties(employeeUpdateDTO.getBank(), bank);
		employee.setBank(bank);

		log.info("Filtering and updating employee technical skills");
		employee.setTechnicalSkills(employee.getTechnicalSkills().stream()
				.filter(tech -> !employeeUpdateDTO.getTechnicalSkill().contains(tech)).collect(Collectors.toSet()));

		log.info("Filtering and updating employee experiences");
		employee.setExperiences(employee.getExperiences().stream()
				.filter(ex -> !employeeUpdateDTO.getExperiences().contains(ex)).collect(Collectors.toSet()));

		log.info("Updating employee contacts");
		employee.getContacts()
				.addAll(EmployeeServiceImpl.updateContact(employee.getContacts(), employeeUpdateDTO.getContacts())
						.stream().map(employeeUtils::dtoToEntity).collect(Collectors.toList()));

		log.info("Saving updated employee information");
		employeeRepository.save(employee);
		log.info("Employee with ID {} has been updated", employeeId);

		return true;
	}

	public static List<AddressDTO> updateAddress(List<Address> addresses, List<AddressDTO> addressDTOs) {
		/*
		 * Using Stream API:-
		 * 
		 * List<AddressDTO> adDTO = addresses2.stream() .filter(dto2 ->
		 * addresses.stream() .noneMatch(address -> address.getDoorNumber() ==
		 * dto2.getDoorNumber())) .collect(Collectors.toList());
		 *
		 */
		log.info("Updating addresses");
		List<AddressDTO> addressDTOs_ = Lists.newArrayList();
		boolean flag = true;
		for (int i = 0; i < addressDTOs.size(); i++) {
			for (int j = 0; j < addresses.size(); j++) {
				if (addressDTOs.get(i).getDoorNumber() == addresses.get(j).getDoorNumber())
					flag = false;
			}
			if (flag)
				addressDTOs_.add(addressDTOs.get(i));
		}
		log.info("Updated addresses: {}", addressDTOs_);
		return addressDTOs_;
	}

	public static List<ContactDTO> updateContact(List<Contact> contacts, List<ContactDTO> contactDTOs) {
		log.info("Updating contacts");
		List<ContactDTO> contactDTOs_ = Lists.newArrayList();
		boolean flag = true;
		for (int i = 0; i < contactDTOs.size(); i++) {
			for (int j = 0; j < contacts.size(); j++) {
				if (contactDTOs.get(i).getContactNumber() == contacts.get(j).getContactNumber())
					flag = false;
			}
			if (flag)
				contactDTOs_.add(contactDTOs.get(i));
		}
		log.info("Updated contacts: {}", contactDTOs_);
		return contactDTOs_;
	}

	@Override
	public Boolean addAttendance(String employeeId, @Valid AttendanceDTO attendanceDTO) {
		log.info("Adding attendance for employee: {}", employeeId);
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> {
			log.error("Failed to add attendance for employee: {}. Employee not found.", employeeId, EMPLOYEE_NOT_FOUND);
			throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND);
		});

		boolean noneMatch = employee.getEmployeeAttendences().stream().noneMatch(
				attendence -> attendence.getAttendanceDate().compareTo(attendanceDTO.getAttendanceDate()) >= 0);

		if (noneMatch) {
			employee.getEmployeeAttendences().add(employeeUtils.dtoToEntity(attendanceDTO));
			employeeRepository.save(employee);
			log.info("Attendance added successfully for employee: {}", employeeId);
			return true;
		}
		log.warn("Attendance already exists for employee: {}", employeeId);
		return false;
	}

	@Override
	public List<AttendanceDTO> getEmployeeAttendanceByEmployeeId(String employeeId) {
		log.info("Retrieving attendance for employee: {}", employeeId);
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> {
			log.error("Failed to retrieve attendance for employee: {}. Employee not found.", employeeId,
					EMPLOYEE_NOT_FOUND);
			throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND);
		});
		List<AttendanceDTO> attendanceDTOs = employee.getEmployeeAttendences().stream().map(employeeUtils::entityToDTO)
				.collect(Collectors.toList());
		attendanceDTOs.forEach(aa -> aa.setEmployeeId(employeeId));
		log.info("Attendance retrieved successfully for employee: {}", employeeId);
		return attendanceDTOs;
	}

	@Override
	public List<MockDetailsDTO> getEmployeeMockRatingById(String employeeId) {
		log.info("Retrieving mock ratings for employee: {}", employeeId);
		List<MockDetailsDTO> mockDetailsDTOs = employeeRepository.findById(employeeId).orElseThrow(() -> {
			log.error("Failed to retrieve mock ratings for employee: {}. Employee not found.", employeeId,
					EMPLOYEE_NOT_FOUND);
			throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND);
		}).getMockDetails().stream().map(employeeUtils::entityToDTO).collect(Collectors.toList());
		log.info("Mock ratings retrieved successfully for employee: {}", employeeId);
		return mockDetailsDTOs;
	}

	@Override
	public Boolean setEmployeeMockRating(String employeeId, Integer mockId, MockDetailsDTO mockDetailsDTO) {

		log.info("Setting mock rating for employee: {}", employeeId);
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> {
			log.error("Failed to set mock ratings for employee: {}. Employee not found.", employeeId,
					EMPLOYEE_NOT_FOUND);
			throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND);
		});

		Optional<MockDetails> firstMockDetails = employee.getMockDetails().stream().filter(m -> {
			if (m.getMock().getMockNumber() == mockId)
				return true;
			return false;
		}).findFirst();

		if (firstMockDetails.isPresent()) {
			MockDetails mockDetails = firstMockDetails.get();
			BeanUtils.copyProperties(mockDetailsDTO, mockDetails);
			mockDetailsRepository.save(mockDetails);
			log.info("Mock rating set successfully for employee: {}", employeeId);
			return true;
		}
		log.info("Mock rating not set for employee: {}", employeeId);
		return false;
	}

	@Override
	public Boolean updateEmployeeStatus(String employeeId, EmployeeStatusDTO employeeStatusDTO) {
		log.info("Updating employee status for employee: {}", employeeId);
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> {
			log.error("Failed to update employee status for employee: {}. Employee not found.", employeeId,
					EMPLOYEE_NOT_FOUND);
			throw new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND);
		});
		employee.setEmployeeStatus(employeeStatusDTO.getEmployeeStatus());
		employeeRepository.save(employee);
		log.info("Employee status updated successfully for employee: {}", employeeId);
		return true;
	}

	@Override
	public Boolean resetPassword(@Valid PasswordResetDTO passwordResetDTO) {
		log.info("Resetting password for user");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		AppUser employee = appUserRepository.findById(authentication.getName()).orElseThrow(() -> {
			log.error("Failed to reset password. Username not found.", USERNAME_NOT_FOUND);
			throw new UsernameNotFoundException(USERNAME_NOT_FOUND);
		});
		if (passwordEncoder.matches(passwordResetDTO.getOldPassword(), employee.getPassword())
				&& passwordResetDTO.getNewPassword().equals(passwordResetDTO.getReEnterPassword())) {
			employee.setPassword(passwordEncoder.encode(passwordResetDTO.getNewPassword()));
			employee.setPasswordReset(true);
			appUserRepository.save(employee);
			log.info("Password reset successfully for user");
			return true;
		}
		log.info("Password reset failed for user");
		return false;
	}
}

package com.ty.lms.util;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ty.lms.dto.AddressDTO;
import com.ty.lms.dto.AttendanceDTO;
import com.ty.lms.dto.BankDTO;
import com.ty.lms.dto.ContactDTO;
import com.ty.lms.dto.EducationDTO;
import com.ty.lms.dto.EmployeeDTO;
import com.ty.lms.dto.EmployeeRegisterDTO;
import com.ty.lms.dto.EmployeeSecondryDTO;
import com.ty.lms.dto.ExperienceDTO;
import com.ty.lms.dto.MockDTO;
import com.ty.lms.dto.MockDetailsDTO;
import com.ty.lms.dto.RequestDTO;
import com.ty.lms.dto.TechnicalSkillDTO;
import com.ty.lms.entity.Address;
import com.ty.lms.entity.Bank;
import com.ty.lms.entity.Contact;
import com.ty.lms.entity.Education;
import com.ty.lms.entity.Employee;
import com.ty.lms.entity.EmployeeAttendence;
import com.ty.lms.entity.EmployeeSecondry;
import com.ty.lms.entity.Experience;
import com.ty.lms.entity.Mock;
import com.ty.lms.entity.MockDetails;
import com.ty.lms.entity.Request;
import com.ty.lms.entity.TechnicalSkill;

import jakarta.validation.Valid;

@Component
public class EmployeeUtils {

	public Employee dtoToEntity(EmployeeRegisterDTO employeeRegisterDTO) {

		Employee employee = Employee.builder().build();
		BeanUtils.copyProperties(employeeRegisterDTO, employee);

		EmployeeSecondryDTO employeeSecondryDTO = employeeRegisterDTO.getEmployeeSecondry();
		EmployeeSecondry employeeSecondry = EmployeeSecondry.builder().build();
		BeanUtils.copyProperties(employeeSecondryDTO, employeeSecondry);

		Set<Education> educations = employeeRegisterDTO.getEducations().stream().map(edu -> {
			Education education = dtoToEntity(edu);
			education.setEmployee(employee);
			return education;
		}).collect(Collectors.toSet());

		List<Address> addresses = employeeRegisterDTO.getAddresses().stream().map(a -> dtoToEntity(a))
				.collect(Collectors.toList());

		Bank bank = dtoToEntity(employeeRegisterDTO.getBank());

		Set<TechnicalSkill> technicalSkills = employeeRegisterDTO.getTechnicalSkill().stream().map(ts -> {
			TechnicalSkill technicalSkill = dtoToEntity(ts);
			technicalSkill.setEmployee(employee);
			return technicalSkill;
		}).collect(Collectors.toSet());

		Set<Experience> experiences = employeeRegisterDTO.getExperiences().stream().map(exp -> {
			Experience experience = dtoToEntity(exp);
			experience.setEmployee(employee);
			return experience;
		}).collect(Collectors.toSet());

		List<Contact> contacts = employeeRegisterDTO.getContacts().stream().map(c -> dtoToEntity(c))
				.collect(Collectors.toList());

		employee.setEmployeeSecondry(employeeSecondry);
		employee.setEducations(educations);
		employee.setAddresses(addresses);
		employee.setBank(bank);
		employee.setTechnicalSkills(technicalSkills);
		employee.setExperiences(experiences);
		employee.setContacts(contacts);

		return employee;
	}

	public Education dtoToEntity(EducationDTO educationDTO) {
		Education education = Education.builder().build();
		BeanUtils.copyProperties(educationDTO, education);
		return education;
	}

	public Address dtoToEntity(AddressDTO addressDTO) {
		Address address = Address.builder().build();
		BeanUtils.copyProperties(addressDTO, address);
		return address;
	}

	public Bank dtoToEntity(BankDTO bankDTO) {
		Bank bank = Bank.builder().build();
		BeanUtils.copyProperties(bankDTO, bank);
		return bank;
	}

	public TechnicalSkill dtoToEntity(TechnicalSkillDTO technicalSkillDTO) {
		TechnicalSkill technicalSkill = TechnicalSkill.builder().build();
		BeanUtils.copyProperties(technicalSkillDTO, technicalSkill);
		return technicalSkill;
	}

	public Experience dtoToEntity(ExperienceDTO experienceDTO) {
		Experience experience = Experience.builder().build();
		BeanUtils.copyProperties(experienceDTO, experience);
		return experience;
	}

	public Contact dtoToEntity(ContactDTO contactDTO) {
		Contact contact = Contact.builder().build();
		BeanUtils.copyProperties(contactDTO, contact);
		return contact;
	}

	public EmployeeAttendence dtoToEntity(@Valid AttendanceDTO attendanceDTO) {
		EmployeeAttendence attendence = EmployeeAttendence.builder().build();
		BeanUtils.copyProperties(attendanceDTO, attendence);
		return attendence;
	}

	public MockDetails dtoToEntity(MockDetailsDTO mockDetailsDTO) {
		MockDetails mockDetails = MockDetails.builder().build();
		BeanUtils.copyProperties(mockDetailsDTO, mockDetails);
		mockDetails.setMock(dtoToEntity(mockDetailsDTO.getMock()));
		return mockDetails;
	}

	public Mock dtoToEntity(MockDTO mockDTO) {
		Mock mock = Mock.builder().build();
		BeanUtils.copyProperties(mockDTO, mock);
		return mock;
	}

	public RequestDTO entityToDto(Request request) {
		RequestDTO requestDTO = RequestDTO.builder().build();
		BeanUtils.copyProperties(request, requestDTO);
		requestDTO.setEmployee(entityToDto(request.getEmployee()));
		return requestDTO;
	}

	public EmployeeDTO entityToDto(Employee employee) {

		EmployeeDTO employeeDTO = EmployeeDTO.builder().build();
		BeanUtils.copyProperties(employee, employeeDTO);

		EmployeeSecondryDTO employeeSecondryDTO = entityToDto(employee.getEmployeeSecondry());

		Set<EducationDTO> educationDTOs = employee.getEducations().stream().map(e -> entityToDto(e))
				.collect(Collectors.toSet());

		List<AddressDTO> addressDTOs = employee.getAddresses().stream().map(a -> entityToDto(a))
				.collect(Collectors.toList());

		BankDTO bankDTO = entityToDto(employee.getBank());

		Set<TechnicalSkillDTO> technicalSkillDTOs = employee.getTechnicalSkills().stream().map(s -> entityToDto(s))
				.collect(Collectors.toSet());

		Set<ExperienceDTO> experienceDTOs = employee.getExperiences().stream().map(exp -> entityToDto(exp))
				.collect(Collectors.toSet());

		List<ContactDTO> contactDTOs = employee.getContacts().stream().map(c -> entityToDto(c))
				.collect(Collectors.toList());

		employeeDTO.setEmployeeSecondry(employeeSecondryDTO);
		employeeDTO.setEducations(educationDTOs);
		employeeDTO.setAddresses(addressDTOs);
		employeeDTO.setBank(bankDTO);
		employeeDTO.setTechnicalSkills(technicalSkillDTOs);
		employeeDTO.setExperiences(experienceDTOs);
		employeeDTO.setContacts(contactDTOs);

		return employeeDTO;
	}

	public EmployeeSecondryDTO entityToDto(EmployeeSecondry employeeSecondry) {
		EmployeeSecondryDTO employeeSecondryDTO = EmployeeSecondryDTO.builder().build();
		BeanUtils.copyProperties(employeeSecondry, employeeSecondryDTO);
		return employeeSecondryDTO;
	}

	public EducationDTO entityToDto(Education education) {
		EducationDTO educationDTO = EducationDTO.builder().build();
		BeanUtils.copyProperties(education, educationDTO);
		return educationDTO;
	}

	public AddressDTO entityToDto(Address address) {
		AddressDTO addressDTO = AddressDTO.builder().build();
		BeanUtils.copyProperties(address, addressDTO);
		return addressDTO;
	}

	public BankDTO entityToDto(Bank bank) {
		BankDTO bankDTO = BankDTO.builder().build();
		BeanUtils.copyProperties(bank, bankDTO);
		return bankDTO;
	}

	public TechnicalSkillDTO entityToDto(TechnicalSkill technicalSkill) {
		TechnicalSkillDTO technicalSkillDTO = TechnicalSkillDTO.builder().build();
		BeanUtils.copyProperties(technicalSkill, technicalSkillDTO);
		return technicalSkillDTO;
	}

	public ExperienceDTO entityToDto(Experience experience) {
		ExperienceDTO experienceDTO = ExperienceDTO.builder().build();
		BeanUtils.copyProperties(experience, experienceDTO);
		return experienceDTO;
	}

	public ContactDTO entityToDto(Contact contact) {
		ContactDTO contactDTO = ContactDTO.builder().build();
		BeanUtils.copyProperties(contact, contactDTO);
		return contactDTO;
	}

	public AttendanceDTO entityToDTO(EmployeeAttendence employeeAttendence) {
		AttendanceDTO attendanceDTO = AttendanceDTO.builder().build();
		BeanUtils.copyProperties(employeeAttendence, attendanceDTO);
		return attendanceDTO;
	}

	public MockDetailsDTO entityToDTO(MockDetails mockDetails) {
		MockDetailsDTO mockDetailsDTO = MockDetailsDTO.builder().build();
		BeanUtils.copyProperties(mockDetails, mockDetailsDTO);
		mockDetailsDTO.setMock(entityToDTO(mockDetails.getMock()));
		return mockDetailsDTO;
	}

	public MockDTO entityToDTO(Mock mock) {
		MockDTO mockDTO = MockDTO.builder().build();
		BeanUtils.copyProperties(mock, mockDTO);
		return mockDTO;
	}

}

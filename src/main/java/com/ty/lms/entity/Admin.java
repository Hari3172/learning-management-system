package com.ty.lms.entity;

import org.hibernate.annotations.GenericGenerator;

import com.ty.lms.entity.enums.Gender;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_seq")
	@GenericGenerator(name = "admin_seq", strategy = "com.ty.lms.custom.AdminId")
	private String adminId;
	private String adminName;
	private String adminEmailId;
	private Gender gender;

}

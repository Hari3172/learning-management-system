package com.ty.lms.entity;

import com.ty.lms.entity.enums.Contacts;

import jakarta.persistence.Entity;
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
public class Contact {

	@Id
	private long contactNumber;

	private Contacts contactType;
}

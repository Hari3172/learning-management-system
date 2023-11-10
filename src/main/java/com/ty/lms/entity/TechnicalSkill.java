package com.ty.lms.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class TechnicalSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "technical_skill_seq")
	@SequenceGenerator(name = "technical_skill_seq", sequenceName = "technical_skill_seq", allocationSize = 1, initialValue = 1)
	private Integer technicalSkillId;

	@NotNull(message = "NULL data passed for skillType")
	@NotBlank(message = "BLANK data passes for skillType")
	private String technicalSkillType;

	@NotNull(message = "NULL data passed for skillName")
	@NotBlank(message = "BLANK data passes for skillName")
	private String technicalSkillName;

	@NotNull(message = "NULL data passed for skillRating")
	private Double technicalSkillRating;

	@NotNull(message = "NULL data passed for skillRating")
	private Double yearOfExperience;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Employee employee;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Mentor mentor;

}

package com.ty.lms.entity;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
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
public class Mentor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mentor_seq")
	@GenericGenerator(name = "mentor_seq", strategy = "com.ty.lms.custom.MentorId")
	private String mentorId;

	@NotNull(message = "NULL data passed for mentorName")
	@NotBlank(message = "BLANK data passes for mentorName")
	private String mentorName;

	@NotNull(message = "NULL data passed for mentorEmailId")
	@NotBlank(message = "BLANK data passes for mentorEmailId")
	@Email
	private String mentorEmailId;

	@JoinTable(name = "map_batch_mentor", joinColumns = @JoinColumn(name = "batch_fk"), inverseJoinColumns = @JoinColumn(name = "mentor_fk"))
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Batch> batchs;

	@JoinTable(name = "map_mentor_tech_skill", joinColumns = @JoinColumn(name = "mentor_fk"), inverseJoinColumns = @JoinColumn(name = "tech_skill_fk"))
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<TechnicalSkill> technicalSkills;

	@JoinTable(name = "map_mentor_mock", joinColumns = @JoinColumn(name = "mentor_fk"), inverseJoinColumns = @JoinColumn(name = "mock_fk"))
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Mock> mocks;
}


package com.ty.lms.dto;

import java.util.Set;
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
public class MentorRegisterDTO {

	@NotNull
	@NotBlank
	private String password;
	@NotNull(message = "NULL data passed for mentorName")
	@NotBlank(message = "BLANK data passes for mentorName")
	private String mentorName;
	@NotNull(message = "NULL data passed for mentorEmailId")
	@NotBlank(message = "BLANK data passes for mentorEmailId")
	@Email
	private String mentorEmailId;
	private Set<TechnicalSkillDTO> technicalSkills;
}

package com.ty.lms.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class AppUser {
	@Id
	private String username;
	private String password;

	@JoinTable(name = "map_user_role", joinColumns = @JoinColumn(name = "user_fk"), inverseJoinColumns = @JoinColumn(name = "role_fk"))
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Role> roles;
	private boolean isPasswordReset;
	private boolean isAccountActive;

}

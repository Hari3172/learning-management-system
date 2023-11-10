package com.ty.lms.constant;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ty.lms.entity.Admin;
import com.ty.lms.entity.AppUser;
import com.ty.lms.entity.Role;
import com.ty.lms.repository.AdminRepository;
import com.ty.lms.repository.AppUserRepository;
import com.ty.lms.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class AppData implements CommandLineRunner {

	private static final String USERNAMEADMIN = "admin01@gmail.com";

	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;
	private final AdminRepository adminRepository;
	private final AppUserRepository appUserRepository;

	@Override
	public void run(String... args) throws Exception {

		Optional<AppUser> userOP = appUserRepository.findById(USERNAMEADMIN);

		if (userOP.isEmpty()) {

			Role admin = Role.builder().roleName("ROLE_ADMIN").appUsers(Lists.newArrayList()).build();
			Role mentor = Role.builder().roleName("ROLE_MENTOR").appUsers(Lists.newArrayList()).build();
			Role employee = Role.builder().roleName("ROLE_EMPLOYEE").appUsers(Lists.newArrayList()).build();

			HashSet<Role> roles = Sets.newHashSet();
			roles.add(admin);

			Admin admin_ = Admin.builder().adminName("admin01").adminEmailId("admin01@gmail.com").build();

			AppUser appUser = AppUser.builder().username(USERNAMEADMIN).password(passwordEncoder.encode("qwert"))
					.isAccountActive(true).isPasswordReset(true).roles(roles).build();

			admin.getAppUsers().add(appUser);
			mentor.getAppUsers().add(appUser);
			employee.getAppUsers().add(appUser);

			roleRepository.save(admin);
			roleRepository.save(mentor);
			roleRepository.save(employee);

			adminRepository.save(admin_);

		}

	}
}

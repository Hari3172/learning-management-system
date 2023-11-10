package com.ty.lms.service.implementation;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ty.lms.entity.AppUser;
import com.ty.lms.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private final AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Attempting to load user by username: {}", username);
		AppUser user = appUserRepository.findById(username).orElseThrow(
				() -> new UsernameNotFoundException("User with username " + username + " doesn't exist!!"));
		log.debug("User found: {}", user.getUsername());
		UserDetails userDetails = User.builder().username(username).password(user.getPassword()).authorities(user.getRoles().stream()
				.map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList())).build();
        log.info("User details loaded for user: {}", username);
		return userDetails;
	}
}

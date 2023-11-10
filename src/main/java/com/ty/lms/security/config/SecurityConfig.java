package com.ty.lms.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ty.lms.security.filter.JwtAuthenticationEntryPoint;
import com.ty.lms.security.filter.SecurityFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	private static final String[] WHITE_LIST_URL = { "/api/v1/users/**", "/api/v1/auth/**", "/v2/api-docs",
			"/v3/api-docs", "/v3/api-docs/**", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
			"/configuration/security", "/swagger-ui/**", "/webjars/**", "/swagger-ui.html" };

	/*
	 * CORS must be processed before Spring Security because the pre-flight request
	 * will not contain any cookies (i.e. the JSESSIONID ). If the request does not
	 * contain any cookies and Spring Security is first, the request will determine
	 * the user is not authenticated (since there are no cookies in the request) and
	 * reject it.
	 */
	private final PasswordEncoder passwordEncoder;
	private final JwtAuthenticationEntryPoint authenticationEntryPoint;
	private final UserDetailsService userDetailsService;
	private final SecurityFilter securityFilter;

	@Bean
	AuthenticationManager authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return new ProviderManager(authProvider);
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable());
		http.cors(cors -> cors.disable());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/admins/**").hasAnyRole("ADMIN"));
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/requests/**").hasAnyRole("ADMIN"));
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/mentors/**").hasAnyRole("ADMIN", "MENTOR"));
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/mentors/register").hasRole("ADMIN"));
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/mocks/**").hasAnyRole("ADMIN", "MENTOR"));
		http.authorizeHttpRequests(
				auth -> auth.requestMatchers("/api/v1/attendences/**").hasAnyRole("ADMIN", "MENTOR"));
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/batchs/**").hasAnyRole("ADMIN", "MENTOR"));
		http.authorizeHttpRequests(
				auth -> auth.requestMatchers("/api/v1/employees/**").hasAnyRole("ADMIN", "MENTOR", "EMPLOYEE"));
		http.authorizeHttpRequests(
				auth -> auth.requestMatchers(WHITE_LIST_URL).permitAll().anyRequest().authenticated());
		http.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint));
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.authenticationManager(authenticationProvider());
		http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}

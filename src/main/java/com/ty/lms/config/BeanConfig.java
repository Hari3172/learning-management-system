package com.ty.lms.config;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class BeanConfig {

	/*
	 * PasswordEncoder Configuration
	 */
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * Swagger Configuration
	 */
	@Bean
	OpenAPI openAPI() {
		Info info = new Info().description("Learning Management System Developed By Harishankar Patel")
				.title("Learning Management System (LMS)").version("v1");
		return new OpenAPI().info(info);

	}

	
	
}

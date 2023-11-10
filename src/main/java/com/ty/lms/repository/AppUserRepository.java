package com.ty.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.lms.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String>{

}

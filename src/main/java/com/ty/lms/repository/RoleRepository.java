package com.ty.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.lms.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}

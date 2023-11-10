package com.ty.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.lms.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {

}

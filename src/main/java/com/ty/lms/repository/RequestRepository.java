package com.ty.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.lms.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {

}

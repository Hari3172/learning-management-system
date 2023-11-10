package com.ty.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.lms.entity.Mock;

public interface MockRepository extends JpaRepository<Mock, Integer> {

}

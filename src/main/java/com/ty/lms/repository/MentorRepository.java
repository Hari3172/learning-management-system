package com.ty.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.lms.entity.Mentor;

public interface MentorRepository extends JpaRepository<Mentor, String> {

}

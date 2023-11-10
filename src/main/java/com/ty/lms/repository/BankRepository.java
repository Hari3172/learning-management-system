package com.ty.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.lms.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

}

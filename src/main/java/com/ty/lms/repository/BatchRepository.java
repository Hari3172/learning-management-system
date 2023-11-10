package com.ty.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.lms.entity.Batch;

public interface BatchRepository extends JpaRepository<Batch, String> {

	Optional<Batch> findByBatchName(String batchNames);

}

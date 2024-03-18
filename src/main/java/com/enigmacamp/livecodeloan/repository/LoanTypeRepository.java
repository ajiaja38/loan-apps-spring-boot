package com.enigmacamp.livecodeloan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enigmacamp.livecodeloan.model.entity.LoanType;

public interface LoanTypeRepository extends JpaRepository<LoanType, String> {
  
}
